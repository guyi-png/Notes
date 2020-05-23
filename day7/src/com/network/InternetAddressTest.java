package com.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 一。网络编程中的两个主要的问题：
 * 1.如何精确的定位网络上一台或多台主机，以及定位主机上特定的应用
 * 2.找到主机后如何可靠高效地进行数据的传输
 *
 * 二。网络编程的两个元素：
 * 1.IP和端口号
 * 2.提供网络通信协议，TCP/IP参考模型（应用层，传输层，网络层，物理+数据链路层）
 *
 * IP：Internet上的计算机的唯一的标识
 * 在Java中使用InteAddress类代表IP
 *
 * 本机ip: 127.0.0.1  dns: localhost
 *
 * 端口号： 标识正在计算机上运行的进程
 * 不同的进程有不同的端口号
 * 范围： 规定一个16位的整数： 0~65535
 * 端口号与IP地址的组合的得出一个网络套接字： Socket
 *
 */
public class InternetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("192.168.0.1"); //通过IP地址
            System.out.println(ip);

            InetAddress ip1= InetAddress.getByName("www.google.com"); //通过域名
            System.out.println(ip1);

            InetAddress localHost = InetAddress.getLocalHost(); //本机地址
            System.out.println(localHost);

            System.out.println(ip1.getHostName());   //获得主机名
            System.out.println(ip1.getHostAddress());  //获得主机地址
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
