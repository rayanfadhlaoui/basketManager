package com.basket.manager.builders;

import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.mappers.PlayerMapper;
import com.basket.manager.pojos.Player;
import com.basket.manager.pojos.Team;

public class TeamBuilder {
    private TeamEntity teamEntity;
    private final PlayerMapper playerMapper;

    public TeamBuilder(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
        playerMapper = new PlayerMapper();
    }

    public static TeamBuilder init(TeamEntity teamEntity) {
        return new TeamBuilder(teamEntity);
    }

    public Team get() {
        Team team = new Team(teamEntity.getName());
        teamEntity.getTeamPlayers()
                .forEach(p -> {
                    Player player = playerMapper.map(p);
                    team.addPlayer(p.getPlayerPositionEnum(), player);
                });
        return team;
    }
}
