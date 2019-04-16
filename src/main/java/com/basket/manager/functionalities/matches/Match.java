package com.basket.manager.functionalities.matches;

import com.basket.manager.entities.teams.PlayerPositionEnum;
import com.basket.manager.pojos.*;
import com.basket.manager.utils.RandomUtils;

import java.util.Arrays;

public class Match {

    private static final int QUARTER_TIME = 600;
    private static final int PROLONGATION_TIME = 300;
    private final Team team1;
    private final Team team2;
    private final boolean commentsActivated;
    private Strategy strategy = Strategy.ISOLATION;

    Match(Team team1, Team team2, boolean isCommentsActive) {

        this.team1 = team1;
        this.team2 = team2;
        this.commentsActivated = isCommentsActive;
    }

    public Team getWinner() {
        if (team1.getScore() > team2.getScore()) {
            return team1;
        } else if (team2.getScore() > team1.getScore()) {
            return team2;
        }

        return null;
    }

    public Team getLoser() {
        if (team1.getScore() < team2.getScore()) {
            return team1;
        } else if (team2.getScore() < team1.getScore()) {
            return team2;
        }

        return null;
    }

    public void simulateAll() {
        resetStats();

        int rand = RandomUtils.rand(0, 1);
        if (rand == 1) {
            simulateAll(team1, team2);
        } else {
            simulateAll(team2, team1);
        }

        if (commentsActivated) {
            logEndGame();
        }
    }

    private void resetStats() {
        team1.resetScore();
        team1.getAllPlayers().forEach(p -> p.setStats(new Stats()));
        team2.resetScore();
        team2.getAllPlayers().forEach(p -> p.setStats(new Stats()));
    }

    private void log(String value) {
        if (commentsActivated) {
            System.out.println(value);
        }
    }

    private void simulateAll(Team team1, Team team2) {
        log("");
        log("*******************************");
        log(team1.getName() + " vs " + team2.getName());

        for (int i = 1; i <= 4; i++) {
            simulate(team1, team2, QUARTER_TIME);
        }
        int nbProlongation = 1;
        while (team1.getScore() == team2.getScore()) {
            if (nbProlongation == 1) {
                log("PROLONGATION");
            } else {
                log("PROLONGATION number " + nbProlongation);
            }
            simulate(team1, team2, PROLONGATION_TIME);
            nbProlongation++;
        }
    }

    private void simulate(Team team1, Team team2, int time) {
        while (time != 0) {
            time -= executeStrategy(team1, team2, time);
            if (time != 0) {
                time -= executeStrategy(team2, team1, time);
            }
        }
    }

    private void logEndGame() {
        System.out.println("score final");
        System.out.println(team1.getName() + " :" + team1.getScore());
        System.out.println(team2.getName() + " :" + team2.getScore());

        System.out.println("*********DETAIL***********\n");
        System.out.println(team1.getName());
        Arrays.stream(PlayerPositionEnum.values())
                .filter(p -> p != PlayerPositionEnum.SUBSTITUTE)
                .map(team1::getPlayer)
                .forEach(this::logPlayerStats);
        System.out.println("\n" + team2.getName());

        Arrays.stream(PlayerPositionEnum.values())
                .filter(p -> p != PlayerPositionEnum.SUBSTITUTE)
                .map(team2::getPlayer)
                .forEach(this::logPlayerStats);
    }

    private void logPlayerStats(Player p) {
        System.out.println(p.getFirstName() + " " + p.getLastName() + " " + p.getHeight() + " cm");
        System.out.println(p.getStats());
    }

    private int executeStrategy(Team team1, Team team2, int timeLeft) {
        StrategyResult strategyResult = strategy.execute(team1, team2, timeLeft);
        team1.incrementScore(strategyResult.getScore());
        return strategyResult.getTimeSpent();
    }
}
