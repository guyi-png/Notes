package com.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class TcpTest1 {
    public static void client(){
        OutputStream os = null;
        try {
            //1.创建socket对象，指明服务器的ip和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            Socket socket = new Socket(inet, 8080);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("奥里给".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器的ServerSocket，指明自己的端口号
            ss = new ServerSocket(8080);
            //2.调用accept()表示接收来自客户端的socket
            socket = ss.accept();
            //3.获取一个输入流
            is = socket.getInputStream();
//        byte[] buffer = new byte[1024];
//        int len;
//        while((len = is.read(buffer)) != -1){
//            String str = new String(buffer,0,len);
//            System.out.println(str);  //可能乱码
//        }
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[20];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            if (baos != null)
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


