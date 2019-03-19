package com.basket.manager.services;

import com.basket.manager.entities.players.NameEntity;
import com.basket.manager.entities.players.PlayerEntity;
import com.basket.manager.repositories.NamesRepository;
import com.basket.manager.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamesService extends GenericService<NameEntity, NamesRepository> {

    public NamesService(NamesRepository namesRepository) {
        super(namesRepository);
    }
}
