package com.basket.manager.pojos;

import com.basket.manager.entities.teams.PlayerPositionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    private String name;
    private Map<PlayerPositionEnum, Player> startersByPosition;
    private List<Player> substitues;
    private int score = 0;
    private int reputation;

    public Team(String name) {
        this.name = name;
        startersByPosition = new HashMap<>();
        substitues = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Player getPlayer(PlayerPositionEnum playerPositionEnum) {
        return startersByPosition.get(playerPositionEnum);
    }

    public void incrementScore(int score) {
        this.score += score;
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addPlayer(PlayerPositionEnum playerPositionEnum, Player player) {
        if (playerPositionEnum == PlayerPositionEnum.SUBSTITUTE) {
            substitues.add(player);
        } else {
            startersByPosition.put(playerPositionEnum, player);
        }
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>(startersByPosition.values());
        players.addAll(substitues);
        return players;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public void increaseReputation(int reputation) {
        if (this.reputation + reputation < 0) {
            this.reputation = 0;
        } else {
            this.reputation += reputation;
        }
    }

    public Integer getReputation() {
        return reputation;
    }
}
