package cn.lingsmc.spigottemplate.listener;

import cn.lingsmc.spigottemplate.SpigotTemplate;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/31
 */
public class AnyListener implements Listener {
    static SpigotTemplate plugin = SpigotTemplate.getInstance();

    private AnyListener() {

    }

    public static void initialize() {
        Bukkit.getPluginManager().registerEvents(new AnyListener(), plugin);
    }
}
