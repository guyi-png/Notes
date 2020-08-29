package com.spring3.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public class UserRepositoryImpl implements UserRepository {
    UserController userController;

    @Autowired   //按类型指定装配
    public void setUserController(@Qualifier("userController") UserController userController) {
                                    // 可以在参数前指定装配的bean
        this.userController = userController;
    }

    @Override
    public void save() {
        System.out.println("userRepository save()");
        userController.execute();
    }
}
