package com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 关于java.lang.Class 类的理解：
 *  类的加载过程： 程序经过javac.exe命令后，会生成一个或多个字节码文件(以.class结尾)
 *  默认使用java.exe命令对某个字节码文件进行解释运行，即将某个字节码文件加载到内存中(类的加载)，
 *  加载到内存中的类，称为运行时类，此类作为Class的实例，  即类也是对象
 *
 *  Class的实例对应着一个运行时类
 *  class,enum,annotation,interface,[]array,primitive type,void 都有Class对象
 */
public class ReflectionTest {
    public static void main(String[] args) throws Exception {

    }

    public static void test1(){
        Person p1 = new Person("Alice",15);
        p1.age = 16;
        System.out.println(p1.toString());
        //以上未使用反射，不能通过Person类的对象调用其内部私有的结构
        //比如， name , show()以及私有的构造器
    }

    public static void test2() throws Exception{
        //使用反射
        Class cl= Person.class;

        Constructor cons= cl.getConstructor(String.class,int.class);
        Person p1 = (Person) cons.newInstance("Alice",15);
        System.out.println(p1.toString());

        Field age= cl.getDeclaredField("age");
        age.set(p1, 16);
        System.out.println(p1.toString());

        Method show = cl.getDeclaredMethod("show");
        show.invoke(p1);

        Constructor cons1 = cl.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p2 = (Person)cons1.newInstance("Mike");
        System.out.println(p2);

        Method  say = cl.getDeclaredMethod("say", String.class);
        say.setAccessible(true);
        String response = (String)say.invoke(p1, "干就完了");
        System.out.println(response);
    }

    public static void test3(){
        //获取Class的实例

        //调用运行时类的属性
        Class<Person> cl =Person.class;
        System.out.println(cl);
        //通过运行时类的对象
        Person p = new Person();
        Class cl1 = p.getClass();  //读取对象的类
        System.out.println(cl1);
        //调用Class的静态方法，forName(String classPath)
        Class cl2= null;
        try {
            cl2 = Class.forName("com.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(cl2);

        System.out.println((cl==cl1) + " and "+ (cl1 == cl2));//获取的是同一个实例

        //使用类的加载器
        Class cl3 = null;
        try {
            ClassLoader classLoader = ReflectionTest.class.getClassLoader();
            cl3 = classLoader.loadClass("com.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(cl3);
    }

    public static void test4(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        System.out.println(classLoader);
        //调用系统加载器的getParent()，获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        //调用扩展类加载器的getParent()，无法获取引导类加载器
        //引导类加载器主要加载java的核心类库，无法加载自定义类
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
    }

    public static void test5() throws Exception{
        Class<Person> cl = Person.class;
        Person p = cl.newInstance();  //调用该类的构造器
        System.out.println(p);
    }
}
