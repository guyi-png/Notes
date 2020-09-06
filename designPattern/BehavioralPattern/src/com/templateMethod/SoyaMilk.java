package com.templateMethod;

/**
 * 抽象类
 */
public abstract class SoyaMilk {
    // make方法为 模板方法 , final 禁止子类重写此方法
    public final void make(){
        System.out.println("第一步");
        select();
        System.out.println("第二步");
        if (!isPure()){
            add();
        }else{
            System.out.println("什么也不加");
        }
        System.out.println("第三步");
        soak();
        System.out.println("第四步");
        beat();
    }

    protected  void select(){
        System.out.println("选择上好的黄豆");
    };

    protected abstract void add();

    protected boolean isPure(){
        return false;
    }

    protected void soak(){
        System.out.println("浸泡");
    }

    protected void beat(){
        System.out.println("打浆");
    }
}
