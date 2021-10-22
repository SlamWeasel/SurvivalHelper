package de.SlamWeasel.SurvivalHelper.listener;

import de.SlamWeasel.SurvivalHelper.util.ConfigManager;
import de.SlamWeasel.SurvivalHelper.util.Craftables;
import de.SlamWeasel.SurvivalHelper.util.NameFix;
import de.SlamWeasel.SurvivalHelper.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ClickListener implements Listener
{
    @Deprecated
    @EventHandler
    public void onClick(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR ||e.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(e.getItem().getType() == Material.PLAYER_HEAD && p.isSneaking())
            {
                if(e.getItem().getItemMeta().getDisplayName().contains("Speichereinheit") || e.getItem().getItemMeta().getDisplayName().contains("Storage Unit"))
                {
                    if(e.getItem().getItemMeta().getLore().size() == 2)
                    {
                        if (ConfigManager.getLang() == "de")
                        {
                            Inventory choose = Bukkit.getServer().createInventory(p, 36, "Wähle ein Item zum einlagern");
                            for (int i = 0; i < 36; i++)
                            {
                                if (i < 9)
                                    choose.setItem(i + 27, p.getInventory().getContents()[i]);
                                else
                                    choose.setItem(i - 9, p.getInventory().getContents()[i]);
                            }
                            p.openInventory(choose);
                        }
                        else
                        {
                            Inventory choose = Bukkit.getServer().createInventory(p, 36, "Choose the item to store in");
                            for (int i = 0; i < 36; i++)
                            {
                                choose.setItem(i, p.getInventory().getContents()[i]);
                            }
                            p.openInventory(choose);
                        }
                    }
                    else
                    {
                        int Anzahl = Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]);
                        Material mat = Material.getMaterial(p.getItemInHand().getItemMeta().getLore().get(3).split("\\|")[1]);
                        int sub = Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].split("\\)|")[0]);
                        String[] Lore = Util.getStringArray(p.getItemInHand().getItemMeta().getLore().toArray());

                        if(Anzahl <= 64)
                        {
                            ItemStack i = new ItemStack(mat, Anzahl, (short) sub);
                            ItemMeta meta = p.getItemInHand().getItemMeta();
                            meta.setLore(Arrays.asList(Lore[0], Lore[1]));
                            p.getItemInHand().setItemMeta(meta);
                            p.getInventory().addItem(i);
                        }
                        else
                        {
                            ItemStack i = new ItemStack(mat, 64, (short) sub);
                            ItemMeta meta = p.getItemInHand().getItemMeta();
                            meta.setLore(Arrays.asList(Lore[0], Lore[1], NameFix.getName(p.getItemInHand()) + " x " + String.valueOf(Anzahl - 64), Lore[3]));
                            p.getItemInHand().setItemMeta(meta);
                            p.getInventory().addItem(i);
                        }

                        if(p.getItemInHand().getItemMeta().getLore().get(3).contains(" "))
                        {
                            if(Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]) > 64)
                            {
                                ItemStack i = new ItemStack(Material.getMaterial(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[0].split(" ")[1]), 64, (short) Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].substring(0, p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].length() - 1)));
                                p.getInventory().addItem(i);
                                ItemMeta neue = p.getItemInHand().getItemMeta();
                                neue.setLore(
                                        Arrays.asList(neue.getLore().get(0),
                                                neue.getLore().get(1),
                                                NameFix.getName(p.getItemInHand()) + " x " + String.valueOf(Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]) - 64),
                                                "§0§k" + p.getItemInHand().getData().toString()));
                                p.getItemInHand().setItemMeta(neue);
                            }
                            else
                            {
                                ItemStack i = new ItemStack(Material.getMaterial(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[0].split(" ")[1]), Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]), (short) Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].substring(0, p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].length() - 1)));
                                p.getInventory().addItem(i);
                                ItemMeta neue = p.getItemInHand().getItemMeta();
                                neue.setLore(
                                        Arrays.asList(neue.getLore().get(0),
                                                neue.getLore().get(1)));
                                p.getItemInHand().setItemMeta(neue);
                            }
                        }
                        else
                        {
                            if(Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]) > 64)
                            {
                                ItemStack i = new ItemStack(Material.getMaterial(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[0].substring(4, p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[0].length())), 64, (short) Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].substring(0, p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].length() - 1)));
                                p.getInventory().addItem(i);
                                ItemMeta neue = p.getItemInHand().getItemMeta();
                                neue.setLore(
                                        Arrays.asList(neue.getLore().get(0),
                                                neue.getLore().get(1),
                                                NameFix.getName(p.getItemInHand()) + " x " + String.valueOf(Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]) - 64),
                                                "§0§k" + p.getItemInHand().getData().toString()));
                                p.getItemInHand().setItemMeta(neue);
                            }
                            else
                            {
                                ItemStack i = new ItemStack(Material.getMaterial(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[0].substring(4, p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[0].length())), Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]), (short) Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].substring(0, p.getItemInHand().getItemMeta().getLore().get(3).split("\\(")[1].length() - 1)));
                                p.getInventory().addItem(i);
                                ItemMeta neue = p.getItemInHand().getItemMeta();
                                neue.setLore(
                                        Arrays.asList(neue.getLore().get(0),
                                                neue.getLore().get(1)));
                                p.getItemInHand().setItemMeta(neue);
                            }
                        }
                    }
                }
            }
        }
        else if(e.getAction() == Action.LEFT_CLICK_AIR ||e.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            e.setCancelled(true);
            if(e.getItem().getType() == Material.PLAYER_HEAD && p.isSneaking())
            {
                p.sendMessage("Head");
                if (e.getItem().getItemMeta().getDisplayName().contains("Speichereinheit") || e.getItem().getItemMeta().getDisplayName().contains("Storage Unit"))
                {
                    p.sendMessage("Name + " + e.getItem().getItemMeta().getLore().size());
                    if (e.getItem().getItemMeta().getLore().size() == 4)
                    {
                        boolean found = false;
                        int i = 0;
                        p.sendMessage("Meta  | " + p.getItemInHand().getItemMeta().getLore().get(3).split("\\|")[1]);
                        while(!found && i < 36)
                        {
                            try
                            {
                                if (p.getInventory().getItem(i).getType() == Material.getMaterial(p.getItemInHand().getItemMeta().getLore().get(3).split("\\|")[1]))
                                {
                                    p.sendMessage("Match");
                                    ItemMeta neue = p.getItemInHand().getItemMeta();
                                    neue.setLore(
                                            Arrays.asList(neue.getLore().get(0),
                                                    neue.getLore().get(1),
                                                    NameFix.getName(p.getItemInHand()) + " x " + String.valueOf(Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(2).split(" x ")[1]) + p.getInventory().getItem(i).getAmount()),
                                                    neue.getLore().get(3)));
                                    p.getItemInHand().setItemMeta(neue);
                                    p.getInventory().setItem(i, null);
                                    found = true;
                                }
                            }
                            catch(NullPointerException n)
                            {

                            }
                            i++;
                        }
                        if(!found)
                        {
                            if(ConfigManager.getLang().contains("de"))
                                p.sendMessage(ChatColor.RED + "Du hast kein " + NameFix.getName(p.getItemInHand()) + " in deinem Inventar!");
                            else
                                p.sendMessage(ChatColor.RED + "You've got no " + NameFix.getName(p.getItemInHand()) + " in your inventory!");
                        }
                    }
                }
            }
        }
    }
}
