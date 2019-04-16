package com.basket.manager.functionalities.matches;

import com.basket.manager.pojos.Team;

public class MatchBuilder {
    private final Team team1;
    private final Team team2;
    private boolean isCommentsActif = false;

    private MatchBuilder(Team team1, Team team2) {

        this.team1 = team1;
        this.team2 = team2;
    }

    public static MatchBuilder init(Team team1, Team team2) {
        return new MatchBuilder(team1, team2);
    }

    public MatchBuilder withComments(boolean withComments) {
        isCommentsActif = withComments;
        return this;
    }

    public Match get() {
        return new Match(team1, team2, isCommentsActif);
    }
}
