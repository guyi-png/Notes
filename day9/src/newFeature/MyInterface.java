package newFeature;

public interface MyInterface {
    void methodAbstract();

    static void methodStatic(){
        System.out.println("静态方法");
    }

    default void methodDefault(){
        System.out.println("俺是默认方法");
    }
    //以上是public 的方法
    private void methodPrivate(){
        System.out.println("私有方法");
    }
}
