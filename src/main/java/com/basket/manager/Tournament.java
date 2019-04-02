package com.basket.manager;

import com.basket.manager.managers.match.MatchManager;
import com.basket.manager.managers.match.MatchManagerBuilder;
import com.basket.manager.pojos.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {
    private List<Team> teams;
    private boolean withComments;
    private List<MatchManager> matches;
    private List<Team> qualifiedTeams;
    private Ranking ranking;

    public Tournament(List<Team> teams, boolean withComments) {
        this.teams = teams;
        this.withComments = withComments;
        this.matches = new ArrayList<>();
        this.qualifiedTeams = new ArrayList<>(teams);
        this.ranking = new Ranking();
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void simulateAll() {
        simulate();

        System.out.println(ranking);
    }

    private void simulate() {
        createMatches();

        ranking.update(matches.size());

        System.out.println("\n" + ranking.getCurrentRound() + "\n");

        matches.forEach(m -> {
            m.simulateAll();
            qualifiedTeams.add(m.getWinner());
            ranking.addTeam(m.getLoser());
        });

        if (qualifiedTeams.size() != 1) {
            simulate();
        } else {
            ranking.setWinner(qualifiedTeams.get(0));
        }
    }

    private void createMatches() {
        matches = new ArrayList<>();

        Collections.shuffle(qualifiedTeams);

        for (int i = 0; i < qualifiedTeams.size(); i += 2) {
            MatchManager matchManager = MatchManagerBuilder.init(qualifiedTeams.get(i), qualifiedTeams.get(i + 1))
                    .withComments(withComments)
                    .get();
            matches.add(matchManager);
        }
        qualifiedTeams = new ArrayList<>();
    }
}
