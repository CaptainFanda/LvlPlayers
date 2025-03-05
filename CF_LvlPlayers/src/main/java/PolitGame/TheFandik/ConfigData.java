package PolitGame.TheFandik;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigData {
    private static File config = new File("plugins/CF_LvlPlayers/config.yml");
    private static File message = new File("plugins/CF_LvlPlayers/message.yml");
    private static File playerData = new File("plugins/CF_LvlPlayers/playerData.yml");

    public static void createFiles() {
        YamlConfiguration ymlPut;
        if(!config.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(config);
            int maxLvl = 10;
            int defaultLvl = 1;
            ymlPut.addDefault("maxLvl", maxLvl);
            ymlPut.addDefault("defaultLvl", defaultLvl);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(config);

            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("Error to Create config.yml");
            }
        }
        if(!message.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(message);
            LinkedHashMap<String, Object> messages = new LinkedHashMap<>();
            messages.put("prefix", "[&eLvlPlayers&f] ");
            messages.put("wrong-command", "&cНеверная Команда");
            messages.put("not-permission", "&cВы не имеете прав на использование");
            messages.put("succes-set", "&aУспешно Установлен уровень %target%: %lvl%");
            messages.put("usage", "&cИспользование: /lvl set [player] [lvl], " + "/lvl check [player], " + "/lvl give [player] [lvl]");
            messages.put("check-lvl", "&aВаш Уровень: %lvl%");
            messages.put("check-lvl-target", "&aУровень %target%: %lvl%");
            messages.put("give-lvl-target", "&aВы успешно выдали %target%: %lvl%");
            ymlPut.addDefaults(messages);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(message);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("Error to Create Message.yml");
            }
        }
    }
    public static Map<String, String> loadMsgList() {
        Map<String, String> map = new HashMap<>();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(message);
        Set<String> set = ymlPut.getKeys(true);
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String msg = ymlPut.getString(key);
            String msg1 = msg.replace('&', '§');
            map.put(key, msg1);
        }
        return map;
    }

    public static File loadConfig() {
        return config;
    }
    public static File loadPlayerData() {
        return playerData;
    }

}
