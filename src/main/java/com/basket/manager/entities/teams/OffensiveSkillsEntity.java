package com.basket.manager.entities.teams;

import com.basket.manager.entities.GenericEntity;

import javax.persistence.*;

@Entity(name = "OffensiveSkillsEntity")
@Table(name = "offensive_skills")
public class OffensiveSkillsEntity implements GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shooting_skills_id")
    private ShootingSkillsEntity shootingSkills;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ShootingSkillsEntity getShootingSkills() {
        return shootingSkills;
    }

    public void setShootingSkills(ShootingSkillsEntity shootingSkills) {
        this.shootingSkills = shootingSkills;
    }
}
