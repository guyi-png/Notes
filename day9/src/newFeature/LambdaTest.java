package newFeature;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Lambda表达式：
 * 1.例如： (o1, o2) -> Integer.compare(o1,o2)
 * 原来是 ： new Comparator<Integer>() {
 *             @Override
 *             public int compare(Integer o1, Integer o2) {
 *                 return Integer.compare(o1,o2);
 *             }
 *         };
 *  Lambda表达式格式：
 *      -> : Lambda操作符 或 箭头操作符
 *      ->的左边： Lambda的形参列表(即上面例子中的接口的形参列表)
 *      ->的右边： Lambda体(其实就是重写的抽象方法的方法体)
 *
 *  Lambda表达式的本质： 作为函数式接口的实例
 *
 *  如果一个接口中，只声明了一个抽象方法，则此接口称为函数式接口
 *
 *  Java内置的4大核心函数式接口：
 *                 接口             抽象方法
 *  消费型接口： Consumer<T>     void accept(T t)
 *  供给型接口： Supplier<T>         T get()
 *  函数型接口： Function<t>       R apply(T t)
 *  断定型接口： Predicate<T>    boolean test(T t)
 *
 *  函数式接口的使用情景： 如果开发中需要定义一个函数式接口，首先看已有的jdk提供的
 *  函数式接口是否满足需求，如果可以就用现成的，没有就自定义一个函数式接口
 *
 *  方法引用是Lambda表达式的语法糖
 *  方法引用的使用：
 *      使用要求： 要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同
 *      使用情景： 当要传递给Lambda体的操作，已经有实现得方法了，可以使用方法引用，
 *      使用格式： 类(对象)::方法名
 *  构造器引用：
 *      使用要求： 函数式接口的抽象方法的形参列表和构造器的形参列表一致，
 *      抽象方法的返回值类型即为构造器所属的类的类型
 *
 */
public class LambdaTest {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("I have a dream");
            }
        };
        r.run();
        System.out.println("**************************");

        //使用Lambda表达式
        Runnable r1 = () -> System.out.println("I want to win");
        r1.run();


        test1();
        test2();
        test3();
        test4();
    }

    public static void test1(){
        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        System.out.println(c.compare(11,21));
        System.out.println("****************************");
        //Lambda表达式
        Comparator<Integer> c1 = (o1,o2)-> Integer.compare(o1,o2);
        System.out.println(c1.compare(11,21));
        //方法引用
        Comparator<Integer> c2 = Integer::compare;
        System.out.println(c2.compare(11,21));
    }

    public static void test2(){
        //Lambda表达式
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("hello");
        System.out.println("**************");
        //方法引用
        Consumer<String> con2 = System.out::println;
        con2.accept("world");
        System.out.println("#####################");

    }

    public static void test3(){
        Comparator<String> com1 = (s1,s2)->s1.compareTo(s2);
        System.out.println(com1.compare("abc","abd"));
        System.out.println("********************");
        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("abc","abd"));
    }

    public static void test4(){
        //lambda表达式
        Function<Integer,int[]> func1 = length -> new int[length];
        int[] arr = func1.apply(5);
        System.out.println(Arrays.toString(arr));
        System.out.println("****************************");
        //数组引用
        Function<Integer,int[]> func2 = int[]::new;
        int[] arr1 = func2.apply(3);
        System.out.println(Arrays.toString(arr1));
    }
}
