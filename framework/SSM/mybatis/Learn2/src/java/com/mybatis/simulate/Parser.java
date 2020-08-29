package com.mybatis.simulate;


import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于解析xml文件和注解，    方法有局限性
 * 关于解析xml文件的相关操作   略
 */
public class Parser {

    /**
     * @param is  用于读取xml文件
     */
    public static Configuration parse(InputStream is) {
        //  具体解析xml的实现  略， 直接获取到配置信息
        Configuration configuration = new Configuration("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test" +
                        "?useSSL=false&useUnicode=true&characterEncoding=utf8" +
                        "&rewriteBatchedStatements=true",
                "root", "root");
        // 定义一个map,存放Mapper
        // key为对应着 dao的全类名.方法   value对应着 sql语句 + 返回结果的类型
        Map<String, Mapper> mappers = new HashMap<>();

        // 只实现注解sql方式, 通过读取xml配置文件获得对应dao的全类名
        String daoTypename = "com.mybatis.dao.UserDao";
        Class<?> aClass = null;
        try {
            aClass = Class.forName(daoTypename);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert aClass != null;
        Method[] methods = aClass.getMethods();
        // 遍历所有的方法
        for (Method method : methods){
            Annotation[] annotations = method.getAnnotations();
            // 遍历所有的注解
            for (Annotation annotation : annotations){
                // 只针对Select注解
                if (annotation instanceof Select){
                    String sql = ((Select) annotation).value();
                    // 获得mappers的key
                    String mappersKey =  daoTypename + "."+method.getName();

                    Class clazz = null;
                    Type genericReturnType = method.getGenericReturnType();
                    if (genericReturnType instanceof ParameterizedType){
                        Type[] typeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                        if (typeArguments != null && typeArguments.length >0){
                            if (typeArguments[0] instanceof Class){
                                // 只能考虑返回类型只有一个泛型
                                clazz = (Class) typeArguments[0];
                            }
                        }
                    }
                    Mapper mapper = new Mapper();
                    mapper.setQueryString(sql);
                    assert clazz != null;
                    mapper.setResultType(clazz.getName());

                    mappers.put(mappersKey ,mapper);
                }
            }
        }
        //装入mappers
        configuration.setMappers(mappers);
        return configuration;
    }

}
