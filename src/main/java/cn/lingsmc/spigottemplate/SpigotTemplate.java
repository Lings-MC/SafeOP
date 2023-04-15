package cn.lingsmc.spigottemplate;

import cn.lingsmc.spigottemplate.commands.Commands;
import cn.lingsmc.spigottemplate.listener.AnyListener;
import cn.lingsmc.spigottemplate.utils.ConfigUtils;
import lombok.Getter;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Crsuh2er0
 * @since 2023/1/16
 */
public final class SpigotTemplate extends JavaPlugin {
    @Getter
    private static SpigotTemplate instance;

    private static void initInstance() {
        instance = JavaPlugin.getPlugin(SpigotTemplate.class);
    }

    @Override
    public void onLoad() {
        initInstance();
        ConfigUtils.initialize();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        // init commands
        final PluginCommand command = this.getCommand(instance.getName());
        assert command != null;
        Commands commands = new Commands();
        command.setExecutor(commands);
        command.setTabCompleter(commands);
        // init listeners
        AnyListener.initialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
