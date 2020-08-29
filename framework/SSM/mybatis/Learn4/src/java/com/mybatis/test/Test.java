import com.mybatis.dao.UserDao;
import com.mybatis.entity.Criteria;
import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = ssfb.build(is);
        SqlSession session = build.openSession(true); // 设置自动提交为true
        UserDao userDao = session.getMapper(UserDao.class);

//        findUserByCondition(userDao);
        findUserByCriteria(userDao);

        session.close();
        is.close();
    }

    private static void findUserByCondition(UserDao userDao){
        User user = new User();
        user.setUsername("爱莉");
        user.setSex("女");

        List<User> users = userDao.findUserByCondition(user);
        users.forEach(System.out::println);
    }

    private static void findUserByCriteria(UserDao userDao){
        Criteria criteria = new Criteria();
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(100);
        idList.add(3);
        criteria.setIdList(idList);

        List<User> users = userDao.findUserByCriteria(criteria);

        users.forEach(System.out::println);
    }
}
