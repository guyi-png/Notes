package com.java.upload;


import java.util.HashMap;
import java.util.Map;

/**
 * 保存配置信息的类
 */
public class UploadProperties {
    private final Map<String, String> properties = new HashMap<>();

    private UploadProperties(){}

    private static final UploadProperties instance = new UploadProperties();

    public static UploadProperties getInstance(){
        return instance;
    }

    public void addProperties(String propertyName, String propertyValue) {
        properties.put(propertyName, propertyValue);
    }

    public Map<String, String> getProperties(){
        return properties;
    }
}
