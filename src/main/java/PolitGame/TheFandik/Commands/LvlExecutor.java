package PolitGame.TheFandik.Commands;

import PolitGame.TheFandik.CF_LvlPlayers;
import PolitGame.TheFandik.ConfigData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Map;

public class LvlExecutor implements CommandExecutor {
    private String prefix = CF_LvlPlayers.getMassages().get("prefix");
    private Map<String, String> massages = CF_LvlPlayers.getMassages();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            File config = ConfigData.loadConfig();
            YamlConfiguration ymlPutConfig = YamlConfiguration.loadConfiguration(config);
            if (args.length == 0) {
                String message = prefix + (String) massages.get("check-lvl");
                message = message.replace("%lvl%", "" + CF_LvlPlayers.getLevelFromPlayer(p));
                p.sendMessage(message);
            } else if (args.length >= 1 && args[0].equals("set")) {
                if (sender.hasPermission("Lvl.admin")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    int settedLvl = Integer.parseInt(args[2]);
                    try {
                        CF_LvlPlayers.setLvlToPlayer(target, settedLvl);
                        String message = prefix + (String) CF_LvlPlayers.getMassages().get("succes-set");
                        message = message.replace("%target%", target.getName());
                        message = message.replace("%lvl%", "" + settedLvl);
                        p.sendMessage(message);
                    } catch (Exception e) {
                        String message = prefix + (String) massages.get("usage");
                        p.sendMessage(message);
                    }
                } else {
                    String message = prefix + (String) CF_LvlPlayers.getMassages().get("not-permission");
                    p.sendMessage(message);
                }

            } else if (args.length >= 1 && args[0].equals("check")) {
                if (sender.hasPermission("Lvl.admin")) {
                    String targetName = args[1];
                    Player target = Bukkit.getPlayerExact(targetName);
                    String message = prefix + (String) CF_LvlPlayers.getMassages().get("check-lvl-target");
                    message = message.replace("%target%", target.getName());
                    message = message.replace("%lvl%", "" + CF_LvlPlayers.getLevelFromPlayer(target));
                    p.sendMessage(message);
                } else {
                    String message = prefix + (String) CF_LvlPlayers.getMassages().get("not-permission");
                    p.sendMessage(message);
                }
            } else if (args.length >= 1 && args[0].equals("give")) {
                if (sender.hasPermission("Lvl.admin")) {
                    String targetName = args[1];
                    int setterLvl = Integer.parseInt(args[2]);
                    int lvl = CF_LvlPlayers.getLevelFromPlayer(p) + setterLvl;
                    if (lvl <= ymlPutConfig.getInt("maxLvl")) {
                        Player target = Bukkit.getPlayerExact(targetName);
                        String message = prefix + (String) CF_LvlPlayers.getMassages().get("give-lvl-target");
                        message = message.replace("%target%", target.getName());
                        message = message.replace("%lvl%", "" + setterLvl);
                        CF_LvlPlayers.giveLvlToPlayer(p, setterLvl);
                        p.sendMessage(message);
                    } else {
                        p.sendMessage(CF_LvlPlayers.getMassages().get("Wrong-Command"));
                    }
                } else {
                    String message = prefix + (String) CF_LvlPlayers.getMassages().get("not-permission");
                    p.sendMessage(message);
                }
            }
            if (args.length == 1 && args[0].equals("set") || args.length == 1 && args[0].equals("check") || args.length == 1 && args[0].equals("give")) {
                String message = prefix + (String) massages.get("usage");
                p.sendMessage(message);
            }


        }

        return true;
    }
}
