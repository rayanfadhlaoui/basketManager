package com.basket.manager.pojos;

import org.junit.Test;

import static org.mockito.Mockito.mock;

class StrategyTest {

    @Test
    public void test1() {
        Strategy strategy = new Strategy();

        strategy.execute(mock(Team.class), mock(Team.class), 10);
    }
}