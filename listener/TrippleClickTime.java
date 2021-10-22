package de.SlamWeasel.SurvivalHelper.listener;

import de.SlamWeasel.SurvivalHelper.main.Main;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TrippleClickTimer extends BukkitRunnable
{
    Main plugin;
    ItemStack i;
    public static boolean FailIt = false;

    public TrippleClickTimer(Main plugin, ItemStack i)
    {
        this.plugin = plugin;
        this.i = i;
    }

    @Override
    public void run()
    {
        if(TrippleLeftClickListener.clicks >= 3)
        {
            TrippleLeftClickListener.output(i);
            TrippleLeftClickListener.fired = false;
            return;
        }
        else
        {
            TrippleLeftClickListener.clicks = 0;
            return;
        }
    }
}
