package me.petulikan1.minecrafttodiscord.command;

import me.petulikan1.minecrafttodiscord.Loader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordMessageCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command command, String aa, String[] args) {
        if(!(s.hasPermission("DiscordMessages.Admin")))return true;
        if(args[0].equalsIgnoreCase("reload")){
            Loader.config();
            s.sendMessage("&8&l[&9DMSG!&8&l] &cReloaded!");
            return true;
        }
        return true;
    }
}
