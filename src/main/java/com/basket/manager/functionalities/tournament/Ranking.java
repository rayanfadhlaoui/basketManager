package com.basket.manager.functionalities.tournament;

import com.basket.manager.pojos.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Ranking {

    private Map<RankingEnum, List<Team>> teamsByRanking = new HashMap<>();
    private RankingEnum currentRound;
    private Team winner;

    public Team getWinner() {
        return winner;
    }

    void update(int nbMatch) {
        switch (nbMatch) {
            case 16:
                currentRound = RankingEnum.ROUND_32;
                break;
            case 8:
                currentRound = RankingEnum.LAST_SIXTEEN;
                break;
            case 4:
                currentRound = RankingEnum.QUARTER_FINAL;
                break;
            case 2:
                currentRound = RankingEnum.SEMI_FINAL;
                break;
            case 1:
                currentRound = RankingEnum.FINAL;
                break;
            default:
                currentRound = RankingEnum.NOT_RANKED;
        }
    }

    void addTeam(Team team) {
        teamsByRanking.putIfAbsent(currentRound, new ArrayList<>());
        List<Team> teams = teamsByRanking.get(currentRound);
        teams.add(team);
    }

    void setWinner(Team team) {
        this.winner = team;
    }

    RankingEnum getCurrentRound() {
        return currentRound;
    }

    public Map<RankingEnum, List<Team>> getTeamsByRanking() {
        return teamsByRanking;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        BiFunction<RankingEnum, List<Team>, List<Team>> biFunction = (r, teams) -> {
            sb.append(r).append("\n");

            String teamsAsString = teams.stream().map(Team::getName).collect(Collectors.joining(", "));
            sb.append(teamsAsString).append("\n");
            return teams;
        };
        teamsByRanking.computeIfPresent(RankingEnum.LAST_SIXTEEN, biFunction);
        teamsByRanking.computeIfPresent(RankingEnum.QUARTER_FINAL, biFunction);
        teamsByRanking.computeIfPresent(RankingEnum.SEMI_FINAL, biFunction);
        teamsByRanking.computeIfPresent(RankingEnum.FINAL, biFunction);
        sb.append("WINNER").append("\n").append(winner.getName());
        return sb.toString();
    }
}
