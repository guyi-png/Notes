package com.IOStream;

import java.io.*;

/**
 * 1.缓冲流：
 * BufferedInputStream/
 * BufferedOutputStream/
 * BufferedReader/
 * BufferedWriter
 *
 * 2.作用： 提高流的读取，写入的速度
 * 3.原因： 内部提供了一个缓冲区DEFAULT_BUFFER_SIZE = 8192;
 *
 */
public class BufferedTest {
    public static void main(String[] args) {
        bufferedStreamTest();
        bufferedReaderWriter();
    }

    public static void bufferedStreamTest(){
        File srcFile = new File("xx.mp4");
        File destFile = new File("yy.mp4");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
//            //建立节点流
//            FileInputStream fis = new FileInputStream(srcFile);
//            FileOutputStream fos = new FileOutputStream(destFile);
            //在节点流的基础上创建缓存流
            bis = new BufferedInputStream(new FileInputStream(srcFile));
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            //传输
            byte[] buffer = new byte[1024];
            int len;
            long start = System.currentTimeMillis();
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0 ,len);
//                bos.flush();  xx.flush()刷新缓冲区
            }
            long end = System.currentTimeMillis();
            System.out.println("传输成功，用时："+(end -start)+"毫秒");//539毫秒
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流的顺序：先关闭外层，再关闭内层
//            bis.close();
//            bos.close();
//            fis.close();
//            fos.close();
            //当关闭外层的流的同时，内层流也会关闭
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bufferedReaderWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File("dream.txt")));
            bw = new BufferedWriter(new FileWriter(new File("olg.txt")));
//            char[] cbuf = new char[10];
//            int len;
//            while((len = br.read(cbuf)) != -1){
//                bw.write(cbuf, 0 ,len);
//    //            bw.flush();
//            }
            String data;
            //xx.readLine()返回每行的数据不包括换行符，到文件尾返回null
            while ((data = br.readLine()) != null){
                bw.write(data);
                bw.newLine(); //添加换行
            }
            System.out.println("传输成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bw != null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
