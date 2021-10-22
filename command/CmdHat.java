package de.SlamWeasel.SurvivalHelper.command;

import de.SlamWeasel.SurvivalHelper.util.ConfigManager;
import de.SlamWeasel.SurvivalHelper.util.NameFix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdHat implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            ItemStack i = p.getItemInHand();
            ItemStack oldHelmet = p.getInventory().getHelmet();

            try
            {
                try
                {
                    p.getInventory().setHelmet(i);
                    if(ConfigManager.getLang().equals("de"))
                        p.sendMessage(ChatColor.GREEN + "Du hast " + ChatColor.GOLD + NameFix.getName(i) + ChatColor.GREEN + " auf deinen Kopf gesetzt");
                    else
                        p.sendMessage(ChatColor.GREEN + "You put " + ChatColor.GOLD + NameFix.getName(i) + ChatColor.GREEN + " on your head");
                    i.setType(Material.AIR);
                    p.getInventory().setItem(p.getInventory().getHeldItemSlot(), oldHelmet);
                }
                catch(NullPointerException n)
                {
                    p.getInventory().setHelmet(i);
                    if(ConfigManager.getLang().equals("de"))
                        p.sendMessage(ChatColor.GREEN + "Du hast " + ChatColor.GOLD + NameFix.getName(i) + ChatColor.GREEN + " auf deinen Kopf gesetzt");
                    else
                        p.sendMessage(ChatColor.GREEN + "You put " + ChatColor.GOLD + NameFix.getName(i) + ChatColor.GREEN + " on your head");
                    i.setType(Material.AIR);
                }

            }
            catch(NullPointerException n)
            {
                if(ConfigManager.getLang().equals("de"))
                    p.sendMessage(ChatColor.RED + "Du musst schon ein Item in der Hand haben");
                else
                    p.sendMessage(ChatColor.RED + "You need to have an item in your hand");
                p.getInventory().setItem(p.getInventory().getHeldItemSlot(), oldHelmet);
            }
        }
        else
            Bukkit.getServer().getConsoleSender().sendMessage("Dies ist ein Command für Spieler");
        return false;
    }
}
