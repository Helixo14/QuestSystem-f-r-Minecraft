package me.helixo14.questsystem;

public class QuestData {
    private String currentQuest;
    private int completedQuests;

    public QuestData() {
        this.currentQuest = null;
        this.completedQuests = 0;
    }

    public String getCurrentQuest() {
        return currentQuest;
    }

    public void setCurrentQuest(String quest) {
        this.currentQuest = quest;
    }

    public int getCompletedQuests() {
        return completedQuests;
    }

    public void completeQuest() {
        this.currentQuest = null;
        this.completedQuests++;
    }

    public boolean hasQuest() {
        return currentQuest != null;
    }
}


