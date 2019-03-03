package com.basket.manager.entities.teams;

import com.basket.manager.entities.GenericEntity;
import com.basket.manager.entities.players.PlayerEntity;

import javax.persistence.*;

@Entity(name = "TeamPlayerEntity")
@Table(name = "team_player")
public class TeamPlayerEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_position")
    private PlayerPositionEnum playerPositionEnum;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public PlayerPositionEnum getPlayerPositionEnum() {
        return playerPositionEnum;
    }

    public void setPlayerPositionEnum(PlayerPositionEnum playerPositionEnum) {
        this.playerPositionEnum = playerPositionEnum;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }
}
