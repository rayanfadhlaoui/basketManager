package com.basket.manager.managers;

import com.basket.manager.FileUtils;
import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.functionalities.tournament.Ranking;
import com.basket.manager.functionalities.tournament.RankingEnum;
import com.basket.manager.functionalities.tournament.Tournament;
import com.basket.manager.mappers.PlayerMapper;
import com.basket.manager.mappers.TeamMapper;
import com.basket.manager.pojos.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        for (int i = 0; i < 200; i++) {
            Tournament tournament = new Tournament(teams, false);
            tournament.simulateAll();
            updateRanking(tournament.getRanking());
        }

        prettyPrintTeams(teams);
    }

    private void updateRanking(Ranking ranking) {
        Map<RankingEnum, List<Team>> teamsByRanking = ranking.getTeamsByRanking();
        teamsByRanking.forEach(this::updateTeamReputation);
        ranking.getWinner().increaseReputation(16);
    }

    private void updateTeamReputation(RankingEnum rankingEnum, List<Team> teams) {
        switch (rankingEnum) {
            case NOT_RANKED:
            case ROUND_32:
                teams.forEach(team -> team.increaseReputation(-2));
                break;
            case LAST_SIXTEEN:
                teams.forEach(team -> team.increaseReputation(1));
                break;
            case QUARTER_FINAL:
                teams.forEach(team -> team.increaseReputation(2));
                break;
            case SEMI_FINAL:
                teams.forEach(team -> team.increaseReputation(4));
                break;
            case FINAL:
                teams.forEach(team -> team.increaseReputation(8));
                break;
        }
    }

    private void prettyPrintTeams(List<Team> teams) {

        AtomicInteger i = new AtomicInteger(1);
        teams.stream()
                .sorted(Comparator.comparing(Team::getReputation).reversed())
                .forEach(t -> System.out.println(i.getAndIncrement() + " -> " + t.getName() + " " + t.getReputation()));

    }
}
