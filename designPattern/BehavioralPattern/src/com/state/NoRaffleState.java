package com.state;


public class NoRaffleState implements State {
    private Activity activity;

    public NoRaffleState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deductIntegral() {
        System.out.println("扣除50积分, 现在可以抽奖");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("扣除了积分才可以抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("不能领取奖品");
    }
}
