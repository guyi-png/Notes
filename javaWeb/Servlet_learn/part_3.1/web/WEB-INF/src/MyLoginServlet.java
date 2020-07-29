import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyLoginServlet extends MyHttpServlet{
    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        //  从index.html的表单读取数据
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        // 从web.xml中配置参数获取数据
        ServletContext context = getServletConfig().getServletContext();
        String username1 = context.getInitParameter("user");
        String password1 = context.getInitParameter("password");
        // 设置响应配置,获取Writer用于响应
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/plain");
        PrintWriter writer = servletResponse.getWriter();
        // 验证 数据
        if ( username.equals(username1) && password.equals(password1) ){
            writer.print("hello, "+ username);
        }else{
            writer.print("账号或密码错误");
        }
    }

}
