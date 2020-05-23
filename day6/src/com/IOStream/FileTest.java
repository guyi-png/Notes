package com.IOStream;

import java.io.File;
import java.io.IOException;

/**
 * File类的使用：
 * 1.file类的一个对象，代表一个文件或一个文件目录（文件夹）
 * 2.File类位于java.io包
 *
 * 相对路径和绝对路径
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
    public static void test1(){
        File file = new File("hello.txt");
        File file1 = new File("D:\\JAVA\\lEARN\\idea_learn\\workspace\\day6\\src\\com\\IOStream\\hi.txt");
        File file2 = new File("D:\\JAVA\\lEARN\\idea_learn\\workspace\\day6\\src\\com\\IOStream\\", "hei.txt");

        System.out.println(file);
        System.out.println(file1);
        System.out.println(file2);
        System.out.println();

        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.lastModified());
        System.out.println();

        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());


    }

    public static void test2(){
        File file = new File("D:\\JAVA\\lEARN\\idea_learn\\workspace");
        String[] list =  file.list();   //返回该文件目录下的所有的文件的名字
        for (String fileName : list){
            System.out.println(fileName);
        }
        File[] files = file.listFiles(); //返回该文件目录下的所有的文件
        for (File f : files){
            System.out.println(f);
        }
    }

    public static void test3(){
        File file = new File("hello.txt");
        File file1 = new File("D:\\JAVA\\lEARN\\idea_learn\\workspace\\olg.txt");

        //public boolean renameTo(File dest): 把文件移动到指定的文件路径并重命名
        // 要想修改成功，需要保证在硬盘中file存在，file1不存在
        boolean renameTo = file.renameTo(file1);
        System.out.println(renameTo);
    }

    public static void test4(){
        File file = new File("olg.txt");
        System.out.println();
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.exists());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.isHidden());

    }

    public static void test5() throws IOException {
        File file = new File("hello.txt");
        if (!file.exists()){
            file.createNewFile();    //创建文件
            System.out.println("创建成功");
        }else{
            file.delete();       //删除文件
            System.out.println("删除成功");
        }

        File file1 = new File("D:\\java\\LEARN");

        if (!file1.exists()){
            boolean mkdir = file1.mkdir();  //创建目录，若上一个目录没有，则不创建
//            boolean mkdirs = file1.mkdirs(); //创建目录，若上一个目录没有，则一同创建
            System.out.println("cj"+mkdir);
        }else{
            boolean del = file1.delete();
            System.out.println("del"+del);
        }
    }
}
