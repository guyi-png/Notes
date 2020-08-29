package com.guyi.NIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 *      // 通道相当于实际的道路
 *  一. 通道(channel): 用于源节点与目标节点的连接，在Java中负责缓冲区中数据的传输，
 *  Channel本身没有存储数据， 所以需要配合缓冲区进行传输
 *
 * 二.通道的主要实现类
 * java.nio.channels.Channel 接口
 *      |--- FileChannel
 *      |--- SocketChannel
 *      |--- ServerSocketChannel
 *      |--- DatagramChannel
 *
 * 三. 获取通道
 * 1.java针对支持通道的类提供了getChannel()获取通道
 * 本地io：
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * 网络io：
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2.各个通道可以通过静态方法open()
 * 3.Files工具类的newByteChannel()
 *
 * 四.通道之间的数据交换
 * transferFrom()
 * transferTo()
 *
 * 五. 分散(Scatter) and 聚焦(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚焦希尔（Gathering Writes）: 将多个缓冲区的数据聚焦到通道中
 *
 * 六. 字符集
 * 编码：字符串 -> 字节数组
 * 解码：字节数组 -> 字符串
 */
public class Channel {
    public static void main(String[] args) {
        test4();
    }

    public static void test(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel ic = null;
        FileChannel oc = null;
        try {
            fis = new FileInputStream("xx.png");
            fos = new FileOutputStream("yy.png");
            //获取通道
            ic = fis.getChannel();
            oc = fos.getChannel();
            //分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读写
            while (ic.read(buffer) != -1){
                buffer.flip();//切换读取数据模式
                oc.write(buffer);
                buffer.clear(); // 清空缓冲区，让车上的乘客都下车
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oc != null)
                    oc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ic != null)
                    ic.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test1(){
        FileChannel ic = null;
        FileChannel oc = null;
        try {
            ic = FileChannel.open(
                    Paths.get("D:\\JAVA\\lEARN\\idea_learn\\workspace", "xx.png"),
                    StandardOpenOption.READ
            );
            oc = FileChannel.open(
                    Paths.get("D:\\JAVA\\lEARN\\idea_learn\\workspace", "zz.png"),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ
            );
            // 内存映射文件，映射到物理内存
            MappedByteBuffer inBuffer = ic.map(FileChannel.MapMode.READ_ONLY, 0, ic.size());
            MappedByteBuffer outBuffer = oc.map(FileChannel.MapMode.READ_WRITE, 0, ic.size());

            // 直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inBuffer.limit()];
            inBuffer.get(dst);
            outBuffer.put(dst);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (ic != null)
                    ic.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oc != null)
                    oc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test2(){
        //通道之间的数据传输（直接缓冲区）
        FileChannel ic = null;
        FileChannel oc = null;
        try {
            ic = FileChannel.open(Paths.get("xx.png"), StandardOpenOption.READ);
            oc = FileChannel.open(Paths.get("ss.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

//            ic.transferTo(0, ic.size(), oc);  // ic -> oc
            oc.transferFrom(ic, 0, ic.size()); // ic -> oc

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ic != null)
                    ic.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oc != null)
                    oc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test3(){
        RandomAccessFile raf = null;
        FileChannel channel = null;
        RandomAccessFile raf1 = null;
        try {
            raf = new RandomAccessFile("xx.txt","rw");
            channel = raf.getChannel();

            ByteBuffer buf1 = ByteBuffer.allocate(1024);
            ByteBuffer buf2 = ByteBuffer.allocate(2048);

            //分散读取
            ByteBuffer[] bufs = {buf1, buf2};
            channel.read(bufs);

            //将缓冲区模式转为读取
            for (ByteBuffer byteBuffer : bufs){
                byteBuffer.flip();
            }

            // 聚焦写入
            raf1 = new RandomAccessFile("yy.txt", "rw");
            FileChannel channel1 = raf1.getChannel();
            channel1.write(bufs);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null)
                    raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (raf1 != null)
                    raf1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (channel != null)
                    channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (channel != null)
                    channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void test4(){
        // 获取可用的字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> charSet : set){
            System.out.println(charSet.getKey() +" = " + charSet.getValue());
        }

        System.out.println("---------------------------");

        Charset cs = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder encoder = cs.newEncoder();
        // 获取解码器
        CharsetDecoder decoder = cs.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("奥利给");
        charBuffer.flip();
        //编码
        ByteBuffer byteBuffer = null;
        try {
             byteBuffer = encoder.encode(charBuffer);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

        //解码
        CharBuffer charBuffer1 = null;
        try {
            assert byteBuffer != null;
            charBuffer1 = decoder.decode(byteBuffer);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

        assert charBuffer1 != null;
        System.out.println(charBuffer1.toString());
    }
}
