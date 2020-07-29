package com.guyi.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
/**
    * 利用Java图片工具 Graphics 制作一次性验证码图片
    * 思路把HTML img src属性 动态改变
*/
public class ValidateCode extends HttpServlet {
    public static final String CHECK_CODE_KEY = "CHECK_CODE_KEY";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final int width = 140; // 图片宽度
        final int height = 40; // 图片高度
        final String imgType = "jpg"; // 指定图片格式 (不是指MIME类型)
        final OutputStream output = response.getOutputStream(); // 获得可以向客户端返回图片的输出流

        // 创建验证码图片并返回图片上的字符串
        String code = create(width, height, imgType, output);

        // 返回验证码
        request.getSession().setAttribute(CHECK_CODE_KEY, code);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 以字符串形式返回生成的验证码 同时输出一个图片
     *
     * @param width   图片的宽度
     * @param height  图片的高度
     * @param imgType 图片的类型
     * @param output  图片的输出流(图片将输出到这个流中)
     * @return 返回所生成的验证码(字符串)
     */
    public static String create(final int width, final int height, final String imgType, OutputStream output) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();

        graphic.setColor(Color.getColor("000000"));
        graphic.fillRect(0, 0, width, height);

        Color[] colors = new Color[] { Color.BLUE, Color.PINK, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,
                Color.DARK_GRAY };
        // 在 "画板" 上生成干扰线条 ( 40 是线条个数)
        for (int i = 0; i < 40; i++) {
            graphic.setColor(colors[random.nextInt(colors.length)]);
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);
            final int w = random.nextInt(20);
            final int h = random.nextInt(20);
            final int signA = random.nextBoolean() ? 1 : -1;
            final int signB = random.nextBoolean() ? 1 : -1;
            graphic.drawLine(x, y, x + w * signA, y + h * signB);
        }

        graphic.setFont(new Font("Default", Font.BOLD, 30));
        for (int i = 0; i < 5; i++) {
            String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            String s = String.valueOf(temp.charAt(random.nextInt(52)));
            sb.append(s);
            graphic.setColor(colors[random.nextInt(colors.length)]);
            graphic.drawString(s, i * (width / 5), height - (height / 3));
        }
        graphic.dispose();
        try {
            ImageIO.write(image, imgType, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}