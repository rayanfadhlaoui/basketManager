package com.basket.manager.managers;

import com.basket.manager.builders.TeamBuilder;
import com.basket.manager.entities.teams.TeamEntity;
import com.basket.manager.managers.match.MatchManager;
import com.basket.manager.managers.match.MatchManagerBuilder;
import com.basket.manager.pojos.Team;
import com.basket.manager.utils.RandomUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TournamentManagerTest {

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

    }
}
