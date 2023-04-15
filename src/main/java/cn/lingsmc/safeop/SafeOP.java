package cn.lingsmc.safeop;

import cn.lingsmc.safeop.utils.ConfigUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Crsuh2er0
 * @since 2023/1/16
 */
public final class SafeOP extends JavaPlugin {
    @Getter
    private static SafeOP instance;

    private static void initInstance() {
        instance = JavaPlugin.getPlugin(SafeOP.class);
    }
    @Override
    public void onLoad() {
        initInstance();
    }

    @Override
    public void onEnable() {
        ConfigUtils.initialize();
    }

    @Override
    public void onDisable() {
        ConfigUtils.removeBackup();
    }
}
