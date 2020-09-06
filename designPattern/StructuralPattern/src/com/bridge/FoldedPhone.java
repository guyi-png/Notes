package com.bridge;

/**
 * 折叠的样式
 */
public class FoldedPhone extends Phone{
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        System.out.print("折叠式的");
        super.open();
    }

    public void close(){
        System.out.print("折叠式的");
        super.close();
    }

    public void call(){
        System.out.print("折叠式的");
        super.call();
    }
}
