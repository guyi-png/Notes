package com.spring1.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


public class LoggingAspect {
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName+" start");
    }

    public void afterMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + " end");
    }

    public void afterReturning(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + " executed");
        System.out.println("返回值为"+result);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception exception){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+" exception: \n"+exception);
    }

    public Object aroundMethod(ProceedingJoinPoint pjp){
        Object result = null;
        String methodName = pjp.getSignature().getName();
        try {
            // 前置通知
            System.out.println(methodName+"方法开始执行");
            // 执行目标方法
            result = pjp.proceed();
            // 返回通知
            System.out.println(methodName+"正常执行完了");
        } catch (Throwable throwable) {
            // 异常通知
            System.out.println(methodName+"出现异常"+throwable);
            throwable.printStackTrace();
        }
        // 后置通知
        System.out.println(methodName+"执行完了，过了");
        return result;
    }
}
