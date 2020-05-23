package com.IOStream;

import java.io.*;

/**
 *  一· 流的分类：
 *      1.操作数据单位： 字节流
 *      2.数据的流向： 输入流，输出流
 *      3.流的角色： 节点流，处理流
 *
 * 二· 流的体系结构：
 * 1.抽象基类： InputStream  OutputStream Reader Writer
 * 2.节点流(文件流)： FileInputStream FileOutputStream FileReader FileWriter
 * 3.缓冲流(处理流的一种)：BufferedInputStream BufferedOutputStream BufferedReader BufferedWriter
 */
public class FileReaderWriterAndStreamTest {
    public static void main(String[] args){
//        testFileReader1();
//        System.out.println();
//        testFileReader2();
//        System.out.println();
//        testFileWrite();
//        System.out.println();
//        testFileReaderFileWriter();
        testFileStreamTest();
    }

    public static void testFileReader1(){
        File file = new File("olg.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            //fr.read()返回读入的一个字符，返回-1则读取的文件到了文件尾
            int data = fr.read();
            while(data != -1){
                System.out.print((char)data);
                data = fr.read();  //读取下一个字符
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭流
            try {
                if (fr != null)
                    fr.close();   //保证资源一定可以执行关闭操作
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void testFileReader2(){
        File file = new File("olg.txt");
        FileReader fr = null;
        try{
            fr = new FileReader(file);
            char[] chuck = new char[5];  //每次读取的数据保存在这个数组
            int length;   //记录读取的数据的个数
            //read(char[] cbuf): 返回每次读入cbuf数组中的字符个数，如果达到文件尾则返回-1
            while ((length = fr.read(chuck)) != -1){
//                for (char data : chuck){
//                    System.out.print(data);     //helloworld219ld
//                }

//                String str = new String(chuck);
//                System.out.print(str);          //helloworld219ld

//                for (int i =0; i< length; i++){
//                    System.out.print(chuck[i]); //helloworld219
//                }

                String string = new String(chuck,0,length);
                System.out.print(string);         //helloworld219
            }
        }catch(IOException e){
            e.getStackTrace();
        }finally{
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void testFileWrite(){
        File file = new File("dream.txt");
        FileWriter fw =null;
        try {
            fw = new FileWriter(file);
            //fw.write()指定的文件不存在就会创建文件，写入内容，覆盖原来的文件内容
            fw.write("I have a dream\n");
            //fw.write(xx, true) 在原来的文件内容后面追加内容
            fw.write("Yuo are good");
            System.out.println("添加成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null){
                try{
                    fw.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void testFileReaderFileWriter(){
        File srcFile = new File("dream.txt");
        File destFile = new File("olg.txt");
        FileReader fr = null;
        FileWriter fw = null;
        try{
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            //从srcFile中读取，写到destFile中
            char[] cbuf = new char[4];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                fw.write(cbuf,0,len);
            }
            System.out.println("传输成功");
        }catch (IOException e){
            e.getStackTrace();
        }finally {
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testFileStreamTest(){
        File srcFile = new File("xx.png");
        File destFile = new File("yy.png");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis =  new FileInputStream(srcFile);
            fos =  new FileOutputStream(destFile);
            byte[] buf = new byte[10];  //每次读取的数据保存在这个数组
            int len;     //记录读取的数据的个数
            long start = System.currentTimeMillis();
            while ((len = fis.read(buf)) != -1){
                fos.write(buf,0,len);
            }
            long end = System.currentTimeMillis();
            System.out.println("传输成功,用时:" + (end - start));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
