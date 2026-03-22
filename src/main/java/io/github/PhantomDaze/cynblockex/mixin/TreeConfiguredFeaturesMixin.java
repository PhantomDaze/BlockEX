package io.github.PhantomDaze.cynblockex.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PropaguleBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.root.AboveRootPlacement;
import net.minecraft.world.gen.root.MangroveRootPlacement;
import net.minecraft.world.gen.root.MangroveRootPlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(TreeConfiguredFeatures.class)
public class TreeConfiguredFeaturesMixin {
    @Unique
    private static final RegistryKey<ConfiguredFeature<?, ?>> PALM = ConfiguredFeatures.of("palm");
    @Inject(at = @At("RETURN"), method = "bootstrap")
    private static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable, CallbackInfo ci) {
        RegistryEntryLookup<Block> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.BLOCK);
        BeehiveTreeDecorator beehiveTreeDecorator2 = new BeehiveTreeDecorator(0.01F);
        ConfiguredFeatures.register(featureRegisterable, PALM,
                Feature.TREE,
                (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.MANGROVE_LOG),
                        new UpwardsBranchingTrunkPlacer(
                                2,
                                1,
                                4,
                                UniformIntProvider.create(1, 4),
                                0.5F,
                                UniformIntProvider.create(0, 1),
                                registryEntryLookup.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
                        BlockStateProvider.of(Blocks.MANGROVE_LEAVES),
                        new RandomSpreadFoliagePlacer(
                                ConstantIntProvider.create(3),
                                ConstantIntProvider.create(0),
                                ConstantIntProvider.create(2),
                                70),
                        Optional.of(new MangroveRootPlacer(
                                UniformIntProvider.create(1, 3),
                                BlockStateProvider.of(Blocks.MANGROVE_ROOTS),
                                Optional.of(new AboveRootPlacement(BlockStateProvider.of(Blocks.MOSS_CARPET), 0.5F)),
                                new MangroveRootPlacement(registryEntryLookup.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                                        RegistryEntryList.of(Block::getRegistryEntry, new Block[]{Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS}),
                                        BlockStateProvider.of(Blocks.MUDDY_MANGROVE_ROOTS),
                                        8, 15, 0.2F))),
                        new TwoLayersFeatureSize(
                                2, 0, 2)))
                        .decorators(List.of(
                                new LeavesVineTreeDecorator(0.125F),
                                new AttachedToLeavesTreeDecorator(
                                        0.14F, 1, 0,
                                        new RandomizedIntBlockStateProvider(BlockStateProvider.of((BlockState)Blocks.MANGROVE_PROPAGULE.getDefaultState().with(PropaguleBlock.HANGING, true)),
                                                PropaguleBlock.AGE, UniformIntProvider.create(0, 4)), 2, List.of(Direction.DOWN)), beehiveTreeDecorator2)).ignoreVines().build());
    }
}
