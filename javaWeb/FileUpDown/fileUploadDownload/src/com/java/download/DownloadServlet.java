package com.java.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/x-msdownload");
        String fileName = "tsl.jpg";
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        // 响应时下载文件
        ServletOutputStream os = response.getOutputStream();
        File file = new File("D:\\JAVA\\lEARN\\idea_learn\\javaWeb" +
                "\\FileUpDown\\fileUpload\\web\\tsl.jpg");
        InputStream is = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len=is.read(buffer)) != -1){
            os.write(buffer, 0, len);
        }
        is.close();
    }
}
