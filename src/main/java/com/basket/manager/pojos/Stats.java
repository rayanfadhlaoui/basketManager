package com.basket.manager.pojos;

public class Stats {
    private int lostBalls;
    private int defensiveRebounds;
    private int offensiveRebounds;
    private int rebounds;
    private int nb3PointsMade;
    private int nbMiDistanceMade;
    private int nb3PointsTaken;
    private int nbMiDistanceTaken;

    public Stats() {
        this.nb3PointsTaken = 0;
        this.nb3PointsMade = 0;
        this.nbMiDistanceTaken = 0;
        this.nbMiDistanceMade = 0;
        this.rebounds = 0;
        this.offensiveRebounds = 0;
        this.defensiveRebounds = 0;
        this.lostBalls = 0;
    }

    private int getScore() {
        return 2 * nbMiDistanceMade + 3 * nb3PointsMade;
    }

    public void shootMiDistance(boolean success) {
        if (success) {
            nbMiDistanceMade++;
        }
        nbMiDistanceTaken++;
    }

    public void shoot3Points(boolean success) {
        if (success) {
            nb3PointsMade++;
        }
        nb3PointsTaken++;
    }

    public void addLostBall() {
        lostBalls++;
    }

    @Override
    public String toString() {
        String scoreString = getScore() + " points ";
        String threePointsString = " 3 points " + nb3PointsMade + "/" + nb3PointsTaken;
        String totalShootsString = " total shoots " + (nb3PointsMade + nbMiDistanceMade) + "/" + (nb3PointsTaken + nbMiDistanceTaken);
        String reboundString = "Rebonds total " + rebounds + " defensif|offensif " + defensiveRebounds + " | " + offensiveRebounds;
        return scoreString + threePointsString + totalShootsString + "\n" + reboundString + "\n LB " + lostBalls;
    }

    public void addRebound(boolean offensive) {
        if (offensive) {
            offensiveRebounds++;
        } else {
            defensiveRebounds++;
        }
        rebounds++;
    }
}
