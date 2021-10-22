package de.SlamWeasel.SurvivalHelper.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class Craftables
{
    public enum Crafts
    {
        DEEP_STORAGE
    }

    public static ItemStack getCraftable(Crafts c)
    {
        if(ConfigManager.getLang().contains("en"))
        {
            switch (c) {
                case DEEP_STORAGE:      ItemStack i = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                                          SkullMeta iMeta = (SkullMeta) i.getItemMeta();
                                            iMeta.setDisplayName("§6Storage Unit");
                                            iMeta.setOwner("MHF_Chest");
                                            iMeta.setLore(Arrays.asList("You can put three chests worth of", "one item in here"));
                                          i.setItemMeta(iMeta);
                                        return i;
                default:                ItemStack n = new ItemStack(Material.BARRIER);
                                        return n;
            }
        }
        else
        {
            switch (c) {
                case DEEP_STORAGE:      ItemStack i = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                                          SkullMeta iMeta = (SkullMeta) i.getItemMeta();
                                            iMeta.setDisplayName("§6Speichereinheit");
                                            iMeta.setOwner("MHF_Chest");
                                            iMeta.setLore(Arrays.asList("Du kannst drei Kisten wert eines Items", "hier hineinpacken"));
                                          i.setItemMeta(iMeta);
                                        return i;
                default:                ItemStack n = new ItemStack(Material.BARRIER);
                                        return n;
            }
        }
    }
}
