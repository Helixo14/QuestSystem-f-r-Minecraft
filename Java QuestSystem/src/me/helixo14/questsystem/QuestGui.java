package me.helixo14.questsystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class QuestGui {
    private final QuestManager questManager;

    public QuestGui(QuestManager questManager) {
        this.questManager = questManager;
    }

    public void open(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "§6Quest-Menü");

        ItemStack questItem = new ItemStack(Material.BOOK);
        ItemMeta meta = questItem.getItemMeta();
        meta.setDisplayName("§aNeue Quest starten");
        meta.setLore(List.of("§7Klicke hier, um eine neue Quest zu erhalten."));
        questItem.setItemMeta(meta);
        gui.setItem(11, questItem);

        ItemStack topItem = new ItemStack(Material.DIAMOND);
        ItemMeta topMeta = topItem.getItemMeta();
        topMeta.setDisplayName("§bBestenliste");
        List<String> lore = new ArrayList<>();
        int rank = 1;
        for (var entry : questManager.getTopPlayers()) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(entry.getKey());
            lore.add("§f" + rank++ + ". §e" + p.getName() + " §7- " + entry.getValue().getCompletedQuests() + " Quests");
        }
        topMeta.setLore(lore);
        topItem.setItemMeta(topMeta);
        gui.setItem(15, topItem);

        player.openInventory(gui);
    }

    public void handleClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;
        if (!e.getView().getTitle().equals("§6Quest-Menü")) return;

        e.setCancelled(true);
        if (e.getSlot() == 11) {
            QuestData data = questManager.getData(player);
            if (data.hasQuest()) {
                player.sendMessage("§cDu hast bereits eine aktive Quest!");
            } else {
                String quest = questManager.getRandomQuest();
                data.setCurrentQuest(quest);
                player.sendMessage("§aNeue Quest: §e" + quest);
            }
            player.closeInventory();
        }
    }
}


