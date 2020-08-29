package com.spring1.aop1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)  // @Order(value) 指定切面的优先级，value值越小越优先
@Aspect
@Component
public class ValidateAspect {
    @Before(value = "com.spring1.aop1.LoggingAspect.declarePointcut()")
    public void beforeMethod(){
        System.out.println("validate begin");
    }
}
