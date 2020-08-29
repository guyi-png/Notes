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
 * mybatis中的缓存：
 *      什么是缓存？
 *          存在于内存中的临时数据
 *      为什么要用缓存？
 *          减少和数据库的交互次数， 提高执效率
 *      什么样的数据可以用缓存？
 *          需要经常查询，且不经常改变的数据
 *          数据的正确与否对最终的结果影响不大的数据
 *          像， 商品的库存，银行的汇率， 股市的牌价等 不适用于缓存
 *      mybatis中有一级缓存和二级缓存
 *          一级缓存：
 *              它指的是mybatis中SqlSession对象的缓存，
 *              当我们执行查询之后，查询的结果会同时存入到SqlSession为我们提供的一块区域中，
 *              该区域的结构是一个Map， 当我们再次查询同样的数据时，
 *              mybatis会首先去SqlSession的那个Map中获取相应的数据， 没有再去数据库查。
 *              当SqlSession对象消失时， mybatis的一级缓存就消失了。
 *              一级缓存的SqlSession范围的缓存，当调用SqlSession的
 *              修改，删除，添加， commit(),close()等方法时， 会清空一级缓存
 *              可以显式清空缓存 SqlSession.clearCache();
 *          二级缓存：
 *              它指的是mybatis中的SqlSessionFactory对象的缓存，
 *              由同个SqlSessionFactory对象创建的SqlSession共享其缓存
 *              使用步骤：
 *                  让mybatis框架支持二级缓存(在配置文件中配置)
 *                  让当前的映射文件支持二级缓存
 *                  让当前的的操作支持二级缓存(在select标签中配置)
 */
public class Test {
    private static final SqlSessionFactory factory;
    private static SqlSession session;
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
        factory = ssfb.build(is);
        session = factory.openSession(true);

        // 获取代理对象
        userDao = session.getMapper(UserDao.class);
        accountDao = session.getMapper(AccountDao.class);
        roleDao = session.getMapper(RoleDao.class);
    }

    public static void main(String[] args) throws IOException {
        // 调用方法获取数据
//        accountGetAll();
//        roleGetAll();
//        userGetAllIncludeAccount();
//        userGetAllIncludeRole();
//        testFirstCache();
        testSessionFactoryLevel();

        // 关闭连接
        session.close();
        is.close();
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

    private static void testFirstCache(){
        User user1 = userDao.getById(3);
        System.out.println(user1.getUsername());    //爱莉
        User user2 = userDao.getById(3);
        System.out.println(user2.getUsername());    //爱莉
        System.out.println(user1 == user2);     // true


//        User user1 = userDao.getById(3);
//        System.out.println(user1.getUsername());    //爱莉
//
//        session.close();
//        session = factory.openSession();
        //或
        //session.clearCache();
//
//        User user2 = session.getMapper(UserDao.class).getById(3);
//        System.out.println(user2.getUsername());    //爱莉
//        System.out.println(user1 == user2);     // false
//        session.close();
    }

    private static void testSessionFactoryLevel(){
        SqlSession session1 = factory.openSession();
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.getById(3);
        System.out.println(user1.getUsername());

        SqlSession session2 = factory.openSession();
        UserDao userDao2 = session2.getMapper(UserDao.class);
        User user2 = userDao2.getById(3);
        System.out.println(user2.getUsername());

        // 注意， 二级缓存， 缓存的是 数据 不是对象
        System.out.println(user1 == user2);  //false

        session1.close();
        session2.close();
    }
}
