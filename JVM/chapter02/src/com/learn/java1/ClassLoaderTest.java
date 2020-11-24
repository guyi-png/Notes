package com.learn.java1;

public class ClassLoaderTest {
    public static void main(String[] args) {
        // 获取系统类加载器（SystemClassLoader）
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d

        // 获取其上层， 扩展类加载器（ExtClassLoader）
        ClassLoader extensionClassLoader = systemClassLoader.getParent();
        System.out.println(extensionClassLoader);
        //jdk.internal.loader.ClassLoaders$PlatformClassLoader@723279cf

        // 无法获取引导类加载器
        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        System.out.println(bootstrapClassLoader);   //null

        // 用户自定义的类来说, 默认使用系统类加载器进行加载
        ClassLoader userClassLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(userClassLoader);
        //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d

        // java的核心类库都是由引导类加载器加载的
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println(stringClassLoader);  //null

    }
}
