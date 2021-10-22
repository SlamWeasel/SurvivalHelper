package de.SlamWeasel.SurvivalHelper.listener;

import de.SlamWeasel.SurvivalHelper.util.ConfigManager;
import de.SlamWeasel.SurvivalHelper.util.NameFix;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryListener implements Listener
{
    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName().contains("Choose the item to store in") || e.getInventory().getName().contains("Wähle ein Item zum einlagern"))
        {
            if(p.getItemInHand().getAmount() == 1)
            {
                if(e.getCurrentItem().getType() != Material.AIR)
                {
                    e.setCancelled(true);
                    ItemMeta neue = p.getItemInHand().getItemMeta();
                    neue.setLore(
                            Arrays.asList(neue.getLore().get(0),
                                    neue.getLore().get(1),
                                    NameFix.getName(e.getCurrentItem()) + " x " + e.getCurrentItem().getAmount(),
                                    "§0§k" + e.getCurrentItem().getData().toString() + "|" + e.getCurrentItem().getType().toString()));
                    p.getItemInHand().setItemMeta(neue);
                    p.getInventory().setItem(e.getSlot(), null);
                    p.closeInventory();
                }
                else
                    e.setCancelled(true);
            }
            else
            {
                e.setCancelled(true);
                if(ConfigManager.getLang() == "de")
                    p.sendMessage(ChatColor.RED + "Du darfst keinen Stack von Speichereinheiten in der Hand haben, du musst eine allein haben");
                else
                    p.sendMessage(ChatColor.RED + "You can't have a stack of Storage Units in your hand, you need to have one alone");
            }
        }
    }
}
