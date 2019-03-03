package com.basket.manager.mappers;

import com.basket.manager.entities.teams.OffensiveSkillsEntity;
import com.basket.manager.entities.teams.ShootingSkillsEntity;
import com.basket.manager.pojos.OffensiveSkills;
import com.basket.manager.pojos.ShootingSkills;

public class SkillMapper {
    public OffensiveSkills mapOffensiveSkills(OffensiveSkillsEntity offensiveSkillsEntity) {
        ShootingSkills shootingSkills = new ShootingSkills();
        OffensiveSkills offensiveSkills = new OffensiveSkills();
        ShootingSkillsEntity shootingSkillsEntity = offensiveSkillsEntity.getShootingSkills();

        shootingSkills.setMiDistance(shootingSkillsEntity.getMiDistance());
        shootingSkills.setThreePoint(shootingSkillsEntity.getThreePoint());
        offensiveSkills.setShootingSkills(shootingSkills);
        return offensiveSkills;
    }
}
