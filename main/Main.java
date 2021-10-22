package de.SlamWeasel.SurvivalHelper.main;

import de.SlamWeasel.SurvivalHelper.command.CmdCraft;
import de.SlamWeasel.SurvivalHelper.command.CmdHat;
//import de.SlamWeasel.SurvivalHelper.listener.ClickListener;
//import de.SlamWeasel.SurvivalHelper.listener.InventoryListener;
//import de.SlamWeasel.SurvivalHelper.listener.TrippleLeftClickListener;
import de.SlamWeasel.SurvivalHelper.listener.ClicksListener;
import de.SlamWeasel.SurvivalHelper.listener.InventoryListener;
import de.SlamWeasel.SurvivalHelper.listener.TrippleLeftClickListener;
import de.SlamWeasel.SurvivalHelper.util.ConfigManager;
import de.SlamWeasel.SurvivalHelper.util.Scheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main plugin;
    private ConfigManager cfgM;

    public void onEnable()
    {
        plugin = this;

        this.getCommand("craft").setExecutor(new CmdCraft());
        this.getCommand("hat").setExecutor(new CmdHat());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new TrippleLeftClickListener(), this);
        pm.registerEvents(new ClicksListener(), this);
        pm.registerEvents(new InventoryListener(), this);

        loadConfigManager();
        Scheduler.Scheduler();

        System.out.println("SurvivalHelper has been loaded");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public void loadConfigManager()
    {
        cfgM = new ConfigManager();
        cfgM.setup();
    }
}
