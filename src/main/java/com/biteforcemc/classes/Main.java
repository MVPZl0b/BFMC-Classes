package com.biteforcemc.classes;


import com.biteforcemc.commands.classesCommand;
import com.biteforcemc.handlers.classHandler;
import com.biteforcemc.handlers.placeHolderAPI;
import com.biteforcemc.listeners.classesListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Main instance;
    YamlConfiguration player = new YamlConfiguration();
    File file = new File(getDataFolder(), "config.yml");
    File players = new File(getDataFolder(), "players.yml");
    private classHandler classHandler;

    public static Main getInstance() {
        return instance;
    }

    public classHandler getClassHandler() {
        return classHandler;
    }

    public void onEnable() {
        instance = this;
        classHandler = new classHandler();
        registerCommands();
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        createConfig();
        saveDefaultConfig();

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new placeHolderAPI().register();
        }

        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> Bukkit.getOnlinePlayers().forEach(getClassHandler()::giveEffects), 0L, 60L);
    }

    //ik
    public void onDisable() {
        saveDefaultConfig();
        savePlayerFile();
    }

    public void registerCommands() {
        final PluginManager pm = getServer().getPluginManager();
        getCommand("classes").setExecutor(new classesCommand());
        pm.registerEvents(new classesListener(), this);

    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
            if (!players.exists()) {
                players.getParentFile().mkdirs();
                saveResource("players.yml", false);
                getLogger().info("players.yml not found, creating!");

            } else {
                getLogger().info("players.yml found, loading!");
            }
            player.load(players);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public File getPlayersFile() {
        return players;
    }

    public YamlConfiguration getPlayers() {
        return player;
    }

    public void savePlayerFile() {
        try {
            getPlayers().save(getPlayersFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
