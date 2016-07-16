package com.techiecrow;

import com.techiecrow.listeners.OreCoal;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class OreRewards extends JavaPlugin
{

    public void onEnable()
    {
        this.registerCommands();
        this.registerListeners();
        this.registerConfig();
    }

    public void onDisable()
    {

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        String prefix = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Prefix"));
        if (command.getName().equalsIgnoreCase("orerewards") && sender.hasPermission("orerewards.reload"))
            if (args.length == 1)
                if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("orerewards.reload"))
                {
                    this.reloadConfig();
                    this.getConfig();
                    sender.sendMessage(prefix + ChatColor.GREEN + " Successfully reloaded the config!");
                    return true;
                } else
                {
                    sender.sendMessage(prefix + ChatColor.RED + " You need the \'orerewards.reload\' permission to use this command.");
                    return false;
                }
        sender.sendMessage(prefix + ChatColor.RED + " Command not valid.");
        return false;
    }

    private void registerCommands()
    {

    }

    private void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new OreCoal(this), this);
    }

    private void registerConfig()
    {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }
}