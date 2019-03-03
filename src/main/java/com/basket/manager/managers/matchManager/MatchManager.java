package com.basket.manager.managers.matchManager;

import com.basket.manager.pojos.*;
import com.basket.manager.utils.RandomUtils;

import java.util.List;

public class MatchManager {

    private static final int QUARTER_TIME = 600;
    private final Team team1;
    private final Team team2;
    private final boolean commentsActivated;
    private int currentQuarter = 1;
    private int timeLeft = QUARTER_TIME;
    private Strategy strategy = Strategy.ISOLATION;

    MatchManager(Team team1, Team team2, boolean isCommentsActif) {

        this.team1 = team1;
        this.team2 = team2;
        this.commentsActivated = isCommentsActif;
    }

    public void simulateAll() {
        int rand = RandomUtils.rand(0, 1);
        if (rand == 1) {
            log(team1.getName() + " start ! ");
            simulateAll(team1, team2);
        } else {
            log(team2.getName() + " start ! ");
            simulateAll(team2, team1);
        }

        if (commentsActivated) {
            logEndGame();
        }
    }

    private void log(String value) {
        if (commentsActivated) {
            System.out.println(value);
        }
    }

    private void simulateAll(Team team1, Team team2) {
        while (timeLeft != 0 || currentQuarter != 4) {
            if (0 == timeLeft) {
                currentQuarter++;
                timeLeft = QUARTER_TIME;
            }

            executeStrategy(team1, team2);
            executeStrategy(team2, team1);
        }
    }

    private void logEndGame() {
        System.out.println("score final");
        System.out.println(team1.getName() + " :" + team1.getScore());
        System.out.println(team2.getName() + " :" + team2.getScore());

        System.out.println("*********DETAIL***********");
        System.out.println(team1.getName());
        team1.getAllPlayers().forEach(this::logPlayerStats);
        System.out.println("**************************");
        System.out.println(team2.getName());
        team2.getAllPlayers().forEach(this::logPlayerStats);

    }

    private void logPlayerStats(Player p) {
        System.out.println(p.getFirstName() + " " + p.getLastName());
        System.out.println(p.getStats());
    }

    private void executeStrategy(Team team1, Team team2) {
        if (timeLeft != 0) {
            int possession = timeLeft > 24 ? 24 : timeLeft;
            StrategyResult strategyResult = strategy.execute(team1, team2, possession);
            team1.incrementScore(strategyResult.getScore());
            timeLeft -= strategyResult.getTimeSpent();
        }
    }
}
