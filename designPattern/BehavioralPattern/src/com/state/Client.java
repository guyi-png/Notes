package com.state;

/**
 * 状态模式：如法炮制
 *
 * 应用场景：
 *      当一个事件或者对象有很多种状态，状态之间会相互转换，
 *      对不同的状态的要求有不同的行为的时候，可以考虑使用
 */
public class Client {
    public static void main(String[] args) {
        Activity activity = new Activity();

        activity.deductIntegral();
        boolean isWin = activity.raffle();
        if (isWin){
            activity.dispensePrize();
        }

        /**
         *  看下面问题, 变量state保存State对象的引用，当
         *  state.deductIntegral()等操作时，修改的是内部state对象的指向
         *  但下面的变量state没有改变其引用， 还是指向一开始的noRaffleState
         */
        System.out.println("-----------------------------");
        State state = activity.getState();
        state.deductIntegral();
        state.raffle();
        state.dispensePrize();
    }
}
