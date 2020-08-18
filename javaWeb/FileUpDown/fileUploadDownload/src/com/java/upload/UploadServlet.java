package com.java.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 获取请求的文件并保存到数据库
 */
public class UploadServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ServletFileUpload upload = getFileUpload();
        List<FileItem> fileItems = null;
        try {
            // 解析request的信息,得到List<FileItem>对象fileItems
            fileItems = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        // assume we know there are two files. The first file is a small
        // text file, the second is unknown and is written to a file on
        // the server
        // 遍历filesItems，若是表单域打印信息，若是文件域保存到磁盘中
        assert fileItems != null;

        for (FileItem fi : fileItems) {
            if (fi.isFormField()) {
                // 若是表单域打印信息
                String fieldName = fi.getFieldName();
                String comment = fi.getString();
                System.out.println(fieldName+":"+comment);
            } else {
                //若是文件域保存到磁盘中
                // filename on the client
                String fileName = fi.getName();
                // save comment and filename to database
                // write the file
                try {
                    fi.write(new File("D:\\JAVA\\lEARN\\idea_learn\\javaWeb\\FileUpDown\\fileUpload\\web",
                            fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ServletFileUpload getFileUpload(){
        // 读取配置信息
        Map<String, String> properties = UploadProperties.getInstance().getProperties();
        long totalMaxSize = Long.parseLong(properties.get("totalMaxSize"));
        long fileMaxSize = Long.parseLong(properties.get("fileMaxSize"));

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(4096);
        // the location for saving data that is larger than getSizeThreshold()
        File tempDirectory = new File("D:\\JAVA\\lEARN\\idea_learn\\javaWeb\\FileUpDown\\fileUpload\\web");
        factory.setRepository(tempDirectory);

        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum size before a FileUploadException will be thrown
        upload.setSizeMax(totalMaxSize);
        // maximum value per file
        upload.setFileSizeMax(fileMaxSize);

        return upload;
    }
}
