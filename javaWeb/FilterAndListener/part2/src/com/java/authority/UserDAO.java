package com.java.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作数据
 */
public class UserDAO {
    private static Map<String, User> users;
    private static List<Authority> authorities;

    static {
         // 简单起见，直接获得数据
        users = new HashMap<>();
        authorities = new ArrayList<>();
        authorities.add(new Authority("article1", "/article1.jsp"));
        authorities.add(new Authority("article2", "/article2.jsp"));
        authorities.add(new Authority("article3", "/article3.jsp"));
        authorities.add(new Authority("article4", "/article4.jsp"));

        User user1 = new User("AA", authorities.subList(0,2));
        User user2 = new User("BB", authorities.subList(2,4));
        users.put("AA", user1);
        users.put("BB", user2);
    }

    public User get(String username){
        return users.get(username);
    }

    public void update(String username, List<Authority> authorities){
        users.get(username).setAuthorities(authorities);
    }

    public List<Authority> getAuthorities(){
        return authorities;
    }

    public List<Authority> getAuthorities(String[] urls){
        List<Authority> authorities1 =  new ArrayList<>();
        if (urls != null){
            for (String url : urls){
                for (Authority authority : authorities){
                    if (url.equals(authority.getUrl())){
                        authorities1.add(authority);
                        break;
                    }
                }
            }
        }
        return authorities1;
    }
}
