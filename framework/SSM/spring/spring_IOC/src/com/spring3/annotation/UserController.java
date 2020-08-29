package com.spring3.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired(required = false) //required=false避免没有相应bean而报错
    @Qualifier("userService") //指定装配哪个bean
    // 还可以使用 @Resource @Inject 自动装配, 一般使用@Autowire
    private UserService userService;

    public void execute(){
        System.out.println("useController execute()");
        userService.add();
    }
}
