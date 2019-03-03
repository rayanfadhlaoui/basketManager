package com.basket.manager.factories;

import com.basket.manager.entities.players.PlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PlayerFactory {

    private final Supplier<String> firstNameSupplier;
    private final Supplier<String> lastNameSupplier;
    private final Supplier<Integer> heightSupplier;

    public PlayerFactory(Supplier<String> firstNameSupplier, Supplier<String> lastNameSupplier) {
        this.firstNameSupplier = firstNameSupplier;
        this.lastNameSupplier = lastNameSupplier;
        heightSupplier = new HeightSupplier();
    }

    public List<PlayerEntity> create(int nbPlayers) {
        List<PlayerEntity> playerEntities = new ArrayList<>();
        for (int i = 0; i < nbPlayers; i++) {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setFirstName(firstNameSupplier.get());
            playerEntity.setLastName(lastNameSupplier.get());
            playerEntity.setAge(11);
            playerEntity.setHeight(heightSupplier.get());
            playerEntities.add(playerEntity);
        }
        return playerEntities;
    }
}
