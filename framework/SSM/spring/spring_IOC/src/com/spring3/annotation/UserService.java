package com.spring3.annotation;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void add(){
        System.out.println("userService add()");
    }
}
