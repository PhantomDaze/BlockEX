package io.github.PhantomDaze.cynblockex.Sapling;
import io.github.PhantomDaze.cynblockex.Sapling.PalmTreeConfig;

import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

/**
 * 自定义树苗生成器 - 用于棕榈树
 */
public class PalmSaplingGenerator extends SaplingGenerator {
    public PalmSaplingGenerator() {
        super(
                "palm",
                Optional.of(PalmTreeConfig.PALM),  // 使用 PalmTreeConfig 中定义的注册键
                Optional.empty(),
                Optional.empty()
        );
    }
    public static void initialize() {}
}
