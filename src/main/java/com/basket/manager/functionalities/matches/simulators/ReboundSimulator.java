package com.basket.manager.functionalities.matches.simulators;

import com.basket.manager.entities.teams.PlayerPositionEnum;
import com.basket.manager.pojos.Player;
import com.basket.manager.pojos.ReboundResult;
import com.basket.manager.pojos.Team;
import com.basket.manager.utils.RandomUtils;

public class ReboundSimulator {

    public static final double RATIO_STANDING_VERTICAL_JUMP = 1.16;

    public ReboundResult simulate(Team offensiveTeam, Team defensiveTeam) {
        Player offensivePlayer = getRandomPlayer(offensiveTeam);
        Player defensivePlayer = getRandomPlayer(defensiveTeam);
        Player rebounder = defensivePlayer;
        boolean offensiveHasRebound = false;

        boolean offensiveHasPosition = offensiveHasPosition(offensivePlayer, defensivePlayer);

        int rand = RandomUtils.rand(0, 100);

        //si rand est inferieur a 25 alors la balle arrive trop basse, impossible de prendre la balle sans le controle de la position
        if (rand > 25) {
            int reboundScore1 = getReboundScore(offensivePlayer);
            int reboundScore2 = getReboundScore(defensivePlayer) + 20;
            if (reboundScore1 > reboundScore2) {
                rebounder = offensivePlayer;
                offensiveHasRebound = true;
            }
        } else if (offensiveHasPosition) {
            rebounder = offensivePlayer;
            offensiveHasRebound = true;
        }


        ReboundResult reboundResult = new ReboundResult();
        reboundResult.setRebounder(rebounder);
        reboundResult.setOffensiveHasRebound(offensiveHasRebound);

        rebounder.getStats().addRebound(offensiveHasRebound);

        return reboundResult;
    }

    private int getReboundScore(Player player) {
        int timing = getTiming(player);
        int standingVerticalJump = (int) (player.getPhysicalSkills().getStandingVerticalJump() * RATIO_STANDING_VERTICAL_JUMP);
        int verticalJumpWithTimingEffects = (standingVerticalJump * timing) / 100;
        return player.getHeight() + verticalJumpWithTimingEffects;
    }

    private int getTiming(Player player) {
        int rand = RandomUtils.rand(0, 100);
        int timing = player.getReboundingSkills().getTiming();

        if (rand <= timing) {
            return 100;
        }
        return 100 - (rand - timing);
    }

    private boolean offensiveHasPosition(Player player1, Player player2) {
        double player1PositionScore = getPositionScore(player1);
        double player2PositionScore = getPositionScore(player2) + 30;
        double probabilityPlayer1GetsPosition = (player1PositionScore - player2PositionScore) + 50;
        return RandomUtils.rand(0, 100) <= probabilityPlayer1GetsPosition;
    }

    private double getPositionScore(Player player1) {
        int positionTechnics = player1.getReboundingSkills().getPositionTechnic();
        int strength = player1.getPhysicalSkills().getStrength();

        return strength * 0.3 + positionTechnics * 0.7;
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
