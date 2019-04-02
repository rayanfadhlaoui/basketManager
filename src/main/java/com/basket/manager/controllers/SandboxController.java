package com.basket.manager.controllers;

import com.basket.manager.RosterOrganizer;
import com.basket.manager.entities.players.NameEntity;
import com.basket.manager.entities.players.NameTypeEnum;
import com.basket.manager.entities.players.PlayerEntity;
import com.basket.manager.entities.teams.PlayerPositionEnum;
import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.entities.teams.TeamPlayerEntity;
import com.basket.manager.factories.PlayerFactory;
import com.basket.manager.factories.RandomObjectSupplier;
import com.basket.manager.services.NamesService;
import com.basket.manager.services.PlayerService;
import com.basket.manager.services.TeamService;
import com.basket.manager.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sandbox")
public class SandboxController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private NamesService namesService;


    @RequestMapping(value = "/recrutePlayer", method = RequestMethod.GET)
    @Transactional
    public List<PlayerEntity> createPlayers() {

        List<PlayerEntity> playerEntities = playerService.gePlayerWithoutTeam();
        List<TeamEntity> teamEntities = teamService.getTeamsWithoutPlayers();
        int indiceTeam = 0;
        TeamEntity currentTeam = teamEntities.get(indiceTeam);
        int count = 0;
        List<PlayerEntity> collect = playerEntities.stream()
                .sorted((o1, o2) -> 1 - RandomUtils.rand(0, 2))
                .collect(Collectors.toList());
        for (PlayerEntity playerEntity : collect) {
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
        return collect;
    }

    @RequestMapping(value = "/showAllTeams", method = RequestMethod.GET)
    @Transactional
    public List<TeamEntity> showAllTeams() {
        return teamService.getAll();
    }


    @RequestMapping(value = "/reorganize", method = RequestMethod.GET)
    @Transactional
    public List<TeamEntity> reorganize() {
        List<TeamEntity> teamEntities = teamService.getAll();
        RosterOrganizer rosterOrganizer = new RosterOrganizer();
        teamEntities.forEach(rosterOrganizer::reorganize);
        teamService.save(teamEntities);
        return teamEntities;
    }


    @RequestMapping(value = "/createPlayer", method = RequestMethod.GET)
    @Transactional
    public void updatePlayers() {
        List<NameEntity> nameEntities = namesService.getAll();
        List<String> firstNames = nameEntities.stream()
                .filter(n -> n.getNameType() == NameTypeEnum.FIRST_NAME)
                .map(NameEntity::getValue)
                .collect(Collectors.toList());
        List<String> lastNames = nameEntities.stream()
                .filter(n -> n.getNameType() == NameTypeEnum.LAST_NAME)
                .map(NameEntity::getValue)
                .collect(Collectors.toList());
        PlayerFactory playerFactory = new PlayerFactory(new RandomObjectSupplier<>(firstNames), new RandomObjectSupplier<>(lastNames));
        List<PlayerEntity> playerEntities = playerFactory.create(300);
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
