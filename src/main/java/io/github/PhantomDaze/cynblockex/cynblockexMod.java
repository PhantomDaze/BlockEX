package io.github.PhantomDaze.cynblockex;

import io.github.PhantomDaze.cynblockex.Sapling.ModTreeConfig;
import io.github.PhantomDaze.cynblockex.Sapling.Saplings;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class cynblockexMod implements ModInitializer {
    public static final String MOD_ID = "cynblockex";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        UpdateChecker.initialize();
        Saplings.initialize();
        ModBlock.initialize();
        ModItem.initialize();
        ModGroup.initialize();
        ModTreeConfig.initialize();
        LOGGER.info("C.Y.N. BlockEX Mod Loaded");
    }

}