package me.helixo14.questsystem;

import org.bukkit.entity.Player;

import java.util.*;

public class QuestManager {
    private final Map<UUID, QuestData> playerData = new HashMap<>();
    private final List<String> quests = List.of(
        "Töte 5 Zombies",
        "Sammle 10 Diamanten",
        "Laufe 1000 Blöcke",
        "Fische einen Fisch",
        "Zähme ein Pferd"
    );

    public QuestData getData(Player player) {
        return playerData.computeIfAbsent(player.getUniqueId(), k -> new QuestData());
    }

    public String getRandomQuest() {
        return quests.get(new Random().nextInt(quests.size()));
    }

    public List<Map.Entry<UUID, QuestData>> getTopPlayers() {
        return playerData.entrySet().stream()
            .sorted((a, b) -> Integer.compare(b.getValue().getCompletedQuests(), a.getValue().getCompletedQuests()))
            .limit(5)
            .toList();
    }
}

