package com.basket.manager.entities.players.skills;

import com.basket.manager.entities.GenericEntity;

import javax.persistence.*;

@Entity(name = "ReboundingSkillsEntity")
@Table(name = "rebounding_skills")
public class ReboundingSkillsEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "position_technics")
    private int positionTechnics;

    private int timing;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public int getPositionTechnics() {
        return positionTechnics;
    }

    public void setPositionTechnics(int positionTechnics) {
        this.positionTechnics = positionTechnics;
    }
}
