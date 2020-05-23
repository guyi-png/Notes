package com.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 */
public class UDPTest {
    //发送端
    public void sender(){
        DatagramSocket socket = null;  //datagram数据电报
        try {
            socket = new DatagramSocket();
            byte[] data = new String("奥里给奥里给").getBytes();
            DatagramPacket packet = new DatagramPacket(data,0,data.length, InetAddress.getLocalHost(),8080);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (socket != null)
                socket.close();
        }
    }
    //接收端
    public void receiver(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8080);
            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(),0,packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }
}
