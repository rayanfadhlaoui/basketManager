package com.basket.manager.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomUtils {

    public static int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static Supplier<Integer> getRandomNumberSupplier(int min, int max) {
        return () -> rand(min, max);
    }
}
