package com.basket.manager.pojos;

import com.basket.manager.entities.teams.PlayerPositionEnum;
import com.basket.manager.functionalities.matches.simulators.*;
import com.basket.manager.utils.RandomUtils;

public class Strategy {
    public static final Strategy ISOLATION = new Strategy();
    private static final int POSSESSION_TIME = 24;
    private static final int POSSESSION_AFTER_REBOUND_TIME = 10;
    private final ReboundSimulator reboundSimulator = new ReboundSimulator();
    private final DribbleSimulator dribbleSimulator = new DribbleSimulator(RandomUtils.getRandomNumberSupplier(0, 100));
    private final ShootSimulator shootSimulator = new ShootSimulator();
    private final PasseSimulator passeSimulator = new PasseSimulator();
    private Player playerWithBall;
    private int timeLeft;

    public StrategyResult execute(Team team1, Team team2, int timeLeft) {
        playerWithBall = team1.getPlayer(PlayerPositionEnum.POINT_GARD);
        StrategyResult strategyResult = new StrategyResult();
        this.timeLeft = timeLeft;

        DribbleResult dribbleResult = dribbleSimulator.dribbleToMidField(playerWithBall, timeLeft);
        strategyResult.increaseTimeSpent(dribbleResult.getTimeSpent());

        if (dribbleResult.getResult() == DribbleResultEnum.SUCCESS) {
            boolean offenseHasScored = execute(team1, team2, POSSESSION_TIME - strategyResult.getTimeSpent(), strategyResult);
            if (!offenseHasScored && getTimeLeft(strategyResult) != 0) {
                executeRebound(team1, team2, strategyResult);
            }
        }

        return strategyResult;
    }

    private boolean execute(Team team1, Team team2, int possession, StrategyResult strategyResult) {
        boolean offenseHasScored = false;

        int timeLeft = getTimeLeft(strategyResult);
        if (timeLeft != 0) {
            possession = timeLeft > possession ? possession : timeLeft;
            if (possession <= 2) {
                offenseHasScored = shootSimulator.simulateShoot(ShootType.THREE_POINT, playerWithBall, strategyResult);
                strategyResult.increaseTimeSpent(Math.min(possession, 2));
            } else {
                DribbleResult dribbleResult = dribbleSimulator.simulateDribblePlayer(playerWithBall);
                strategyResult.increaseTimeSpent(dribbleResult.getTimeSpent());

                if (dribbleResult.getResult() == DribbleResultEnum.COMPLETE_SUCCESS) {
                    offenseHasScored = shootSimulator.simulateLayUp(playerWithBall, strategyResult);
                } else if (dribbleResult.getResult() == DribbleResultEnum.SUCCESS) {
                    offenseHasScored = shootSimulator.simulateShoot(playerWithBall, strategyResult);
                } else if (dribbleResult.getResult() == DribbleResultEnum.FAIL) {
                    PasseResult passeResult = passeSimulator.simulatePasse(playerWithBall, team1);
                    if (passeResult.getPasseResultEnum() == PasseResultEnum.SUCCESS) {
                        playerWithBall = passeResult.getReceiver();
                        return execute(team1, team2, possession - dribbleResult.getTimeSpent(), strategyResult);
                    }
                }
            }
        }

        return offenseHasScored;
    }

    private void executeRebound(Team team1, Team team2, StrategyResult strategyResult) {
        ReboundResult reboundResult = reboundSimulator.simulate(team1, team2);
        if (reboundResult.offenseHasRebound()) {
            playerWithBall = reboundResult.getRebounder();
            execute(team1, team2, POSSESSION_AFTER_REBOUND_TIME, strategyResult);
        }
    }

    private int getTimeLeft(StrategyResult strategyResult) {
        return timeLeft - strategyResult.getTimeSpent();
    }
}
