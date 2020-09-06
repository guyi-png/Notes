package com.bridge;

/**
 * 翻盖的样式。
 *      翻盖 拼 音 fān gài
 */
public class FlipPhone extends Phone{
    public FlipPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        System.out.print("翻盖式的");
        super.open();
    }

    public void close(){
        System.out.print("翻盖式的");
        super.close();
    }

    public void call(){
        System.out.print("翻盖式的");
        super.call();
    }
}
