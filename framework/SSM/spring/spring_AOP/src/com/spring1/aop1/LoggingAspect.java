package com.spring1.aop1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * 日志切面, 需要将类声明为一个切面, 还要将该类放到ioc容器中
 *
 *
 * 面向切面的几个概念：
 * Aspect切面： Aspect声明类似于 Java 中的类声明，
 * 在 Aspect 中会包含着一些 Pointcut 以及相应的 Advice。
 *
 * Join point连接点：表示在程序中明确定义的点，典型的包括方法调用，
 * 对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它 joint point。
 *
 * Pointcut切入点：表明在哪些类，哪些方法上切入.表示一组 joint point，
 * 这些 join point 或是通过逻辑关系组合起来，
 * 或是通过通配、正则表达式等方式集中起来，它定义了相应的 Advice 将要发生的地方。
 *
 * Advice通知：Advice 定义了在 pointcut 里面定义的程序点具体要做的操作，
 * 它通过before、after和around来区别是在每个joint point之前，之后还是代替执行的代码。
 *
 * 5种类型的通知注解：
 *      - @Before
 *      - @After
 *      - @AfterReturning
 *      - @AfterThrowing
 *      - @Around
 */
@Aspect
@Component
public class LoggingAspect {
    /*
        定义一个方法，用于声明切入点表达式，该方法中不需要添加其他的代码,
        用@Pointcut声明切入点表达式
     */
    @Pointcut(value = "execution(public int *(..))")
    public void declarePointcut(){}

    // 前置通过，在目标方法执行前执行
    @Before("execution(public int add(int,int))") // 声明方法是一个前置通知，在目标方法开始前执行
    public void beforeMethod(JoinPoint joinPoint){

        String modifier = Modifier.toString(joinPoint.getSignature().getModifiers()); // public abstract
        String typeName = joinPoint.getSignature().getDeclaringTypeName(); // Calculator
        String methodName = joinPoint.getSignature().getName(); // add
        Object[] args = joinPoint.getArgs(); // 参数值

        System.out.println(modifier + " "+typeName+ " "+ methodName+" ("+args[0]+","+args[1]+")");
        System.out.println(methodName+" start");
    }

    // 后置通过，在目标方法执行后执行(无论该方法是否会有异常)
    @After("declarePointcut()") // 声明方法是一个后置通知，调用切入点表达式
    public void afterMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + " end");
    }

    // 返回通知，在目标方法正常执行后执行, 返回通知 可以访问该方法的返回值
    @AfterReturning(value = "execution(int div(int, int))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "executed");
        System.out.println("返回值为"+result);
    }

    // 异常通知，在目标方法执行出异常后执行,可以访问到异常对象，且可以指定出现特定异常时在执行通过代码
    @AfterThrowing(value = "execution(* div(int,int))", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"exception: \n"+exception);
    }

    // 环绕通知， 需要携带ProceedingJoinPoint类型的参数, 环绕通知类似于动态代理的全过程，
    // pjp参数可以决定是否执行目标方法， 环绕通知必须有返回值，这返回值就是目标方法的返回值
    @Around(value = "execution(public int mul(int, int ))")
    public Object aroundMethod(ProceedingJoinPoint pjp){
        Object result = null;
        String methodName = pjp.getSignature().getName(); //mul
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
        System.out.println(methodName+"执行完了");
        return result;
    }

}
