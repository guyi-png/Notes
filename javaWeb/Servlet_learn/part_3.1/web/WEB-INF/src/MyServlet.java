import javax.servlet.*;
import java.io.IOException;

/**
 * GenericServlet是Servletの实现类
 *
 */
public class MyServlet extends GenericServlet {
    // 重写public void init() throws ServletException {}
    // 不是重写public void init(ServletConfig config) throws ServletException {}
    @Override
    public void init() throws ServletException {

    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 可直接获取ServletConfig
        ServletConfig config = getServletConfig();
    }
}
