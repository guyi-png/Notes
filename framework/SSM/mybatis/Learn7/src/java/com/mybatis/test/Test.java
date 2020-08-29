import com.mybatis.dao.AccountDao;
import com.mybatis.dao.UserDao;
import com.mybatis.entity.Account;
import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class Test {
    private static SqlSessionFactory factory;
    private static SqlSession session;

    public static void main(String[] args) throws IOException {
         factory = (new SqlSessionFactoryBuilder())
                .build((Resources.getResourceAsStream("mybatis-config.xml")));
        session = factory.openSession();

        UserDao userDao = session.getMapper(UserDao.class);
        AccountDao accountDao = session.getMapper(AccountDao.class);

//        testUserDao(userDao);
//        testAccountDao(accountDao);
        testSqlSessionFactoryLevel(userDao);

        session.close();
    }

    private static void testUserDao(UserDao userDao){
        User user = userDao.findById(3);
        System.out.println(user);
    }

    private static void testAccountDao(AccountDao accountDao){
        List<Account> accounts = accountDao.getAll();

        accounts.forEach(System.out::println);
    }

    private static void testSqlSessionFactoryLevel(UserDao userDao){
        User user = userDao.findById(3);

        System.out.println(user.getUsername()); //爱莉

        session.close();
        session = factory.openSession();
        UserDao mapper = session.getMapper(UserDao.class);

        User user1 = mapper.findById(3);

        System.out.println(user1.getUsername()); //爱莉
    }
}
