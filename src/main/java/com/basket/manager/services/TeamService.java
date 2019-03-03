package com.basket.manager.services;

import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.repositories.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends GenericService<TeamEntity, TeamRepository>{
    TeamService(TeamRepository teamRepository) {
        super(teamRepository);
    }
}
