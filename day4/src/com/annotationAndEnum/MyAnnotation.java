package com.annotationAndEnum;

import java.lang.annotation.*;

/**
 * 自定义注解:
 *  注解的声明: @interface
 *  内部定义成员,通常使用value表示
 *  可以通过default定义成员变量的默认值
 *  如果自定义注解没有成员,表明是一个标记作用
 *
 *  元注解: 对注解的修饰:
 *  @Retention: 指定所修饰的Annotation的生命周期, SOURCE/CLASS(默认行为)/RUNTIME
 *  只有声明为RUNTIME生命周期的注解,才能通过反射获取
 *  @Target: 用于指定被修饰的Annotation能用于修饰哪些程序元素
 *  @Documented: 表示所修饰的注解被javadoc解析时,保留下来
 *  @Inherited: 被它修饰的Annotation将具有继承性,此时的Annotation修饰的元素其儿子也同样被它修饰
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.TYPE})
//@Repeatable()
public @interface MyAnnotation {
    String value() default "Hello";
}
