package com.spring2.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean的后置处理器: 实现BeanPostProcessor接口
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    // bean调用初始化方法之前调用
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(bean);
        System.out.println("postProcessBeforeInitialization:"+beanName);
        return bean;
    }

    // bean调用初始化方法之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization:"+beanName);
        if ("car".equals(beanName)){
            Car car = (Car) bean;
            car.setBrand("ford");
        }
        return bean;
    }
}
