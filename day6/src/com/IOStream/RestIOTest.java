package com.IOStream;

import java.io.*;

/**
 * 其他流的使用：
 *  1.标准的输入，输出流
 *  2.打印流
 *  3.数据流
 */
public class RestIOTest {
    /*
    1.标准的输入输出流
    System.in: 标准的输入流，默认从键盘输入
    system.out: 标准的输出流，默认从控制台输出
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            String data;
            while(true){
                System.out.println("请输入需要大写的字符串");
                data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)){
                    System.out.println("程序退出");
                    break;
                }
                String upperCase = data.toUpperCase();  //将键盘的输入改为大写
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    2.打印流
    PrintStream
    PrintWriter
    调整打印的位置
     */
    /*
    3.数据流
    DataInputStream
    DataOutputStream
    用于读取或写入基本数据类型的变量或字符串
     */
//    public static void test(){
//        DataOutputStream dos = new DataOutputStream(new FileOutputStream("xx.txt"));
//        dos.writeUTF("xxx");
//        dos.writeBoolean(true);
//        dos.writeDouble(1.11);
//        DataInputStream dis = new DataInputStream(new FileInputStream("xx.txt"));
//        //读取数据时属性要和写入的一致
//        dis.readUTF();
//        dis.readBoolean();
//        dis.readDouble();
//    }
}
