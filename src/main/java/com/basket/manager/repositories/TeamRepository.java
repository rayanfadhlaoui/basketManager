package com.basket.manager.repositories;

import com.basket.manager.entities.teams.TeamEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepository extends GenericRepository<TeamEntity> {
    @Override
    protected Class<TeamEntity> getClassType() {
        return TeamEntity.class;
    }

    public List<TeamEntity> getTeamsWithoutPlayers() {
        return em.createNamedQuery("team.getTeamsWithoutPlayers", TeamEntity.class)
                .getResultList();
    }
}
