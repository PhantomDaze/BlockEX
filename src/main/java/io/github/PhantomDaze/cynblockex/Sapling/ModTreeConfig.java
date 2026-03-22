package io.github.PhantomDaze.cynblockex.Sapling;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;

/**
 * 模组树配置初始化器
 */
public class ModTreeConfig {
    public static void initialize() {
        // 在数据加载阶段注册树配置
        CommonLifecycleEvents.TAGS_LOADED.register((serverAccess, clientAccess) -> {
            // 这里不需要手动注册，PalmTreeConfig.PALM 键会被自动使用
        });
    }
}
