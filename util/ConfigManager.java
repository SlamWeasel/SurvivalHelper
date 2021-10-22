package de.SlamWeasel.SurvivalHelper.util;

import de.SlamWeasel.SurvivalHelper.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager
{
    private Main plugin = Main.getPlugin(Main.class);

    //Files
    public static File file;
    public static YamlConfiguration cfg;
    public static File kd;
    public static YamlConfiguration kdcfg;

    public void setup()
    {
        if(!plugin.getDataFolder().exists())
        {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), "config.yml");
        kd = new File(plugin.getDataFolder(), "kd.yml");

        if(!file.exists())
        {
            try
            {
                file.createNewFile();
                cfg = YamlConfiguration.loadConfiguration(file);
                cfg.set("language", "en");
                cfg.set("handyhats-underwater-enable", true);
                cfg.set("handyhats-unicorn-enable", true);
                cfg.set("handyhats-extrahearts-enable", true);
                cfg.set("handyhats-lamps-enable", true);
                cfg.set("handyhats-endereye-enable", true);
                cfg.set("handyhats-expbottle-enable", true);
                cfg.options().header("\n  Languages\n    en: English\n    de: Deutsch\n");
                cfg.options().copyHeader(true);
                saveFile(cfg, file);
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "config.yml has been created");
            }
            catch(IOException e)
            {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "An error accured while creating config.yml");
                e.printStackTrace();
            }
        }
        if(!kd.exists())
        {
            try
            {
                kd.createNewFile();
                kdcfg = YamlConfiguration.loadConfiguration(kd);
                kdcfg.options().header("\n  Here you can see all the deaths and kills of the players on your server\n You can look them up ingame with /kd and /kd [Playername]\n");
                kdcfg.options().copyHeader(true);
                saveFile(kdcfg, kd);
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "kd.yml has been created");
            }
            catch(IOException e)
            {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "An error accured while creating kd.yml ");
                e.printStackTrace();
            }
        }

        cfg = YamlConfiguration.loadConfiguration(file);
        kdcfg = YamlConfiguration.loadConfiguration(kd);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "config.yml is loaded");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "kd.yml is loaded");
    }

    public static String getLang()
    {
        cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getString("language");
    }
    public static YamlConfiguration getConfig()
    {
        return cfg;
    }
    public static YamlConfiguration getKDConfig()
    {
        return kdcfg;
    }

    public static void saveFile(YamlConfiguration cfg, File file)
    {
        try
        {
            cfg.save(file);
        }
        catch(IOException e)
        {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "config.yml konnte nicht gespeichert werden");
        }
    }

}
