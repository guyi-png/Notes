package com.state;

public interface State {
    // 扣除积分
    void deductIntegral();

    // 抽奖
    boolean raffle();

    // 领取奖品
    void dispensePrize();
}
