package me.helixo14.questsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestCommand implements CommandExecutor {
    private final QuestManager questManager;
    private final QuestGui questGui;

    public QuestCommand(QuestManager questManager, QuestGui questGui) {
        this.questManager = questManager;
        this.questGui = questGui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            questGui.open(player);
        }
        return true;
    }
}


