package com.spring3.generic;

import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User>{
    /*
    * 此类的父类中有@Autowire注解，会装配，在ioc容器中的与BaseRepository<T>兼容的是
    * UserRepository类的bean，该类继承了BaseRepository<User>，
    * 所以BaseService<T>类中的repository属性被UserRepository对象赋值
    * 此过程叫 泛型依赖注入 (个人理解)
    * */
}
