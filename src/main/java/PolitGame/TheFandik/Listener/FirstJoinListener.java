package PolitGame.TheFandik.Listener;

import PolitGame.TheFandik.CF_LvlPlayers;
import PolitGame.TheFandik.ConfigData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class FirstJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = (Player) e.getPlayer();
        File playerData = ConfigData.loadPlayerData();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(playerData);
        if(!player.hasPlayedBefore()) {
            File config = ConfigData.loadConfig();
            YamlConfiguration ymlPutConfig = YamlConfiguration.loadConfiguration(config);
            int defaultLvl = ymlPutConfig.getInt("defaultLvl");
            CF_LvlPlayers.setLvlToPlayer(player, defaultLvl);
        } else {
            if(!ymlPut.contains("players." + player.getName())) {
                File config = ConfigData.loadConfig();
                YamlConfiguration ymlPutConfig = YamlConfiguration.loadConfiguration(config);
                int defaultLvl = ymlPutConfig.getInt("defaultLvl");
                CF_LvlPlayers.setLvlToPlayer(player, defaultLvl);
            }
        }
    }
}
