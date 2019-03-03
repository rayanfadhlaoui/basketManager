package com.basket.manager.repositories;

import com.basket.manager.entities.players.PlayerEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository extends GenericRepository<PlayerEntity> {
    @Override
    protected Class<PlayerEntity> getClassType() {
        return PlayerEntity.class;
    }
}
