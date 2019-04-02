package com.basket.manager.managers.match;

import com.basket.manager.pojos.Team;

public class MatchManagerBuilder {
    private final Team team1;
    private final Team team2;
    private boolean isCommentsActif = false;

    private MatchManagerBuilder(Team team1, Team team2) {

        this.team1 = team1;
        this.team2 = team2;
    }

    public static MatchManagerBuilder init(Team team1, Team team2) {
        return new MatchManagerBuilder(team1, team2);
    }

    public MatchManagerBuilder withComments(boolean withComments) {
        isCommentsActif = withComments;
        return this;
    }

    public MatchManager get() {
        return new MatchManager(team1, team2, isCommentsActif);
    }
}
