package com.basket.manager.mappers;

import com.basket.manager.entities.players.PlayerEntity;
import com.basket.manager.entities.teams.TeamPlayerEntity;
import com.basket.manager.pojos.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {SkillMapper.class})
public interface PlayerMapper {

    Player entityToPojo(PlayerEntity playerEntity);

    @Mappings({
            @Mapping(target = "offensiveSkills", source = "playerEntity.offensiveSkills"),
            @Mapping(target = "firstName", source = "playerEntity.firstName"),
            @Mapping(target = "lastName", source = "playerEntity.lastName"),
            @Mapping(target = "age", source = "playerEntity.age"),
            @Mapping(target = "height", source = "playerEntity.height")
    })
    Player entityToPojo(TeamPlayerEntity teamPlayerEntity);
}
