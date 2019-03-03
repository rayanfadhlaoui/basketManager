package com.basket.manager.pojos;

import com.basket.manager.entities.teams.PlayerPositionEnum;
import com.basket.manager.utils.RandomUtils;

public class Strategy {
    public static final Strategy ISOLATION = new Strategy();
    public static final int HIGHSCHOOL_3_POINT = 606;

    public StrategyResult execute(Team team1, Team team2, int timeLeft) {
        StrategyResult strategyResult = new StrategyResult(RandomUtils.rand(1, timeLeft));

        Player randomPlayer = getRandomPlayer(team1);
        int rand = RandomUtils.rand(0, 1);
        int pointsScored = 0;
        Stats stats = randomPlayer.getStats();
        boolean success = false;

        if(rand == 0) {
            if(shoot(randomPlayer, new Field(HIGHSCHOOL_3_POINT, true))) {
                pointsScored = 3;
                success = true;
            }
            stats.shoot3Points(success);
        }
        else {
            if(shoot(randomPlayer, new Field(450, false))) {
                pointsScored = 2;
                success = true;
            }
            stats.shootMiDistance(success);
        }
        strategyResult.setScore(pointsScored);
        return strategyResult;
    }

    private boolean shoot(Player player, Field field) {
        ShootingSkills shootingSkills = player.getOffensiveSkills().getShootingSkills();
        int probability;
        if(field.is3Point()) {
            probability = shootingSkills.get3Points();
        }
        else {
            probability = shootingSkills.getMiDistance();
        }

        return RandomUtils.rand(0, 100) <= probability;
    }

    private Player getRandomPlayer(Team team) {
        int rand = RandomUtils.rand(0, 4);
        Player player = null;
        switch (rand) {
            case 0:
                player = team.getPlayer(PlayerPositionEnum.POINT_GARD);
                break;
            case 1:
                player = team.getPlayer(PlayerPositionEnum.SHOOTING_GARD);
                break;
            case 2:
                player = team.getPlayer(PlayerPositionEnum.SMALL_FORWARD);
                break;
            case 3:
                player = team.getPlayer(PlayerPositionEnum.POWER_FORWARD);
                break;
            case 4:
                player = team.getPlayer(PlayerPositionEnum.CENTER);
                break;
        }
        return player;
    }
}
