package com.state;

import java.util.Random;

public class CanRaffleState implements State {
    private Activity activity;

    public CanRaffleState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deductIntegral() {
        System.out.println("已经扣除了积分，可以抽奖");
    }

    @Override
    public boolean raffle() {
        System.out.println("开始抽奖");
        Random r = new Random();
        int num = r.nextInt(10);
        if (num == 8){
            activity.setState(activity.getDispensePriceState());
            return true;
        }
        activity.setState(activity.getNoRaffleStateState());
        System.out.println("没有抽到奖品");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("中奖了才可以领取奖品");

    }
}
