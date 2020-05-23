package com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构： 属性， 方法， 构造器
 */
public class ReflectionInvoke {
    public static void main(String[] args) throws Exception{
        testMethod();
    }

    public static void testField() throws Exception{
        Class cl = Person.class;
        Constructor constructor = cl.getConstructor();
        Person p = (Person) constructor.newInstance();  //创建一个对象
        //获取指定的属性，要求运行时类中的属性声明为public
        Field age = cl.getField("age");
        age.set(p,16); //参数1指明设置哪个对象的属性，参数2指明属性值设置为多少
        System.out.println((int)age.get(p)); //get()获取对象的当前属性值

        Field name = cl.getDeclaredField("name");//获取运行类中指定的属性
        name.setAccessible(true); //设置为可访问的
        name.set(p,"Alice");
        System.out.println(name.get(p));
    }

    public static void testMethod() throws Exception{
        Class cl = Person.class;
        Person p = (Person) cl.getDeclaredConstructor().newInstance();

        Method say = cl.getDeclaredMethod("say", String.class);
        say.setAccessible(true);
        String  response = (String)say.invoke(p, "干就完了");
        System.out.println(response);

        System.out.println("***************调用静态方法********************");
        Method good = cl.getDeclaredMethod("good");
        good.setAccessible(true);
        int response1 = (int)good.invoke(Class.class);   //私猜，此处的xx.invoke()参数可为随意类型的Class实例
        System.out.println(response1);
    }
}
