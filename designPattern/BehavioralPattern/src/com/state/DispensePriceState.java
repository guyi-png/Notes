package com.state;

public class DispensePriceState implements State {
    private Activity activity;

    public DispensePriceState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deductIntegral() {
        System.out.println("你已经中奖了，去领取奖品吧");
    }

    @Override
    public boolean raffle() {
        System.out.println("你已经中奖了，去领取奖品吧");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("领取奖品成功");
        activity.setState(activity.getNoRaffleStateState());
    }
}
