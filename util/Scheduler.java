package de.SlamWeasel.SurvivalHelper.util;

import de.SlamWeasel.SurvivalHelper.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Iterator;

public class Scheduler
{
    static int waiter = 0;
    public static void Scheduler()
    {
        Server server = Bukkit.getServer();
        server.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            public void run()
            {
                Player player;
                Iterator var2;
                var2 = Bukkit.getOnlinePlayers().iterator();

                while(var2.hasNext())
                {
                    try
                    {
                        player = (Player) var2.next();
                        Material hut = player.getInventory().getHelmet().getType();

                        if(ConfigManager.getConfig().getBoolean("handyhats-underwater-enable"))
                            if ((hut == Material.GLASS) || (hut == Material.BEACON))
                                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 80, 0, false, false), false);
                        if(ConfigManager.getConfig().getBoolean("handyhats-unicorn-enable"))
                            if (hut == Material.END_ROD)
                                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 80, 0, false, false), true);
                        if(ConfigManager.getConfig().getBoolean("handyhats-extrahearts-enable"))
                            if (hut == Material.GOLDEN_APPLE)
                                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 80, 0, false, false), true);
                        if(ConfigManager.getConfig().getBoolean("handyhats-lamps-enable"))
                            if ((hut == Material.GLOWSTONE) || (hut == Material.SEA_LANTERN) || (hut == Material.BEACON))
                                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 80, 0, false, false), true);
                        if(ConfigManager.getConfig().getBoolean("handyhats-endereye-enable"))
                            if (hut == Material.ENDER_EYE)
                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 80, 0, false, false), true);
                        if(ConfigManager.getConfig().getBoolean("handyhats-expbottle-enable"))
                            if (hut == Material.EXPERIENCE_BOTTLE)
                            {
                                waiter++;
                                if(waiter >= 20)
                                {
                                    ExperienceOrb exp = (ExperienceOrb)player.getWorld().spawnEntity(player.getLocation(), EntityType.EXPERIENCE_ORB);
                                    exp.setExperience(2);
                                    waiter = 0;
                                }
                            }
                    }
                    catch(NullPointerException n)
                    {

                    }
                }
            }
        }, 0L, 60L);
    }
}
