package com.guyi.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * 非阻塞传输
 *
 *
 *  * 一. 使用NIO完成网络通信的三个核心
 *  * 1.通道（channel）：负责连接
 *  *   java.nio.channels.Channel 接口
 *  *      |--- SelectableChannel
 *  *          |--- SocketChannel
 *  *          |--- ServerSocketChannel
 *  *          |--- DatagramChannel
 *  *          |--- Pipe.SinkChannel
 *  *          |--- Pipe.SourceChannel
 *  * 2.缓冲区（buffer）：负责数据的存取
 *  * 3.选择器（selector）：是SelectableChannel的多路复用器， 用于监控SelectableChannel的IO状况
 *  *
 *
 */
public class NonBlockingNIO {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                server();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        client();
    }

    //客户端
    public static void client(){
        SocketChannel sc = null;
        try {
            // 获取通道
            sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 3000));
            // 切换到非阻塞模式
            sc.configureBlocking(false);
            // 分配指定的大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //读取数据发送到服务器
            buf.put((new Date().toString()).getBytes());
            buf.flip();
            sc.write(buf);
            buf.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sc != null)
                    sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //服务端
    public static void server() throws Exception{
        //获取通道
        ServerSocketChannel sc = ServerSocketChannel.open();
        // 切换到非阻塞模式
        sc.configureBlocking(false);
        //  绑定端口
        sc.bind(new InetSocketAddress(3000));
        // 获取选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器上, 并且指定监听事件
        sc.register(selector, SelectionKey.OP_ACCEPT);
        //轮询式的获取选择器上已经"准备就绪"的事件
        while (selector.select() > 0){
            // 获取当前选择器中所有注册的"选择键(已经'准备就绪'的事件)"
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                // 获取准备“就绪”的事件
                SelectionKey next = iterator.next();
                // 如果是可接收事件
                if (next.isAcceptable()){
                    // 获取客户换的通道
                    SocketChannel sc1 = sc.accept();
                    // 切换非阻塞模式
                    sc1.configureBlocking(false);
                    //将该通道注册到选择器上
                    sc1.register(selector, SelectionKey.OP_READ);
                }else if (next.isReadable()){ // 如果是可读事件
                     SocketChannel sChannel = (SocketChannel)next.channel();
                    // 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len  =0;
                    while ((len = sChannel.read(buf)) != -1){
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
            }
            // 取消选择键
            iterator.remove();
        }
    }
}
