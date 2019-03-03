package com.basket.manager.repositories;

import com.basket.manager.entities.players.NameEntity;
import org.springframework.stereotype.Repository;

@Repository
public class NamesRepository extends GenericRepository<NameEntity> {
    @Override
    protected Class<NameEntity> getClassType() {
        return NameEntity.class;
    }
}
