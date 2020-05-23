package newFeature;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 集合讲的是数据，Stream讲的是计算
 * Stream不会存储数据，不会改变源对象，
 * 其操作是延迟执行的，需要结果时才执行，返回一个持有结果的新Stream
 *
 * Stream的执行流程：
 *  1.Stream的实例化
 *  2.一系列的中间操作
 *  3.终止操作
 *
 *  一个中间操作链，对数据源的数据进行处理，一旦执行终止操作，就执行中间操作链，
 *  并产生结果
 */
public class StreamAPITest {
    public static void main(String[] args) {
        test4();
    }

    public static void test1(){
        List<String> list = new ArrayList<>();
        list.add("阿济格的");
        list.add("是德国空军和");
        list.add("啊师傅哭一会");
//  *  1.Stream的实例化
//      default Stream<E> stream(): 返回一个顺序流
        Stream<String> stream = list.stream();

//      default Stream<E> parallelStream(): 返回一个并行流
        Stream<String> parallelStream = list.parallelStream();

//      调用Arrays类的static <T> Stream stream(T[] array):返回一个流
        int[] arr = new int[]{1,2,6,23,7};
        IntStream stream1 = Arrays.stream(arr);

//      通过Stream的of()创建流
        Stream<Integer> stream2 = Stream.of(12, 36, 87, 1, 5);

//      创建无限流
//      迭代：
//      public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //limit()截断流，做限制
        Stream.iterate(0,t -> t+2).limit(10).forEach(System.out::println);

//      生成：
//      public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    public static void test2(){
        List<Integer> list = new ArrayList<>();
        list.add(12354);
        list.add(54);
        list.add(222);
        list.add(98);
        list.add(28);
        list.add(28);
        list.add(123);
        list.add(234);
        list.add(357);
        //创建stream
        Stream<Integer> stream = list.stream();
        //中间操作
        //filter() 过滤
        Stream<Integer> stream1 = stream.filter(e -> e > 100);
        //limit(n) 截断流，使其元素不超过给定数量
        Stream<Integer> stream2 = stream1.limit(9);
        //skip(n)  跳过流，返回一个扔掉了前n个元素的流，若流中没有n个元素，则返回空流
        Stream<Integer> stream3 = stream2.skip(2);
        // distinct() 筛选，通过流中元素的hashCode()和equals()去除重复元素
        Stream<Integer> stream4 = stream3.distinct();
        //map() 映射
        Stream<Integer> stream5 = stream4.map(e -> e + 250);
        //flatMap(Function f)

        //排序  sorted()自然排序  sorted(Comparator com)定制排序
//        Stream<Integer> stream6 = stream5.sorted();
        Stream<Integer> stream6 = stream5.sorted(Integer::compareTo);
        //终止操作
        stream6.forEach(System.out::println);
    }

    public static void test3(){
        List<Integer> list = new ArrayList<>();
        list.add(12354);
        list.add(54);
        list.add(222);
        list.add(98);
        list.add(28);
        list.add(28);
        list.add(123);
        list.add(234);
        list.add(357);
        //Stream的终止操作:
        //1.匹配与查找
        Stream<Integer> stream = list.stream();
        //allMatch(Predicate p) 全部都匹配，就返回true
        boolean allMatch = stream.allMatch(e -> e > 70);  //终止了操作
        System.out.println(allMatch);
        //anyMatch(Predicate p) 有就行
        stream = list.stream();
        boolean anyMatch = stream.anyMatch(e -> e > 10000);
        System.out.println(anyMatch);
        //noneMatch(Predicate p) 没有就行
        stream = list.stream();
        boolean noneMatch = stream.noneMatch(e -> e < -1);
        System.out.println(noneMatch);
        //findFirst() 返回第一个元素
        stream = list.stream();
        Optional<Integer> first = stream.findFirst();
        System.out.println(first);
        //findAny() 返回任意的一个元素
        Stream<Integer> parallelStream = list.parallelStream();
        Optional<Integer> any = parallelStream.findAny();
        System.out.println(any);

        //count  返回流中的总个数
        stream = list.stream();
        long count = stream.filter(e -> e > 100).count();
        System.out.println(count);
        //max(Comparator c)  返回流中最大值
        stream = list.stream();
        Optional<Integer> max = stream.max(Integer::compare);
        System.out.println(max);
        //min(Comparator c)  返回流中最小值
        //forEach(Consumer c)  内部迭代



        //2.归约
        //accumulator 累加器
        //reduce(T identity,BinaryOperator accumulator): 可以将流中的元素反复
        //结合起来，得到一个值，返回T
        stream = list.stream();
        Integer reduce = stream.reduce(0, Integer::sum);
        System.out.println(reduce);
        //reduce(BinaryOperator accumulator): 可以将流中元素反复结合起来，
        //得到一个值，返回Optional<T>
        stream = list.stream();
//        Optional<Integer> reduce1 = stream.reduce((i1,i2) -> i1+i2);
        Optional<Integer> reduce1 = stream.reduce(Integer::sum);
        System.out.println(reduce1);


//        3.收集
        //collect(Collector c): 将流转换为其他形式，接收一个Collector接口的实现
        //用于给Stream中元素做汇总的方法
        System.out.println("$$$$$$$$$$$$$$");
        stream = list.stream();
        Stream<Integer> stream1 = stream.sorted();
        List<Integer> list1 = stream1.collect(Collectors.toList());
        list1.forEach(System.out::println);
//        Set<Integer> set = stream1.collect(Collectors.toSet());
//        set.forEach(System.out::println);
    }

    public static void test4(){
        /*java9新特性*/
        List<Integer> list = Arrays.asList(23, 45, 65, 356, 1, 67, 89);
        Stream<Integer> stream = list.stream();
        //takeWhile返回从开头开始的尽可能多的元素
        stream.takeWhile(x -> x < 60).forEach(System.out::println);

        System.out.println("*************************");
        //返回剩余的元素
        stream = list.stream();
        stream.dropWhile(x -> x <60).forEach(System.out::println);

        Stream.iterate(0, x -> x < 100, x -> x + 1).forEach(System.out::println);

    }
}
