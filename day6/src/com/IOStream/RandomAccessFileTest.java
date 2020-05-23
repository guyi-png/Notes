package com.IOStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile使用
 * RandomAccessFile继承于java.lang.object类，实现了DataInput，DataOutput接口
 * RandomAccessFile可以作为输入流和输出流
 */
public class RandomAccessFileTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        RandomAccessFile raf1= null;
        RandomAccessFile raf2= null;
        try {
            raf1 = new RandomAccessFile(new File("olg.txt"),"rw");
            raf2 = new RandomAccessFile(new File("dream.txt"),"rw");
            byte[] buffer= new byte[1024];
            int len;
            while((len = raf1.read(buffer)) != -1){
                raf2.write(buffer,0,len);
            }

            raf2.seek(raf2.length());    //将指针调到指定的位置
            raf2.write("AbCdEf".getBytes("UTF-8"));//插入指定的内容
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf1 != null)
                    raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (raf2 != null)
                    raf2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
