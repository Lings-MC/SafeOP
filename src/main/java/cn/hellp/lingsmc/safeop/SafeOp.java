package cn.hellp.lingsmc.safeop;

import cn.hellp.lingsmc.safeop.utils.ConfigUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Crsuh2er0
 * @since 2023/1/16
 */
public final class SafeOp extends JavaPlugin {
    @Getter
    private static SafeOp instance;

    private static void initInstance() {
        instance = JavaPlugin.getPlugin(SafeOp.class);
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
