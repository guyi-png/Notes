package com.IOStream;

import java.io.*;

/**
 * 转换流 (属于字符流):
 * InputStreamReader
 * OutputStreamWriter
 * 作用： 将字节流转化为字符流
 *
 * 解码： 字节，字节数组  --> 字符，字符数组
 * 编码： 字符，字符数组  --> 字节，字节数组
 *
 * 字符集：
 */
public class TransformStreamTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1(){
        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream("dream.txt");
//        InputStreamReader isr = new InputStreamReader(fis,charName)
//        第二个参数，具体使用哪个字符集取决于当时文件保存时使用的编码集
            isr = new InputStreamReader(fis, "UTF-8");
            char[] cbuf = new char[4];
            int len;
            while((len = isr.read(cbuf)) != -1){
                String str = new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test2(){
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            FileInputStream fis = new FileInputStream("dream.txt");
            FileOutputStream fos = new FileOutputStream("olg.txt");
            isr = new InputStreamReader(fis, "UTF-8");
            osw = new OutputStreamWriter(fos, "gbk");
            char[] cbuf = new char[4];
            int len;
            while((len = isr.read(cbuf)) != -1){
                osw.write(cbuf,0,len);
            }
            System.out.println("成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null)
                    isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (osw != null)
                    osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


