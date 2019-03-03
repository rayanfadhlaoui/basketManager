package com.basket.manager.services;

import com.basket.manager.entities.players.NameEntity;
import com.basket.manager.repositories.NamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamesService {

    @Autowired
    private NamesRepository namesRepository;

    public List<NameEntity> getAllNames() {
        return namesRepository.getAll();
    }
}
