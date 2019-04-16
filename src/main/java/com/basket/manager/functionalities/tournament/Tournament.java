package com.basket.manager.functionalities.tournament;

import com.basket.manager.functionalities.matches.Match;
import com.basket.manager.functionalities.matches.MatchBuilder;
import com.basket.manager.pojos.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {
    private boolean withComments;
    private List<Match> matches;
    private List<Team> qualifiedTeams;
    private Ranking ranking;

    public Tournament(List<Team> teams, boolean withComments) {
        this.withComments = withComments;
        this.matches = new ArrayList<>();
        teams.forEach(Team::resetScore);
        this.qualifiedTeams = new ArrayList<>(teams);
        this.ranking = new Ranking();
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void simulateAll() {
        simulate();

        log(ranking.toString());
    }

    private void simulate() {
        createMatches();

        ranking.update(matches.size());

        log("\n" + ranking.getCurrentRound() + "\n");
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

    private void log(String value) {
        if (withComments) {
            System.out.println(value);
        }
    }

    private void createMatches() {
        matches = new ArrayList<>();

        Collections.shuffle(qualifiedTeams);

        for (int i = 0; i < qualifiedTeams.size(); i += 2) {
            Match match = MatchBuilder.init(qualifiedTeams.get(i), qualifiedTeams.get(i + 1))
                    .withComments(withComments)
                    .get();
            matches.add(match);
        }
        qualifiedTeams = new ArrayList<>();
    }
}
