package com.basket.manager.mappers;

import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.pojos.Player;
import com.basket.manager.pojos.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    private final PlayerMapper playerMapper;

    public TeamMapper(final PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    public Team entityToPojo(TeamEntity teamEntity) {
        Team team = new Team(teamEntity.getName());
        teamEntity.getTeamPlayers()
                .forEach(p -> {
                    Player player = playerMapper.entityToPojo(p);
                    team.addPlayer(p.getPlayerPositionEnum(), player);
                });
        return team;
    }

    public List<Team> entitiesToPojos(List<TeamEntity> teamEntities) {
        return teamEntities.stream()
                .map(this::entityToPojo)
                .peek(team -> team.setReputation(0))
                .collect(Collectors.toList());
    }
}
