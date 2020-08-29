package com.guyi.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 *  使用 DatagramChannel
 */
public class NonBlockingNIO2 {
    public static void main(String[] args) throws Exception{
        new Thread(()->{
            try {
                receive();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        send();
    }

    public static void send() throws Exception{
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.next();
            buf.put((new Date().toString()+"\n"+str).getBytes());
            buf.flip();
            dc.send(buf, new InetSocketAddress("127.0.0.1", 3000));
            buf.clear();
        }
        dc.close();
    }
    
    public static void receive() throws Exception{
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.bind(new InetSocketAddress(3000));
        Selector selector = Selector.open();
        dc.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey sk = iterator.next();
                if (sk.isReadable()){
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    dc.receive(buf);
                    buf.flip();
                    System.out.println(new String(buf.array(),0, buf.limit()));
                    buf.clear();
                }
            }
            iterator.remove();
        }
    }
}
