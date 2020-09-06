package com.factory.abstractMethod;

/**
 * pizza抽象类
 */
public abstract class Pizza {
    protected String name;
    // 准备
    protected abstract void prepare();

    protected void bake(){
        System.out.println(name+" 开始烘烤");
    }

    protected void cut(){
        System.out.println(name+" 开始切片");
    }

    protected void box(){
        System.out.println(name+" 开始打包");
    }
}
