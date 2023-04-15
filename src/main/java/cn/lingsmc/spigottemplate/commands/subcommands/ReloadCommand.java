package cn.lingsmc.spigottemplate.commands.subcommands;

import cn.lingsmc.spigottemplate.commands.SubCommand;
import cn.lingsmc.spigottemplate.constants.MessageConstants;
import cn.lingsmc.spigottemplate.utils.ConfigUtils;
import cn.lingsmc.spigottemplate.utils.PermissionUtils;
import org.bukkit.command.CommandSender;

/**
 * @author Crsuh2er0
 * @apiNote
 * @since 2023/1/18
 */
public class ReloadCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (PermissionUtils.nonAdminAuth(sender)) {
            return;
        }

        ConfigUtils.initialize();
        sender.sendMessage(MessageConstants.RELOAD_SUCCESS);
    }
}
