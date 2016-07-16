package com.techiecrow.commands;

import com.techiecrow.OreRewards;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Brian on 15/07/2016.
 */
public class ReloadCommand implements CommandExecutor
{
    private OreRewards plugin;

    public ReloadCommand(OreRewards pl)
    {
        this.plugin = pl;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        String prefix = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Prefix"));

        if (label.equalsIgnoreCase("orerewardsreload") && sender.hasPermission("orerewards.reload"))
        {
            this.plugin.reloadConfig();
            this.plugin.getConfig();
            sender.sendMessage(prefix + ChatColor.GREEN + "Successfully reloaded the config!");
            return true;
        } else
        {
            sender.sendMessage(prefix + ChatColor.RED + "You need the \'orerewards.reload\' permission to use this command.");
            return false;
        }
    }
}