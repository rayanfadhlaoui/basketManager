package com.basket.manager.entities.teams;

import com.basket.manager.entities.GenericEntity;

import javax.persistence.*;

@Entity(name = "ShootingSkillsEntity")
@Table(name = "shooting_skills")
public class ShootingSkillsEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mi_distance")
    private int miDistance;

    @Column(name = "three_point")
    private int threePoint;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getMiDistance() {
        return miDistance;
    }

    public void setMiDistance(int miDistance) {
        this.miDistance = miDistance;
    }

    public int getThreePoint() {
        return threePoint;
    }

    public void setThreePoint(int threePoint) {
        this.threePoint = threePoint;
    }
}

