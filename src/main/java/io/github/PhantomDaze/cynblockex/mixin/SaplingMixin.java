package io.github.PhantomDaze.cynblockex.mixin;

import net.minecraft.block.SaplingGenerator;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

@Mixin(SaplingGenerator.class)
public class SaplingMixin {
    @Unique
    private static final SaplingGenerator cynblockex$PALM = new SaplingGenerator(
            "palm",
            Optional.of(TreeConfiguredFeatures.DARK_OAK),
            Optional.empty(),
            Optional.empty());
}
