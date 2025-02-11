package com.protesq.protesqpy;

public class Topic {
    private String title;
    private String description;
    private boolean isUnlocked;
    private boolean isCompleted;
    private boolean isFavorite;

    public Topic(String title, String description, boolean isUnlocked) {
        this.title = title;
        this.description = description;
        this.isUnlocked = isUnlocked;
        this.isCompleted = false;
        this.isFavorite = false;
    }

    // Getter ve Setter metodları
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isUnlocked() { return isUnlocked; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
    public void setUnlocked(boolean unlocked) { isUnlocked = unlocked; }
    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
} 