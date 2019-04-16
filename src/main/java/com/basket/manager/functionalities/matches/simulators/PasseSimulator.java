package com.basket.manager.functionalities.matches.simulators;

import com.basket.manager.entities.teams.PlayerPositionEnum;
import com.basket.manager.pojos.PasseResult;
import com.basket.manager.pojos.Player;
import com.basket.manager.pojos.Team;
import com.basket.manager.utils.RandomUtils;

public class PasseSimulator {
    public PasseResult simulatePasse(Player player, Team team) {
        PasseResult passeResult = new PasseResult();
        Player receiver;
        int playerId = player.getId();
        if (playerId == team.getPlayer(PlayerPositionEnum.POINT_GARD).getId()) {
            receiver = RandomUtils.rand(0, 1) == 0 ? team.getPlayer(PlayerPositionEnum.SHOOTING_GARD) :
                    team.getPlayer(PlayerPositionEnum.POWER_FORWARD);
        } else if (playerId == team.getPlayer(PlayerPositionEnum.POWER_FORWARD).getId()) {
            receiver = team.getPlayer(PlayerPositionEnum.CENTER);
        } else if (playerId == team.getPlayer(PlayerPositionEnum.POWER_FORWARD).getId()) {
            receiver = team.getPlayer(PlayerPositionEnum.CENTER);
        } else if (playerId == team.getPlayer(PlayerPositionEnum.SHOOTING_GARD).getId()) {
            receiver = team.getPlayer(PlayerPositionEnum.POWER_FORWARD);
        } else if (playerId == team.getPlayer(PlayerPositionEnum.POWER_FORWARD).getId()) {
            receiver = RandomUtils.rand(0, 1) == 0 ? team.getPlayer(PlayerPositionEnum.POINT_GARD) :
                    team.getPlayer(PlayerPositionEnum.SHOOTING_GARD);
        } else {
            receiver = RandomUtils.rand(0, 1) == 0 ? team.getPlayer(PlayerPositionEnum.POINT_GARD) :
                    team.getPlayer(PlayerPositionEnum.SMALL_FORWARD);
        }

        passeResult.setPasseResultEnum(PasseResultEnum.SUCCESS);
        passeResult.setReceiver(receiver);

        return passeResult;
    }
}
