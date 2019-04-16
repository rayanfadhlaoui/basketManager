package com.basket.manager.pojos;

import com.basket.manager.functionalities.matches.simulators.PasseResultEnum;

public class PasseResult {
    private Player receiver;
    private PasseResultEnum passeResultEnum;

    public PasseResultEnum getPasseResultEnum() {
        return passeResultEnum;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {

        this.receiver = receiver;
    }

    public void setPasseResultEnum(PasseResultEnum success) {

        this.passeResultEnum = success;
    }
}
