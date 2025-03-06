package PolitGame.TheFandik;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.structure.Palette;

import java.io.File;

public class LvlExpansion extends PlaceholderExpansion {


    @Override
    public String getAuthor() {
        return "CaptainFanky";
    }

    @Override
    public String getIdentifier() {
        return "lvls";
    }

    @Override
    public String getVersion() {
        return "1.2.1";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (player == null || !player.isOnline()) {
            return "";
        }
        if(params.equalsIgnoreCase("playerLvl")){
            try {
                int lvl = CF_LvlPlayers.getLevelFromPlayer((Player) player);
                return "" + lvl;
            } catch (NumberFormatException e) {
                return "";
            }
        }
        return null; // Placeholder is unknown by the Expansion
    }
}
