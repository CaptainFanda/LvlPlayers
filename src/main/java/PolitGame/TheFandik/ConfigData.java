package PolitGame.TheFandik;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigData {
    private static File config = new File("plugins/CF_LvlPlayers/config.yml");
    private static File message = new File("plugins/CF_LvlPlayers/message.yml");
    private static File playerData = new File("plugins/CF_LvlPlayers/playerData.yml");
    private static File rewards = new File("plugins/CF_LvlPlayers/rewards.yml");

    public static void createFiles() {
        YamlConfiguration ymlPut;
        if(!config.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(config);
            int maxLvl = 10;
            int defaultLvl = 1;
            List<String> paywall1 = new ArrayList<>();
            paywall1.add("DIRT:128");
            paywall1.add("COBBLESTONE:128");
            List<String> paywall2 = new ArrayList<>();
            paywall2.add("DIRT:256");
            paywall2.add("COBBLESTONE:256");
            ymlPut.addDefault("maxLvl", maxLvl);
            ymlPut.addDefault("defaultLvl", defaultLvl);
            ymlPut.addDefault("paywall.1", paywall1);
            ymlPut.addDefault("paywall.2", paywall2);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(config);

            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("Error to Create config.yml");
            }
        }
        if(!rewards.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(rewards);
            Map<String, Object> item1 = new HashMap<>();
            item1.put("type", "DIAMOND_SWORD");
            item1.put("name", "The Best Sword");
            ymlPut.addDefault("rewards.reward1.items.item1", item1);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(rewards);
            } catch (IOException i) {
                Bukkit.getConsoleSender().sendMessage("Error to create Rewards");
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
            messages.put("not-have-resourses", "&cУ вас недостаточно Ресурсов для улучшение Уровня");
            messages.put("succes-upgrade-lvl", "&aУспешно улучшен уровень");
            messages.put("maxLvl", "&cВы достигли максимального Уровня");
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
    public static File loadReward() { return rewards; }

}
