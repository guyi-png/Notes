package com.guyi.NIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 使用Pipe
 */
public class PipeTest {
    public static void main(String[] args) {
        test();
    }


    public static void test(){
        Pipe.SinkChannel sink = null;
        Pipe.SourceChannel source = null;
        try {
            //  获取管道
            Pipe pipe = Pipe.open();
            // 将数据写入到管道
            sink = pipe.sink();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.put("干就完了".getBytes());
            buf.flip();
            sink.write(buf);
            // 读取管道中缓冲区的数据
            source = pipe.source();
            buf.flip();
            source.read(buf);
            System.out.println(new String(buf.array(), 0, buf.limit()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sink != null)
                    sink.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (source != null)
                    source.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
