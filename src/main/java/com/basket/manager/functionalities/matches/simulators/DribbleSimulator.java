package com.basket.manager.functionalities.matches.simulators;

import com.basket.manager.pojos.DribbleResultEnum;
import com.basket.manager.pojos.Player;
import com.basket.manager.utils.RandomUtils;

import java.util.function.Supplier;

public class DribbleSimulator {

    private Supplier<Integer> randomNumberSupplier;

    public DribbleSimulator(Supplier<Integer> randomNumberSupplier) {
        this.randomNumberSupplier = randomNumberSupplier;
    }

    public DribbleResult dribbleToMidField(Player player, int timeLeft) {
        int dribble = player.getTechnicalSkills().getDribble();
        int timeSpent = 1;
        int rand = randomNumberSupplier.get();

        if (randomNumberSupplier.get() > (dribble / 4) + 75) {
            player.getStats().addLostBall();
            return new DribbleResult(timeSpent, DribbleResultEnum.LOST_BALL);
        }

        while (dribble < rand && timeSpent != 8 && timeLeft > timeSpent) {
            timeSpent++;
            rand = randomNumberSupplier.get();
        }


        if (timeLeft == timeSpent) {
            return new DribbleResult(timeSpent, DribbleResultEnum.TIMES_UP);
        }

        return new DribbleResult(timeSpent, DribbleResultEnum.SUCCESS);
    }

    public DribbleResult simulateDribblePlayer(Player player) {

        int dribble = player.getTechnicalSkills().getDribble();
        DribbleResultEnum dribbleResultEnum = DribbleResultEnum.FAIL;
        double dribbleScore = dribble * 8 / 10.0;

        if (RandomUtils.rand(0, 100) <= dribbleScore) {
            dribbleResultEnum = DribbleResultEnum.COMPLETE_SUCCESS;
        } else if (RandomUtils.rand(0, 100) <= dribble) {
            dribbleResultEnum = DribbleResultEnum.SUCCESS;
        } else if (RandomUtils.rand(0, 100) > dribble / 4.0 + 75) {
            dribbleResultEnum = DribbleResultEnum.LOST_BALL;
            player.getStats().addLostBall();
        }

        return new DribbleResult(2, dribbleResultEnum);
    }
}
