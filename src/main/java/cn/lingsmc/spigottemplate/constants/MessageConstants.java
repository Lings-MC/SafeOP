package cn.lingsmc.spigottemplate.constants;

import cn.lingsmc.spigottemplate.SpigotTemplate;
import lombok.Getter;

import static cn.lingsmc.spigottemplate.constants.CommandConstants.*;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/18
 */
public class MessageConstants {
    public static final String RELOAD_SUCCESS = "§a重载成功.";
    public static final String UNKNOWN_COMMAND = "§c未知命令.";
    public static final String NO_PERMISSION = "§c你没有执行该命令的权限.";
    public static final String CONSOLE = "§c该命令必须由玩家执行.";
    static SpigotTemplate plugin = SpigotTemplate.getInstance();
    @Getter
    protected static final String[] ROOT_MESSAGE = new String[]{
            String.format("§3此服务器正在运行 §b%s %s§3 by %s", plugin.getName(), plugin.getDescription().getVersion(), "§aC§br§cs§du§eh§a2§be§cr§d0"),
            String.format("§3命令列表: §b/%s %s", ALIAS, HELP),
    };
    @Getter
    protected static final String[] HELP_MESSAGE = new String[]
            {
                    String.format("§3§l----- %s指令 -----", plugin.getName()),
                    String.format("§b/%s %s §3- §a重载本插件", ALIAS, RELOAD),
            };

    private MessageConstants() {
    }
}
