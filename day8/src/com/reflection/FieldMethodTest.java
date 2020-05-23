package com.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;

/**
 *
 */
public class FieldMethodTest {
    public static void main(String[] args) {
       testSuperClass();
    }

    public static void testField(){
        Class cl = String.class;
        //getFields()获取运行类的属性结构,包含父类的属性，只能获得Public修饰的属性
        Field[] fields = cl.getFields();
        for (Field f : fields){
            System.out.println(f);
        }
        System.out.println();

        //getDeclaredFields()获取运行类自己声明的所有属性，不包含父类的属性，
        Field[] declaredFields = cl.getDeclaredFields();
        for (Field f : declaredFields){
            System.out.println(f);
        }
        System.out.println();

        //获取属性具体的信息：权限修饰符+数据类型+变量名
        for (Field f : declaredFields){
            //获取修饰符
            int modifiers = f.getModifiers();
            //0 缺省;  1 public; 2 private; 4 protected;  ....
            System.out.print(Modifier.toString(modifiers));
            //获取声明类型
            Class<?> type = f.getType();
            System.out.print(" "+type.getName());
            //获取属性名
            String fName = f.getName();
            System.out.print(" "+fName);
            System.out.println();
        }
    }

    public static void testMethod(){
        ////getFields()获取运行类的方法,包含父类的方法，只能获得Public修饰的属性
        Class cl = Person.class;
        Method[] methods = cl.getMethods();
        for (Method m : methods){
            System.out.println(m);
        }
        System.out.println();

        //getDeclaredFields()获取运行类自己声明的所有方法，不包含父类的方法，
        Method[] declaredMethods = cl.getDeclaredMethods();
        for (Method m : declaredMethods){
            System.out.println(m);
        }
        System.out.println("****************************");

        //获取方法具体的信息：权限修饰符+返回值类型+方法名+参数列表+异常
        for (Method m : declaredMethods){
            Annotation[] annotations = m.getAnnotations();

            //获取注解
            for (Annotation annotation : annotations){
                System.out.print(annotation+" ");
            }

            //获取权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()));

            //获取返回值类型
            System.out.print(" "+m.getReturnType().getName()+" ");

            //获取方法名
            System.out.print(m.getName());
            System.out.print("(");

            //获取形参
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (parameterTypes ==null || parameterTypes.length ==0){}else{
                for (int i=0; i < parameterTypes.length; i++){
                    if (i == parameterTypes.length -1){
                        System.out.print(parameterTypes[i].getName()+" args_"+i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName()+" args_"+i+", ");
                }
            }
            System.out.print(")");

            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes == null || exceptionTypes.length == 0){}else{
                System.out.print(" throws ");
                for (int i =0; i < exceptionTypes.length;i++){
                    if (i == exceptionTypes.length-1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName()+", ");
                }
            }
            System.out.println();
        }
    }

    public static void testConstructor(){
        Class cl = Person.class;
        //getConstructors():获取当前运行时类中声明public的构造器
        Constructor[] constructors = cl.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println(constructor);
        }
        System.out.println();

        //getDeclaredConstructors(): 获取当前运行类中声明的所有构造器
        Constructor[] declaredConstructors = cl.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors){
            System.out.println(constructor);
        }

    }

    public static void testSuperClass(){
        Class cl = ArrayList.class;
        Class superClass = cl.getSuperclass();
        System.out.println(superClass);

        System.out.println(cl.getGenericSuperclass());//获取泛型父类
        //转化为有参数的类型
        ParameterizedType parameterizedType = (ParameterizedType)cl.getGenericSuperclass();
        //获取泛型类型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0]);

        //获取运行时类的接口
        Class[] interfaces = cl.getInterfaces();
        for (Class c: interfaces){
            System.out.println(c);
        }

        //获取包
        System.out.println(cl.getPackage());
    }
}
