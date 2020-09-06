package com.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 网站工厂类，根据需要返回一个网站
 */
public class WebsiteFactory {
    // 集合， 模拟池
    private Map<String, Website> map = new HashMap<>();

    public Website getWebSiteCategory(String type){
        if (!map.containsKey(type)){
            map.put(type, new ConcreteWebsite(type));
        }
        return map.get(type);
    }

    // 获取网站分类的数量
    public int getWebSiteCount(){
        return map.size();
    }
}
