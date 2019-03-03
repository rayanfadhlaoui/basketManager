package com.basket.manager.repositories;

import com.basket.manager.entities.teams.TeamEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepository extends GenericRepository<TeamEntity> {
    @Override
    protected Class<TeamEntity> getClassType() {
        return TeamEntity.class;
    }
}
