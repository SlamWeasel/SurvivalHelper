package de.SlamWeasel.SurvivalHelper.command;

import de.SlamWeasel.SurvivalHelper.util.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCraft implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player) sender;
            if(p.getInventory().contains(Material.CRAFTING_TABLE))
                p.openWorkbench(p.getLocation(), true);
            else
                switch(ConfigManager.getConfig().getString("language"))
                {
                    case "en": p.sendMessage(ChatColor.RED + "You need to have a workbench in your inventory to do that");break;
                    case "de": p.sendMessage(ChatColor.RED + "Du musst eine Werkbank im Inventar haben um das tuen zu können");break;
                }
        }
        return false;
    }
}
