package com.basket.manager.controllers;

import com.basket.manager.entities.players.PlayerEntity;
import com.basket.manager.entities.teams.*;
import com.basket.manager.services.PlayerService;
import com.basket.manager.services.TeamService;
import com.basket.manager.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/sandbox")
public class SandboxController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

//    @RequestMapping(value = "/recrutePlayer", method = RequestMethod.GET)
    @Transactional
    public TeamService createPlayers() {

        List<PlayerEntity> playerEntities = playerService.getAll();
        List<TeamEntity> teamEntities = teamService.getAll();
        int indiceTeam = 0;
        TeamEntity currentTeam = teamEntities.get(indiceTeam);
        int count = 0;
        for (PlayerEntity playerEntity : playerEntities) {
            currentTeam.addTeamPlayer(createTeamPlayer(playerEntity, count));
            count++;
            if (count % 16 == 0) {
                indiceTeam++;
                currentTeam = teamEntities.get(indiceTeam);
                if (currentTeam == null) {
                    break;
                }
                count = 0;
            }
        }

        teamService.save(teamEntities);
        return teamService;
    }

    @RequestMapping(value = "/showAllTeams", method = RequestMethod.GET)
    @Transactional
    public List<TeamEntity> showAllTeams() {
        return teamService.getAll();
    }


    @RequestMapping(value = "/updatePlayers", method = RequestMethod.GET)
    @Transactional
    public void updatePlayers() {

        List<PlayerEntity> playerEntities = playerService.getAll();
        playerEntities.forEach(p -> {
            OffensiveSkillsEntity offensiveSkills = new OffensiveSkillsEntity();
            ShootingSkillsEntity shootingSkills = new ShootingSkillsEntity();
            shootingSkills.setMiDistance(RandomUtils.rand(1,30));
            shootingSkills.setThreePoint(RandomUtils.rand(1,20));
            offensiveSkills.setShootingSkills(shootingSkills);
            p.setOffensiveSkills(offensiveSkills);
        });

        playerService.save(playerEntities);
    }

    @RequestMapping(value = "/getAllTeams", method = RequestMethod.GET)
    @Transactional
    public List<TeamEntity> getAllTeams() {

        List<TeamEntity> teamEntities = teamService.getAll();
        return teamEntities;
    }

    private TeamPlayerEntity createTeamPlayer(PlayerEntity playerEntity, int count) {
        TeamPlayerEntity teamPlayerEntity = new TeamPlayerEntity();
        teamPlayerEntity.setPlayerEntity(playerEntity);
        teamPlayerEntity.setPlayerPositionEnum(getTeamPlayerEntityPosition(count));
        return teamPlayerEntity;
    }

    private PlayerPositionEnum getTeamPlayerEntityPosition(int count) {
        switch (count) {
            case 0:
                return PlayerPositionEnum.POINT_GARD;
            case 1:
                return PlayerPositionEnum.SHOOTING_GARD;
            case 2:
                return PlayerPositionEnum.SMALL_FORWARD;
            case 3:
                return PlayerPositionEnum.POWER_FORWARD;
            case 4:
                return PlayerPositionEnum.CENTER;
            default:
                return PlayerPositionEnum.SUBSTITUTE;
        }
    }
}
