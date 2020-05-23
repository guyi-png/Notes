package com.IOStream;

import java.io.*;

/**
 *  对象流的使用：
 *      1.ObjectInputStream
 *      2.ObjectOutputStream
 *  作用：
 *      用于存储和读取基本数据类型或对象的处理流，
 *      它的强大之处就是可以把Java的对象写入到数据源中，
 *      也能把对象从数据源中读取出来
 */
public class ObjectStream {
    /*
        序列化过程： 将内存中的Java对象保存到磁盘中或通过网络传输出去，
        由ObjectOutputStream实现
        反序列化过程：将磁盘中或通过网络传输得到的对象还原为内存中的一个Java对象
        由ObjectInputStream实现

        需要序列化的对象，其类要实现Serializable接口，并提供一个全局常量serialVersionUID
        若不提供serialVersionUID，会自动生成对应的serialVersionUID，但此serialVersionUID
        会因为类的修改而找不到反序列化的途径，而报错
        需要序列化的对象的类中所有的属性也必须可序列化
        需要序列化的对象的类中静态属性不可序列化，被static和transient修饰就不能序列化
             */
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("log.dat"));
            oos.writeObject(new String("奥里给"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void test2(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("log.dat"));
            String str = (String)ois.readObject();
            System.out.println();
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

