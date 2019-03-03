package com.basket.manager.factories;

import com.basket.manager.utils.RandomUtils;

import java.util.List;
import java.util.function.Supplier;

public class RandomObjectSupplier<T> implements Supplier<T> {
    private final List<T> values;

    public RandomObjectSupplier(List<T> values) {

        this.values = values;
    }

    @Override
    public T get() {
        return values.get(RandomUtils.rand(0, values.size() - 1));
    }
}
