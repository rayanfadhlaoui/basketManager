package com.basket.manager.factories;

import com.basket.manager.utils.RandomUtils;

import java.util.function.Supplier;

public class HeightSupplier implements Supplier<Integer> {

    private static final int HEIGHT = 148;

    @Override
    public Integer get() {
        int rand = RandomUtils.rand(1, 10);
        int heightModifier = 0;
        if (rand == 10) {
            heightModifier = RandomUtils.rand(-23, 0);
        } else if (rand == 9) {
            heightModifier = RandomUtils.rand(0, 23);
        } else if (rand == 8 || rand == 7) {
            heightModifier = RandomUtils.rand(0, 11);
        } else if (rand == 6 || rand == 5) {
            heightModifier = RandomUtils.rand(-11, 0);
        } else {
            heightModifier = RandomUtils.rand(-5, 5);
        }

        return HEIGHT + heightModifier;
    }
}
