package com.basket.manager.services;

import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService extends GenericService<TeamEntity, TeamRepository>{
    TeamService(TeamRepository teamRepository) {
        super(teamRepository);
    }

    public List<TeamEntity> getTeamsWithoutPlayers() {
        return repository.getTeamsWithoutPlayers();
    }
}
