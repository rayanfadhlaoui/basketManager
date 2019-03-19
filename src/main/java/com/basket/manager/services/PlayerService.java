package com.basket.manager.services;

import com.basket.manager.entities.players.PlayerEntity;
import com.basket.manager.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService extends GenericService<PlayerEntity, PlayerRepository> {

    public PlayerService(PlayerRepository playerRepository) {
        super(playerRepository);
    }

    public List<PlayerEntity> gePlayerWithoutTeam() {
        return repository.gePlayerWithoutTeam();
    }
}
