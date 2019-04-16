package com.basket.manager.entities.players.skills;

import com.basket.manager.entities.GenericEntity;

import javax.persistence.*;

@Entity(name = "PhysicalSkillsEntity")
@Table(name = "physical_skills")
public class PhysicalSkillsEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int strength;

    @Column(name = "standing_vertical_jump")
    private int standingVerticalJump;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStandingVerticalJump() {
        return standingVerticalJump;
    }

    public void setStandingVerticalJump(int standingVerticalJump) {
        this.standingVerticalJump = standingVerticalJump;
    }
}
