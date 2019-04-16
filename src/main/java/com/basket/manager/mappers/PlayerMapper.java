package com.basket.manager.mappers;

import com.basket.manager.entities.teams.TeamPlayerEntity;
import com.basket.manager.pojos.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {SkillMapper.class})
public interface PlayerMapper {

    @Mappings({
            @Mapping(target = "offensiveSkills", source = "player.offensiveSkills"),
            @Mapping(target = "reboundingSkills", source = "player.reboundingSkills"),
            @Mapping(target = "physicalSkills", source = "player.physicalSkills"),
            @Mapping(target = "firstName", source = "player.firstName"),
            @Mapping(target = "lastName", source = "player.lastName"),
            @Mapping(target = "age", source = "player.age"),
            @Mapping(target = "height", source = "player.height"),
            @Mapping(target = "technicalSkills", source = "player.technicalSkills"),
            @Mapping(target = "stats", ignore = true)
    })
    Player entityToPojo(TeamPlayerEntity teamPlayerEntity);
}
