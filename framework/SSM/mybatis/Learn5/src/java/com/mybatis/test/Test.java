import com.mybatis.dao.AccountDao;
import com.mybatis.dao.RoleDao;
import com.mybatis.dao.UserDao;
import com.mybatis.entity.Account;
import com.mybatis.entity.Role;
import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 一对多的关系：    如，一个用户有多个账户
 * 多对一的关系：    如，多个账户属于一个用户
 * 多对多的关系：    如， 一个用户有多个角色，一个角色可赋予多个用户
 */
public class Test {
    private static final SqlSession session;
    private static final UserDao userDao;
    private static final AccountDao accountDao;
    private static final RoleDao roleDao;
    private static InputStream is;

    static {
        // 读取配置，建立工厂
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = ssfb.build(is);
        session = factory.openSession(true);

        // 获取代理对象
        userDao = session.getMapper(UserDao.class);
        accountDao = session.getMapper(AccountDao.class);
        roleDao = session.getMapper(RoleDao.class);
    }

    public static void main(String[] args) throws IOException {
        // 调用方法获取数据
//        userGetById(3);
//        accountGetByUid();
//        userGetAllIncludeAccount();
//        accountGetAll();
//        roleGetAll();
        userGetAllIncludeRole();


        // 关闭连接
        session.close();
        is.close();
    }

    private static void userGetById(int id){
        User user = userDao.getById(id);

        System.out.println(user);
    }

    private static void accountGetByUid(){
        User user = new User();
        user.setId(3);

        List<Account> accounts = accountDao.getByUid(user);

        accounts.forEach(System.out::println);
    }

    private static void userGetAllIncludeAccount(){
        List<User> users = userDao.getAllIncludeAccount();

        users.forEach(System.out::println);
    }

    private static void accountGetAll(){
        // 获取account信息和对应用户的username，address
        List<Account> accounts = accountDao.getAll();

        accounts.forEach(System.out::println);
    }

    private static void roleGetAll(){
        List<Role> roles = roleDao.getAll();

        roles.forEach(System.out::println);
    }

    private static void userGetAllIncludeRole(){
        List<User> users = userDao.getAllIncludeRole();

        users.forEach(System.out::println);
    }
}
