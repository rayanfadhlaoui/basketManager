package com.basket.manager.pojos;

public class StrategyResult {
    private int timeSpent;
    private int score;

    public StrategyResult(int timeSpent) {
        this.timeSpent = timeSpent;
        score = 0;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
