package com.basket.manager.functionalities.matches.simulators;

import com.basket.manager.pojos.DribbleResultEnum;

public class DribbleResult {
    private final int timeSpent;
    private final DribbleResultEnum dribbleResult;

    public DribbleResult(int timeSpent, DribbleResultEnum dribbleResult) {

        this.timeSpent = timeSpent;
        this.dribbleResult = dribbleResult;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public DribbleResultEnum getResult() {
        return dribbleResult;
    }
}
