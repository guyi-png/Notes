package com.prototype;

/**
 * 原型模式的注意事项和细节：
 *      创建新的对象比较复杂时， 可以利用原型模式简化对象的创建过程， 同时也能够提高效率
 *      不用重新初始化对象， 而是动态的获得对象运行时的状态
 *      如果原型对象发生变化，其他的克隆对象也会相应的变化
 *
 *    缺点： 需要为每一个类配备一个克隆方法，这对全新的类还行，但是已有的类，如果
 *    去修改其源码来添加克隆方法， 就违背了ocp原则
 */
public class Test {
    public static void main(String[] args) {
        // 原来的羊
        Sheep sheep = new Sheep("Tom", 1, "白色");
        sheep.setFriend(new Sheep("jon", 2, "黑色"));
        // 克隆 4只羊
//        Sheep cloneSheep1 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep cloneSheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep cloneSheep3 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        Sheep cloneSheep4 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
//        System.out.println(cloneSheep1.equals(cloneSheep2)); //true

//        // 原型克隆
        Sheep cloneSheep1 = sheep.clone();
        Sheep cloneSheep2 = sheep.clone();
        Sheep cloneSheep3 = sheep.clone();
        Sheep cloneSheep4 = sheep.clone();

        System.out.println(cloneSheep1.equals(cloneSheep2));  //true
        System.out.println(sheep.getName() == cloneSheep1.getName()); //true, 同一个引用

        System.out.println(sheep.getFriend() == cloneSheep4.getFriend());  //true , 同一个引用



        DeepClonePrototype deepClonePrototype = new  DeepClonePrototype("xx", "yy");
        DeepCloneTarget deepCloneTarget = new DeepCloneTarget("target", "目标", deepClonePrototype);
        // 克隆
        DeepCloneTarget cloneTarget = deepCloneTarget.clone();
        DeepCloneTarget deepCloneTarget1 = deepCloneTarget.deepClone();

        boolean judge = deepCloneTarget.getDeepClonePrototype() == cloneTarget.getDeepClonePrototype();
        boolean judge1 = deepCloneTarget1.getDeepClonePrototype() == cloneTarget.getDeepClonePrototype();
        System.out.println(judge);  // false
        System.out.println(judge1); //false
    }
}
