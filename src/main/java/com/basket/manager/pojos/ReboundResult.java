package com.basket.manager.pojos;

public class ReboundResult {
    private Player rebounder;
    private boolean offensiveHasRebound;

    public boolean offenseHasRebound() {
        return offensiveHasRebound;
    }

    public Player getRebounder() {
        return rebounder;
    }

    public void setRebounder(Player rebounder) {
        this.rebounder = rebounder;
    }

    public void setOffensiveHasRebound(boolean offensiveHasRebound) {
        this.offensiveHasRebound = offensiveHasRebound;
    }
}
