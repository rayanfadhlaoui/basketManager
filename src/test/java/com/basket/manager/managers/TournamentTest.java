package com.basket.manager.managers;

import com.basket.manager.FileUtils;
import com.basket.manager.Tournament;
import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.mappers.PlayerMapper;
import com.basket.manager.mappers.TeamMapper;
import com.basket.manager.pojos.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TournamentTest {

    private static List<TeamEntity> teamEntities;
    PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);

    private TeamMapper teamMapper = new TeamMapper(playerMapper);

    {
        ObjectMapper mapper = new ObjectMapper();
        File file = FileUtils.getFile("teams.json");
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, TeamEntity.class);
        try {
            teamEntities = mapper.readValue(file, collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        assertThat(teamEntities).hasSize(32);
        List<Team> teams = teamMapper.entitiesToPojos(teamEntities);
        Tournament tournament = new Tournament(teams, true);
        tournament.simulateAll();
        assertThat(tournament.getRanking().getWinner()).isNotNull();
    }
}
