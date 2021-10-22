package de.SlamWeasel.SurvivalHelper.listener;

import de.SlamWeasel.SurvivalHelper.main.Main;
import de.SlamWeasel.SurvivalHelper.util.ConfigManager;
import de.SlamWeasel.SurvivalHelper.util.Craftables;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TrippleLeftClickListener implements Listener
{
    public static boolean fired = false;
    ItemStack i;
    ItemStack iBefore;
    static Player p;
    public static int clicks = 0;

    @EventHandler
    public void TC(PlayerInteractEvent e)
    {
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            if(e.getPlayer().isSneaking())
            {
                if((e.getItem().getType() == Material.CHEST) ||
                   (e.getItem().getType() == Material.PLAYER_HEAD))
                {
                    if (!fired)
                    {
                        TrippleClickTimer.FailIt = false;
                        TrippleClickTimer timer = new TrippleClickTimer(Main.getPlugin(), e.getPlayer().getItemInHand());
                        timer.runTaskLaterAsynchronously(Main.getPlugin(), 40);
                        fired = true;
                        iBefore = e.getPlayer().getItemInHand();
                        clicks = 1;
                    }
                    else
                    {
                        if(clicks < 3)
                        {
                            if (iBefore.getType() == e.getItem().getType())
                            {
                                clicks++;
                            }
                            else
                            {
                                fired = false;
                            }
                        }
                        else
                        {
                            fired = false;
                        }
                    }
                }
            }
        }
        i = e.getItem();
        p = e.getPlayer();
    }

    public static void output(ItemStack i)
    {
        switch(i.getType().toString())
        {
            case "CHEST":if(i.getAmount() == 3)
                        {
                            switch(ConfigManager.getLang())
                            {
                                case "de":  if(p.getLevel() >= 10)
                                            {
                                                p.setItemInHand(null);
                                                p.getInventory().addItem(Craftables.getCraftable(Craftables.Crafts.DEEP_STORAGE));
                                                p.sendMessage(ChatColor.GOLD + "Speichereinheit" + ChatColor.GREEN + " wurde erfolgreich gecraftet");
                                                p.setLevel(p.getLevel() - 10);
                                            }
                                            else
                                            {
                                                p.sendMessage(ChatColor.RED + "Du hast nicht genügend Level um die §6Speichereinheit" + ChatColor.RED + " zu craften, du brauchst hierfür " + ChatColor.GREEN + "10 Level");
                                            }
                                            break;
                                case " de": if(p.getLevel() >= 10)
                                            {
                                                p.setItemInHand(null);
                                                p.getInventory().addItem(Craftables.getCraftable(Craftables.Crafts.DEEP_STORAGE));
                                                p.sendMessage(ChatColor.GOLD + "Speichereinheit" + ChatColor.GREEN + " wurde erfolgreich gecraftet");
                                                p.setLevel(p.getLevel() - 10);
                                            }
                                            else
                                            {
                                                p.sendMessage(ChatColor.RED + "Du hast nicht genügend Level um die §6Speichereinheit" + ChatColor.RED + " zu craften, du brauchst hierfür " + ChatColor.GREEN + "10 Level");
                                            }
                                            break;
                                case "en":  if(p.getLevel() >= 10)
                                            {
                                                p.setItemInHand(null);
                                                p.getInventory().addItem(Craftables.getCraftable(Craftables.Crafts.DEEP_STORAGE));
                                                p.sendMessage(ChatColor.GOLD + "Storage Unit" + ChatColor.GREEN + " has been crafted successfully");
                                                p.setLevel(p.getLevel() - 10);
                                            }
                                            else
                                            {
                                                p.sendMessage(ChatColor.RED + "You don't have enough level to to craft a §6Storage Unit" + ChatColor.RED + ", you need " + ChatColor.GREEN + "10 level" + ChatColor.RED + " to do that");
                                            }
                                            break;
                                case " en":  if(p.getLevel() >= 10)
                                            {
                                                p.setItemInHand(null);
                                                p.getInventory().addItem(Craftables.getCraftable(Craftables.Crafts.DEEP_STORAGE));
                                                p.sendMessage(ChatColor.GOLD + "Storage Unit" + ChatColor.GREEN + " has been crafted successfully");
                                                p.setLevel(p.getLevel() - 10);
                                            }
                                            else
                                            {
                                                p.sendMessage(ChatColor.RED + "You don't have enough level to to craft a §6Storage Unit" + ChatColor.RED + ", you need " + ChatColor.GREEN + "10 level" + ChatColor.RED + " to do that");
                                            }
                            }
                        }
        }
    }
}
