package com.basket.manager.repositories;

import com.basket.manager.entities.players.PlayerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerRepository extends GenericRepository<PlayerEntity> {
    @Override
    protected Class<PlayerEntity> getClassType() {
        return PlayerEntity.class;
    }

    public List<PlayerEntity> gePlayerWithoutTeam() {
        return em.createNamedQuery("player.gePlayerWithoutTeam", PlayerEntity.class)
                .getResultList();
    }
}
