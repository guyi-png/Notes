package newFeature;

public class MyImplements implements MyInterface{
    @Override
    public void methodAbstract() {

    }

    @Override
    public void methodDefault() {

    }

    public static void main(String[] args) {
        //接口中的静态方法只能由接口自己调用
        MyInterface.methodStatic();
        MyImplements mi = new MyImplements();
        //接口的私有方法不能在外部调用
//        mi.methodPrivate();
    }
}
