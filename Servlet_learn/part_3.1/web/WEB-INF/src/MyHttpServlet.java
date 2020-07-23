import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHttpServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        if (servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            if (servletResponse instanceof HttpServletResponse){
                HttpServletResponse response = (HttpServletResponse) servletResponse;

                service(request, response);
            }
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getMethod();

        if ("get".equalsIgnoreCase(method)){
            doGet(request, response);
        }
        if ("post".equalsIgnoreCase(method)){
            doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
}
