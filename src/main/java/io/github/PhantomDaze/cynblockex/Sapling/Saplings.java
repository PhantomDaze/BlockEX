package io.github.PhantomDaze.cynblockex.Sapling;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class Saplings {
    // 创建自定义的棕榈树生成器
    public static final PalmSaplingGenerator PALM_GENERATOR = new PalmSaplingGenerator();
    
    private static Block register(String name, @NotNull Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.@NotNull Settings settings) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        RegistryKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, blockItem);

        return Registry.register(Registries.BLOCK, blockKey, block);
    }
    
    private static RegistryKey<Block> keyOfBlock(String name) { return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("cynblockex", name)); }
    private static RegistryKey<Item> keyOfItem(String name) { return RegistryKey.of(RegistryKeys.ITEM, Identifier.of("cynblockex", name)); }

    public static final Block PALM_SAPLING = register("palm_sapling", (settings) -> new SaplingBlock(PALM_GENERATOR, settings), AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY));

    public static void initialize() {}
}
