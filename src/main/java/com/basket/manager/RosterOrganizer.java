package com.basket.manager;

import com.basket.manager.entities.teams.PlayerPositionEnum;
import com.basket.manager.entities.teams.ShootingSkillsEntity;
import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.entities.teams.TeamPlayerEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RosterOrganizer {

    public void reorganize(TeamEntity team) {

        List<TeamPlayerEntity> starter = team.getTeamPlayers()
                .stream()
                .sorted(Comparator.comparing(this::getPlayerLevel).reversed())
                .peek(t -> t.setPlayerPositionEnum(PlayerPositionEnum.SUBSTITUTE))
                .collect(Collectors.toList());

        starter.get(0).setPlayerPositionEnum(PlayerPositionEnum.POINT_GARD);
        starter.get(1).setPlayerPositionEnum(PlayerPositionEnum.SHOOTING_GARD);
        starter.get(2).setPlayerPositionEnum(PlayerPositionEnum.SMALL_FORWARD);
        starter.get(3).setPlayerPositionEnum(PlayerPositionEnum.POWER_FORWARD);
        starter.get(4).setPlayerPositionEnum(PlayerPositionEnum.CENTER);
    }

    private Integer getPlayerLevel(TeamPlayerEntity teamPlayerEntity) {
        ShootingSkillsEntity shootingSkills = teamPlayerEntity.getPlayerEntity().getOffensiveSkills().getShootingSkills();
        return shootingSkills.getMiDistance() * 2 + shootingSkills.getThreePoint() * 3;
    }
}
