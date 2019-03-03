package com.basket.manager.pojos;

import com.basket.manager.entities.teams.PlayerPositionEnum;

import java.util.*;
import java.util.stream.Stream;

public class Team {
    private String name;
    private Map<PlayerPositionEnum, Player> startersByPosition;
    private List<Player> substitues;
    private int score = 0;

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
}
