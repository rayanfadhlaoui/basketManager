package com.basket.manager.mappers;

import com.basket.manager.entities.teams.OffensiveSkillsEntity;
import com.basket.manager.pojos.OffensiveSkills;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper
public interface SkillMapper {
    //    public OffensiveSkills mapOffensiveSkills(OffensiveSkillsEntity offensiveSkillsEntity) {
//        ShootingSkills shootingSkills = new ShootingSkills();
//        OffensiveSkills offensiveSkills = new OffensiveSkills();
//        ShootingSkillsEntity shootingSkillsEntity = offensiveSkillsEntity.getShootingSkills();
//
//        shootingSkills.setMiDistance(shootingSkillsEntity.getMiDistance());
//        shootingSkills.setThreePoint(shootingSkillsEntity.getThreePoint());
//        offensiveSkills.setShootingSkills(shootingSkills);
//        return offensiveSkills;
////    }
//    @Mappings({
//            @Mapping(target = "shootingSkills.miDistance", source = "shootingSkills.miDistance"),
//            @Mapping(target = "shootingSkills.threePoint", source = "shootingSkills.threePoint")
//    })
    OffensiveSkills offensiveEntityToPojo(OffensiveSkillsEntity offensiveSkillsEntity);
}