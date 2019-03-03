package com.basket.manager.pojos;

public class Stats {
    private int nb3PointsMade;
    private int nbMiDistanceMade;
    private int nb3PointsTaken;
    private int nbMiDistanceTaken;

    public Stats() {
        this.nb3PointsTaken = 0;
        this.nb3PointsMade = 0;
        this.nbMiDistanceTaken = 0;
        this.nbMiDistanceMade = 0;
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

    @Override
    public String toString() {
        String scoreString = "Points : " + getScore();
        String threePointsString = " 3 points : " + nb3PointsMade + "/" + nb3PointsTaken;
        String totalShootsString = " total shoots :" + (nb3PointsMade + nbMiDistanceMade) + "/" + (nb3PointsTaken + nbMiDistanceTaken);
        return scoreString + threePointsString + totalShootsString;
    }
}
