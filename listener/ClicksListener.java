package de.SlamWeasel.SurvivalHelper.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ClicksListener implements Listener
{
    @Deprecated
    @EventHandler
    public void onClick(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if (e.getAction() == Action.LEFT_CLICK_AIR && p.isSneaking())
        {
            if(e.getItem().getType().equals(Material.COBBLESTONE) && e.getItem().getAmount() == 64)
            {
                //Compress.setOwner("MHF_Chest");
                ItemStack com = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta met = (SkullMeta) com.getItemMeta();
                met.setDisplayName(ChatColor.BLUE + "Compressed Cobblestone");
                met.setOwner("MHF_Chest");
                com.setItemMeta(met);

                p.getItemInHand().setAmount(0);
                p.getInventory().addItem(com);
            }
            else if(e.getItem().getType().equals(Material.PLAYER_HEAD) && e.getItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Compressed Cobblestone"))
            {
                e.getItem().setAmount(e.getItem().getAmount() - 1);
                p.getInventory().addItem(new ItemStack(Material.COBBLESTONE, 64));
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();
        ItemStack i = e.getItemInHand();
        if(i.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Compressed Cobblestone"))
        {
            e.setCancelled(true);
        }
    }
}
