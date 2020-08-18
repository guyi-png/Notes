package com.java.upload;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件
 */
@WebListener()
public class FileUploadListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public FileUploadListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
        // 读取配置文件
        InputStream is =
                getClass().getClassLoader().getResourceAsStream("upload.properties");
        Properties properties = new Properties();
        UploadProperties instance = UploadProperties.getInstance();
        try {
            properties.load(is);
            for (Map.Entry<Object, Object> entry : properties.entrySet()){
                instance.addProperties((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}
