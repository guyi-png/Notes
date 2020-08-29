package com.spring3.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 使用注解配置bean
 *
 * Spring能够从classpath下自动扫描，侦测和实例化具有特定注解的组件
 * 特定的组件包括：
 *  - @Component：基本的注解，标识了一个受Spring管理的组件
 *  - @Repository：标识持久层组件
 *  - @Service：标识服务层组件
 *  - @Controller：标识表现层组件
 *  对于扫描到的组件，spring有默认的命名策略，使用非限定的类名，第一个字母小写
 *  也可以在注解中通过value属性值标识组件的名称
 */
@Component
public class UserObject {

    public static void main(String[] args) {
        ApplicationContext ctx  = new
                ClassPathXmlApplicationContext("beans-annotation.xml");
        UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
        userRepository.save();

        UserService userService = (UserService) ctx.getBean("userService");
        userService.add();

        UserController userController = (UserController) ctx.getBean("userController");
        userController.execute();

        UserObject userObject = (UserObject) ctx.getBean("userObject");
        System.out.println(userObject);
    }

}
