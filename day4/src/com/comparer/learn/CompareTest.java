package com.comparer.learn;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一：Java中的对象，正常情况下，只能进行比较操作： == 或 ！= ，
 * 要对多个对象进行排序，就需要比较其大小，可以实现Comparable 或 Comparator接口来比较大小
 *
 * 二。Comparable接口的使用: 1.指定类实现Comparable接口，2.重写compareTo()方法，自然排序
 * 三。Comparator接口的使用：若元素的类型没有实现Java.lang.Comparable接口而又不方便修改代码
 * 那么,可以使用实现Comparator接口的对象来排序,
 * 1.指定类实现Comparator接口(一般通过匿名对象来排序) 2.重写compare(Object o1,Object o2)方法,比较o1与o2的大小
 *
 */
public class CompareTest {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        String[] str = new String[]{"AA", "BB","GG", "CC","DD", "JJ"};
        //String实现了Comparable接口，重写了compareTo()方法，给出了比较的定义，可以从小到大排序
        Arrays.sort(str);
        //通过Comparator接口实现类的对象一次性的比较
        Arrays.sort(str,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String){
                    String s1 = (String)o1;
                    String s2 = (String)o2;
                    //这里没有实现Comparable接口的类需要自己定义比较内容
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("传入的数据类型不一致");
            }
        });
        System.out.println(Arrays.toString(str));

    }

    public static void test2(){
        Goods[] goods = new Goods[4];
        goods[0] = new Goods("联想", 378);
        goods[1] = new Goods("小米", 354);
        goods[2] = new Goods("华为", 323);
        goods[3] = new Goods("戴尔", 344);

        Arrays.sort(goods);//需要实现Comparable接口，并重写compareTo()方法
        System.out.println(Arrays.toString(goods));
    }
}

class Goods implements Comparable{
    private String name;
    private double price;

    public Goods(){}

    public Goods(String name,double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    // 定义比较的内容
    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods){
            Goods goods = (Goods)o;
            if (this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else{
                return this.name.compareTo(goods.name);  //如果两者的价格相等，则比较name
            }
            //简化: return Double.compare(this.price,goods.price)
        }
        throw new RuntimeException("传入的数据类型不一致");
    }
}


