package com.basket.manager.pojos;

public class Field {
    private final int distance;
    private final boolean is3Point;

    public Field(int distance, boolean is3Point) {

        this.distance = distance;
        this.is3Point = is3Point;
    }

    public boolean is3Point() {
        return is3Point;
    }
}
