package com.guyi.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一. 使用NIO完成网络通信的三个核心
 * 1.通道（channel）：负责连接
 *   java.nio.channels.Channel 接口
 *      |--- SelectableChannel
 *          |--- SocketChannel
 *          |--- ServerSocketChannel
 *          |--- DatagramChannel
 *          |--- Pipe.SinkChannel
 *          |--- Pipe.SourceChannel
 * 2.缓冲区（buffer）：负责数据的存取
 * 3.选择器（selector）：是SelectableChannel的多路复用器， 用于监控SelectableChannel的IO状况
 *
 */
public class BlockingNIO {
    public static void main(String[] args) {
        server();
    }

    //客户端
    public static void client(){
        SocketChannel sc = null;
        FileChannel ic = null;
        try {
            // 获取通道
            sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 3000));
            // 创建缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 获取本地资源通道
            ic = FileChannel.open(Paths.get("xx.png"), StandardOpenOption.READ);

            // 读取本地资源，并发送到服务器
            while ( ic.read(buf) != -1){
                buf.flip();
                sc.write(buf);
                buf.clear();
            }

            // 关闭数据的输出
            sc.shutdownOutput();

            // 获取服务器的反馈
            int len =0;
            while ((len = sc.read(buf)) != -1){
                buf.flip();
                System.out.print(new String(buf.array(), 0 , len));
                buf.clear();
            }

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
                if (sc != null)
                    sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 服务端
    public static void server(){
        ServerSocketChannel sc = null;
        SocketChannel sc1 = null;
        FileChannel oc = null;
        try {
            // 获取通道
            sc = ServerSocketChannel.open();
            // 绑定连接
            sc.bind(new InetSocketAddress(3000));
            //获取客户端连接的通道
            sc1 = sc.accept();
            // 输出的通道
            oc = FileChannel.open(Paths.get("yy.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            // 创建缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 读取服务器资源，并输出到本地
            while (sc1.read(buf) != -1){
                buf.flip();
                oc.write(buf);
                buf.clear();
            }
            //向客户端发信息
            buf.put("您的数据我们收到了，感谢".getBytes());
            buf.flip();
            sc1.write(buf);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sc != null)
                    sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sc1 != null)
                    sc1.close();
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
}
