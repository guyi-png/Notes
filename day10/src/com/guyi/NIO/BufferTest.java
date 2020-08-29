package com.guyi.NIO;

import java.nio.ByteBuffer;

/**
 * NIO(new IO)
 *
 *          // 缓存区相当于公共交通工具
 *
 * 一. 缓冲区： Java NIO 中负责数据的存取，缓冲区就是数组，用于存储不同数据类型的数据
 * 根据基本数据类型，提供了相应类型的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 以上都是通过allocate(int capacity)获取缓存区
 *
 * 二。缓存区存取数据的核心方法
 * put(): 存入数据到缓冲区
 * get()：获取缓冲区的数据
 *
 * 三。缓冲区中的四个核心方法
 * capacity: 容量，表示缓冲区中最大存储数据的容量，一旦声明大小就不可修改
 * limit： 界限， 表示缓冲区中可以操作数据的大小，limit后面的数据不能进行读写
 * position：位置，表示缓冲区中正在操作的数据位置
 * mark: 标记， 表示记录当前的position，可以通过reset()恢复mark的位置
 *   0 <= mark <= position <= limit <= capacity
 *
 * 非直接缓冲区： 通过allocate方法分配缓冲区，将缓冲区建立在JVM的内存上
 * 直接缓冲区： 通过allocateDirect方法分配缓冲区，将缓冲区建立在物理内存中，可以提高效率
 */
public class BufferTest {
    public static void main(String[] args) {
            test3();
    }

    public static void test(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //一开始position为0, limit为1024，capacity为1024
        String str = "abcdefg";
        // 存入数据
        byteBuffer.put(str.getBytes());   //此时position为str.length, limit为1024，capacity为1024
        // 切换缓冲区的模式
        byteBuffer.flip();  //此时position为0, limit为str.length，capacity为1024
        // 读取
        byte[] dst = new byte[byteBuffer.limit()]; //limit大小为实际存入的数据量
        byteBuffer.get(dst);    //此时position为str.length, limit为str.length，capacity为1024
        String data = new String(dst, 0, dst.length);
        System.out.println(data);
        // rewind:可以重复读数据
        byteBuffer.rewind();    //此时position为0, limit为str.length，capacity为1024
        // clear: 重置缓冲区， 但是缓冲区中的数据还存在，处于"被遗忘"状态
        byteBuffer.clear(); //此时position为0, limit为1024，capacity为1024
        System.out.println((char)byteBuffer.get());  //a
    }

    public static void test1(){
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("abcde".getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0 , 2);
        System.out.println(buf.position());
        //标记position, 2
        buf.mark();
        buf.get(dst, 2,2);
        System.out.println(buf.position());
        // reset让position到2
        buf.reset();
        System.out.println(buf.position()); // 2

        if (buf.hasRemaining()){ //判断是否还有剩余的数据
            System.out.println(buf.remaining()); //有多少
        }
    }

    public static void test3(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());  //true
    }
}
