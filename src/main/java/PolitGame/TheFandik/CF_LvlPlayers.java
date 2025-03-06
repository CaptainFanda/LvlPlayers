package PolitGame.TheFandik;

import PolitGame.TheFandik.Commands.LvlExecutor;
import PolitGame.TheFandik.Listener.FirstJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public final class CF_LvlPlayers extends JavaPlugin {
    private Logger log = getLogger();
    private static int lvl;
    private static Map<String, String> massages;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigData.createFiles();
        massages = ConfigData.loadMsgList();
        getServer().getPluginManager().registerEvents(new FirstJoinListener(), this);
        getCommand("lvl").setExecutor(new LvlExecutor());

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new LvlExpansion().register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static int getLevelFromPlayer(Player player) {
        File playerData = ConfigData.loadPlayerData();
        YamlConfiguration ymlPutData = YamlConfiguration.loadConfiguration(playerData);
        int lvl = (int) ymlPutData.get("players." + player.getName() + ".lvl");

        return lvl;

    }

    public static void setLvlToPlayer(Player player, int lvl) {
        File playerData = ConfigData.loadPlayerData();
        File config = ConfigData.loadConfig();
        YamlConfiguration ymlPutData = YamlConfiguration.loadConfiguration(playerData);
        YamlConfiguration ymlPutConfig = YamlConfiguration.loadConfiguration(config);
        if(lvl <= ymlPutConfig.getInt("maxLvl")) {
            if (ymlPutData.contains("players." + player.getName())) {
                ymlPutData.set("players." + player.getName() + ".lvl", lvl);
                ymlPutData.options().copyDefaults(true);
                try {
                    ymlPutData.save(playerData);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("Error to setLvl");
                }
            } else {
                ymlPutData.addDefault("players." + player.getName() + ".lvl" , lvl);
                ymlPutData.options().copyDefaults(true);
                try {
                    ymlPutData.save(playerData);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("Error to setLvl");
                }
            }
        }

    }
    public static void giveLvlToPlayer(Player player, int lvl) {
        int playerLvl = getLevelFromPlayer(player);
        File playerData = ConfigData.loadPlayerData();
        File config = ConfigData.loadConfig();
        YamlConfiguration ymlPutData = YamlConfiguration.loadConfiguration(playerData);
        YamlConfiguration ymlPutConfig = YamlConfiguration.loadConfiguration(config);
        if(lvl <= ymlPutConfig.getInt("maxLvl")) {
            if (ymlPutData.contains("players." + player.getName())) {
                ymlPutData.set("players." + player.getName() + ".lvl", playerLvl += lvl);
                ymlPutData.options().copyDefaults(true);
                try {
                    ymlPutData.save(playerData);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("Error to setLvl");
                }
            } else {
                ymlPutData.addDefault("players." + player.getName() + ".lvl" , playerLvl += lvl);
                ymlPutData.options().copyDefaults(true);
                try {
                    ymlPutData.save(playerData);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("Error to setLvl");
                }
            }
        }

    }
    public static Map<String, String> getMassages() {
        return massages;
    }
}
