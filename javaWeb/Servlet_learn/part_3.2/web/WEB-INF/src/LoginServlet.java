import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
//      查询mysql数据库
        String sql = " select count(*) from users where username=? and password=? ";
//      jdbc技术
        Connection connect = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&useSSL=false";
            String DBUser = "root";
            String DBPassword = "root";
            connect = DriverManager.getConnection(url, DBUser, DBPassword);
            statement = connect.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                int count = resultSet.getInt(1); ///查询结果,如果count为0表示账号密码或密码错误

                if (count > 0){
                    writer.print("您好, "+username);
                    System.out.println("success");
                }else{
                    writer.print("账号或密码错误");
                    System.out.println("error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null)
                    connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
