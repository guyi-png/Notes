import com.mybatis.dao.UserDao;
import com.mybatis.dao.UserTwoDao;
import com.mybatis.entity.Criteria;
import com.mybatis.entity.User;
import com.mybatis.entity.UserTwo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

/**
 * 测试
 */
public class UserTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //创建SqlSessionFactory
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(is);
        //使用SqlSessionFactory工厂
        SqlSession session = factory.openSession();
        //使用SqlSession创建Dao接口的代理对象
        UserDao userDao = session.getMapper(UserDao.class);

        UserTwoDao userTwoDao = session.getMapper(UserTwoDao.class);

//        getAll(userDao); // 获取所有的数据
//        save(userDao);   //插入数据
//        update(userDao);   //更新数据
//        delete(userDao);    // 删除数据
//        findById(userDao);      //通过id查询
        findByName(userDao);       // 通过名字查询
//        getTotal(userDao);      // 获取总条目数
//        findUserByCriteria(userDao);  //通过 筛选类 查询
//            testUserTwoDao(userTwoDao);


        // 自动提交现在为false， 要手动提交
        session.commit(); //提交
        // 释放资源
        session.close();
        is.close();
    }

    private static void getAll(UserDao userDao){
        // 使用代理对象执行方法
        List<User> users = userDao.getAll();
        for (User user : users){
            System.out.println(user);
        }
    }

    private static void save(UserDao userDao){
        User user = new User();
        user.setUsername("阿冲");
        user.setBirthday(new Date(new java.util.Date().getTime()));
        user.setSex("男");
        user.setAddress("新世界");

        System.out.println(user);
        userDao.saveUser(user);
        System.out.println(user); // user中有了id属性值
    }

    private static void update(UserDao userDao){
        User user = new User();
        user.setId(5);
        user.setUsername("大古");

        userDao.update(user);
        System.out.println("ok");
    }

    private static void delete(UserDao userDao){
        userDao.delete(5);
        System.out.println("ok");
    }

    private static void findById(UserDao userDao){
        User user = userDao.findById(2);

        System.out.println(user);
    }

    private static void findByName(UserDao userDao){
        List<User> user = userDao.findByName("爱莉");

        System.out.println(user);
    }

    private static void getTotal(UserDao userDao){
        int total = userDao.getTotal();
        System.out.println("一共有"+ total + "条记录");
    }

    private static void findUserByCriteria(UserDao userDao){
        User user = new User();
        user.setUsername("爱莉");
        Criteria criteria = new Criteria();

        criteria.setMaxId(100);
        criteria.setUser(user);

        List<User> users = userDao.findUserByCriteria(criteria);

        System.out.println(users);
    }

    private static void testUserTwoDao(UserTwoDao userDao){
        List<UserTwo> users = userDao.getAll();

        for (UserTwo user : users){
            System.out.println(user);
        }
    }
}
