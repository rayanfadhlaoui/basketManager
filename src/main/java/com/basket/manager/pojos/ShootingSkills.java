package com.basket.manager.pojos;

public class ShootingSkills {
    private int miDistance;
    private int threePoint;
    private int layUp;

    public int get3Points() {
        return threePoint;
    }

    public int getMiDistance() {
        return miDistance;
    }

    public void setMiDistance(int miDistance) {

        this.miDistance = miDistance;
    }

    public void setThreePoint(int threePoint) {
        this.threePoint = threePoint;
    }

    public int getLayUp() {
        return layUp;
    }

    public void setLayUp(int layUp) {
        this.layUp = layUp;
    }
}
