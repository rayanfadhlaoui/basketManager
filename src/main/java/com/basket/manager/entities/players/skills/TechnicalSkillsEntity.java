package com.basket.manager.entities.players.skills;

import com.basket.manager.entities.GenericEntity;

import javax.persistence.*;

@Entity(name = "TechnicalSkillsEntity")
@Table(name = "technical_skills")
public class TechnicalSkillsEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int dribble;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {

    }

    public int getDribble() {
        return dribble;
    }

    public void setDribble(int dribble) {
        this.dribble = dribble;
    }
}
