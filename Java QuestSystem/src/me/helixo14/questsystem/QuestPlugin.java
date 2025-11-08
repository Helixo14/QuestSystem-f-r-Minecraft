package me.helixo14.questsystem;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.helixo14.questsystem.QuestCommand;

public class QuestPlugin extends JavaPlugin implements Listener {
    private QuestManager questManager;
    private QuestGui questGui;

    @Override
    public void onEnable() {
        questManager = new QuestManager();
        questGui = new QuestGui(questManager);

        getCommand("quest").setExecutor(new QuestCommand(questManager, questGui));
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @org.bukkit.event.EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        questGui.handleClick(e);
    }
}
