package com.basket.manager.functionalities.matches.simulators;

import com.basket.manager.pojos.*;
import com.basket.manager.utils.RandomUtils;

public class ShootSimulator {

    private static final int HIGH_SCHOOL_3_POINT = 606;

    public boolean simulateShoot(ShootType shootType, Player player, StrategyResult strategyResult) {
        int pointsScored = 0;
        Stats stats = player.getStats();
        boolean offenseHasScored = false;

        if (shootType == ShootType.THREE_POINT) {
            if (shoot(player, new Field(HIGH_SCHOOL_3_POINT, true))) {
                pointsScored = 3;
                offenseHasScored = true;
            }
            stats.shoot3Points(offenseHasScored);
        } else {
            if (shoot(player, new Field(450, false))) {
                pointsScored = 2;
                offenseHasScored = true;
            }
            stats.shootMiDistance(offenseHasScored);
        }
        strategyResult.setScore(pointsScored);

        return offenseHasScored;
    }

    public boolean simulateLayUp(Player player, StrategyResult strategyResult) {
        ShootingSkills shootingSkills = player.getOffensiveSkills().getShootingSkills();
        boolean offenseHasScored = RandomUtils.rand(0, 100) <= (shootingSkills.getLayUp() / 2 + 50);
        player.getStats().shootMiDistance(offenseHasScored);
        if (offenseHasScored) {
            strategyResult.setScore(2);
        }

        return offenseHasScored;
    }

    public boolean simulateShoot(Player player, StrategyResult strategyResult) {
        ShootingSkills shootingSkills = player.getOffensiveSkills().getShootingSkills();
        int threePoints = shootingSkills.get3Points();
        int miDistance = shootingSkills.getMiDistance();
        if (threePoints * 1.5 >= miDistance) {
            return simulateShoot(ShootType.THREE_POINT, player, strategyResult);
        } else {
            return simulateShoot(ShootType.MI_DISTANCE, player, strategyResult);
        }
    }

    private boolean shoot(Player player, Field field) {
        ShootingSkills shootingSkills = player.getOffensiveSkills().getShootingSkills();
        int probability;
        if (field.is3Point()) {
            probability = shootingSkills.get3Points();
        } else {
            probability = shootingSkills.getMiDistance();
        }

        return RandomUtils.rand(0, 100) <= probability;
    }
}
