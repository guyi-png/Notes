package com.map.learn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 *  |----Map接口: 双向数据，存储key-value对的数据，
 *      |----HashMap: 作为Map的主要实现类，线程不安全，效率高，可存储null的key和value
 *          |----LinkedHashMap：保证在遍历map元素时，可以实现按照添加顺序遍历
 *      |----TreeMap：保证按照添加的key-value对进行排序遍历，此时考虑key的自然排序或定制排序
 *      |----Hashtable： 作为Map的古老实现类，线程安全，不能存储null的key和value
 *          |----Properties: 常用于处理配置文件，key和value都是String类型
 * Map结构的理解：
 *      Map中的key： 无序的，不可重复，使用Set存储所有的key
 *      Map中的value： 无序的，可重复的，使用Collection存储所有的value
 *      一个键值对： key-value构成一个entry对象
 *      Map中的entry： 无序的，不可重复的，使用Set存储所有的entry
 *
 *  HashMap的底层（jdk7.0）：
 *      HashMap map = new HashMap();
 *      在实例化后底层创建了长度是16的一维数组Entry[] table
 *      map.put(key,value);
 *      首先，调用key所在类中定义的hashCode()方法计算出hash值，
 *      然后使用此hash值计算得到key在Entry数组中的存放位置，
 *      如果此位置为空，此时的key-value就添加成功
 *      如果此位置不为空（此位置可能有多个值存在），比较key和该位置上的元素的hash值
 *      如果key的hash值与该位置上所有的元素的hash值不相同，此时就添加成功
 *      如果key的hash值和该位置上的元素的hash值有相同的，则比较二者，
 *      二者通过key类中定义的equals()方法比较，如果是false则添加成功
 *      如果是true则使用新值替换原来在位置上的旧值
 *      同位置的多个元素以链表存储
 *
 *      在不断的添加过程中，会涉及到扩容问题， 当超过临界值并且添加的位置为空时
 *      扩容的默认方式是扩容为原来的2倍，
 *      并将所有数据复制到新数组中
 *
 * HashMap的底层（jdk8.0）：
 *  1.new HashMap(): 底层没有创建一个长度为16的数组
 *  2.jdk8.0底层的数组以Node[]做数组，不是Entry[]
 *  3.第一次动用put()时底层就会创建长度为16的数组
 *  4.jdk7低层结构只有数组和链表，jdk8.0底层结构是数组，链表和红黑树，
 *  当数组的某个索引位置的元素以链表的形式存储个数超过8个，
 *  并且当前数组长度大于64时，索引位置使用红黑树存储（不使用链表）
 *
 *
 *  DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16  HashMap默认的容量
 *  DEFAULT_LOAD_FACTOR = 0.75f; //HashMap默认的加载因子
 *  int threshold；    //扩容的临界值=容量 x 加载因子   16 x 0.75 = 12
 *  TREEIFY_THRESHOLD = 8;  //Bucket中链表长度大于该默认值，转化为红黑树
 *
 *  Map的常用方法:
 *      put(Object key,Object value);
 *      remove(Object key);
 *      get(Object key);
 *      size();
 *      keySet(); /values(); /entrySet();
 */
public class MapTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    public static void test1(){
        Map map = new HashMap();
        map = new LinkedHashMap();
        map.put(341, "aa");
        map.put("cc",3);
        map.put(123,"usd");
        System.out.println(map); //{341=aa, cc=3, 123=usd}
    }

    public static void test2(){
        Map map = new HashMap();
        map.put("aa",23);
        map.put(454,"olg");
        map.put(454,"lgo");
        System.out.println(map); //{aa=23, 454=lgo}

        Map map1 = new HashMap();
        map1.put("CC",66);
        map1.putAll(map);
        System.out.println(map1);//{CC=66, aa=23, 454=lgo}

        Object value = map1.remove("aa");
        System.out.println(value+"###"+map1);//23###{CC=66, 454=lgo}

        map.clear();
        System.out.println(map.size());// 0
        System.out.println(map);  //{}

        Object value1 = map1.get("CC");
        System.out.println(value1); //66

        System.out.println(map1.containsKey(454));  //true
        System.out.println(map1.containsValue(66)); //true

        System.out.println(map.isEmpty()); //true

        Map map2 = new HashMap();
        map2.putAll(map1);
        System.out.println(map1.equals(map2));  //true
    }

    public static void test3(){
        Map map = new HashMap();
        map.put(1,'a');
        map.put(666,"hahahahahahahahaha");
        map.put(2,'b');
        map.put(3,'c');
        //遍历所有的key集,  map.keySet()返回key的Set集
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //遍历所有的value集，values()
//        map.values().forEach(System.out::println);

//        Iterator iterator1 = map.values().iterator();
//        while(iterator1.hasNext()){
//            System.out.println(iterator1.next());
//        }
        Collection values = map.values();
        for (Object value : values){
            System.out.println(value);
        }

        //遍历所有的entry
        Set entrySet = map.entrySet();
        Iterator iterator1 =  entrySet.iterator();
        System.out.println();
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println("key: "+entry.getKey() + ", value: "+entry.getValue());
        }
        //遍历所有的entry方式二
        System.out.println();
        iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            Object value = map.get(key);
            System.out.println("key: "+key + ", value: "+value);
        }
        System.out.println();
    }

    public static void test4(){
        /*
        因为要按照key进行排序，向TreeMap中添加key-value时，
        要求key必须是由同一个类创建的对象
         */
        TreeMap map = new TreeMap();
        //以key的类中定义的重写的compareTo()方法排序
        map.put("abc",100);
        map.put("def",200);
        map.put("deg",182);
        map.put("sab",666);
        map.put("aec",100);
        map.put("sac",667);
        Set entrySet = map.entrySet();
        for (Object obj : entrySet){
            Map.Entry entry = (Map.Entry) obj;
            System.out.println("key= " + entry.getKey() +
                    ", value= "+ entry.getValue());
        }

        System.out.println();

        //添加比较器
        TreeMap map1 = new TreeMap(new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String){
                    String s1 = (String)o1;
                    String s2 = (String)o2;
                    //按字母从大到小排序
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("类型不匹配");
            }
        });
        map1.put("abc",100);
        map1.put("def",200);
        map1.put("deg",182);
        map1.put("sab",666);
        map1.put("aec",100);
        map1.put("sac",667);
        Iterator iterator = map1.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("key= " + entry.getKey() +
                    ", value= "+ entry.getValue());
        }
    }

    public static void test5(){
        //Properties的key和value都是String类型的
        Properties properties = new Properties();
        //配置同包下的jdbc.properties文件
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("day5/src/com/map/learn/jdbc.properties");
            properties.load(fis);//加载流对应的文件
            String name = properties.getProperty("name");
            String password = properties.getProperty("password");
            System.out.println("您的名字："+name+"\n您的密码："+password);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}










































/*
我已经没有任何的竞争力了。
为什么，我的愿望十分简单，但是不可能会存在。












世界真是xx
 */