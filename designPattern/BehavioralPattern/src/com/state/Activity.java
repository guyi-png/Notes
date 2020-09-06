package com.state;

public class Activity {
    private State canRaffleState = new CanRaffleState(this);
    private State dispensePriceState = new DispensePriceState(this);
    private State noRaffleStateState = new NoRaffleState(this);
    // state表示当前的状态
    private State state = noRaffleStateState;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(State canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public State getDispensePriceState() {
        return dispensePriceState;
    }

    public void setDispensePriceState(State dispensePriceState) {
        this.dispensePriceState = dispensePriceState;
    }

    public State getNoRaffleStateState() {
        return noRaffleStateState;
    }

    public void setNoRaffleStateState(State noRaffleStateState) {
        this.noRaffleStateState = noRaffleStateState;
    }

    public void deductIntegral(){
        state.deductIntegral();
    }

    public boolean raffle(){
        return state.raffle();
    }

    public void dispensePrize(){
        state.dispensePrize();
    }
}
