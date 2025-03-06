package PolitGame.TheFandik.Service;

import PolitGame.TheFandik.ConfigData;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.List;

public class UpgradeRewards {
    public static boolean hasRequiredItems(Player player, List<String> paywall) {
        for (String item : paywall) {
            String[] parts = item.split(":");
            Material material = Material.getMaterial(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            if (material != null && player.getInventory().contains(material, amount)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void removeRequiredItems(Player player, List<String> paywall) {
        for (String item : paywall) {
            String[] parts = item.split(":");
            Material material = Material.getMaterial(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            if (material != null) {
                player.getInventory().removeItem(new ItemStack(material, amount));
            }
        }
    }

    public static void giveReward(Player player, int level) {
        String rewardPath = "rewards.reward" + level + ".items.";
        File rewardsFile = ConfigData.loadReward();
        YamlConfiguration rewards = YamlConfiguration.loadConfiguration(rewardsFile);
        if (rewards.contains(rewardPath)) {
            int itemIndex = 1;
            while (rewards.contains(rewardPath + "item" + itemIndex)) {
                String typeString = rewards.getString(rewardPath + "item" + itemIndex + ".type");
                Material material = Material.getMaterial(typeString);

                if (material != null) {
                    ItemStack reward = new ItemStack(material);

                    String itemName = rewards.getString(rewardPath + "item" + itemIndex + ".name");
                    if (itemName != null) {
                        ItemMeta meta = reward.getItemMeta();
                        if (meta != null) {
                            meta.setDisplayName(itemName);
                            reward.setItemMeta(meta);
                        }
                    }
                    player.getInventory().addItem(reward);
                }
                itemIndex++;
            }
        }
    }

}
