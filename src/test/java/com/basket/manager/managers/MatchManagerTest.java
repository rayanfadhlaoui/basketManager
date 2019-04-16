package com.basket.manager.managers;

import com.basket.manager.entities.teams.TeamEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MatchManagerTest {

    private static List<TeamEntity> teamEntities;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            teamEntities = mapper.readValue(new File("C://Users/rayan/Desktop/projet baslet manager/data/teams.json"), mapper.getTypeFactory().constructCollectionType(List.class, TeamEntity.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
//        Team team1 = TeamBuilder.init(teamEntities.get(0))
//                .get();
//        Team team2 = TeamBuilder.init(teamEntities.get(1))
//                .get();
//
//        MatchManager matchManager = MatchBuilder.init(team1, team2)
//                .withComments()
//                .get();
//
//        matchManager.simulateAll();
    }
}
