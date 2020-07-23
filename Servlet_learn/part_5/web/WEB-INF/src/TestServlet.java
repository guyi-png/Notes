import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         *  请求的转发与响应的重定向的区别：
         *      请求的转发只发出了一次请求，而重定向则发出二次请求
         *      请求的转发地址栏是初次发出请求的地址，重定向的地址栏是最后响应的地址
         *      请求的转发在最终的Servlet中request对象与中转的request是同一个，而重定向不是
         *      请求的只能转发到当前WEB应用的资源，而重定向可以到任何资源
         */
        System.out.println("ok");
    }
}
