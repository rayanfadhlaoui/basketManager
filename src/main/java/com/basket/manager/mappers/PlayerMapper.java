package com.basket.manager.mappers;

import com.basket.manager.entities.players.PlayerEntity;
import com.basket.manager.entities.teams.TeamPlayerEntity;
import com.basket.manager.pojos.OffensiveSkills;
import com.basket.manager.pojos.Player;
import com.basket.manager.pojos.Stats;

public class PlayerMapper {

    private SkillMapper skillMapper;

    public PlayerMapper() {
        skillMapper = new SkillMapper();
    }

    public Player map(TeamPlayerEntity teamPlayer) {
        Player player = new Player();
        mapGeneralInformation(teamPlayer, player);
        OffensiveSkills offensiveSkills = skillMapper.mapOffensiveSkills(teamPlayer.getPlayerEntity().getOffensiveSkills());
        player.setOffensiveSkills(offensiveSkills);
        player.setStats(new Stats());
        return player;
    }

    private void mapGeneralInformation(TeamPlayerEntity teamPlayer, Player player) {
        PlayerEntity playerEntity = teamPlayer.getPlayerEntity();
        player.setFirstName(playerEntity.getFirstName());
        player.setLastName(playerEntity.getLastName());
        player.setAge(playerEntity.getAge());
        player.setHeight(playerEntity.getHeight());
    }
}
