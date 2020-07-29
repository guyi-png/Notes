import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class Login implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        /**
         * ServletRequest接口封装了请求信息，可以从中获取任何的请求信息
         * ServletResponse接口封装了响应信息，用来给用户响应数据
         */
        System.out.println(servletRequest);
        System.out.println(servletResponse);
        /*
            ServletRequest的一些常用方法:
                String getParameter(String name): 根据请求的参数名返回参数值
                Map<String, String[]> getParameterMap()
                Enumeration<String> getParameterNames(): 获取所有参数名
                String[] getParameterValues(String name):根据请求参数的名字，返回请求参数对应的字符串数组,可能一个参数名对应多个值，比如表单中type="checkbox"标签
        * */
        String user = servletRequest.getParameter("user");
        String password = servletRequest.getParameter("password");
        System.out.println("user: "+user);
        System.out.println("password: "+ password);

        String[] values = servletRequest.getParameterValues("interesting");
        System.out.print("your interesting: ");
        for (String value : values){
            System.out.print(value+"\t");
        }
        System.out.println();

        Enumeration<String> names = servletRequest.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = servletRequest.getParameter(name);
            // 这里通过名字获取值，如果有多个值，会获取第一个值，后面都不要了
            System.out.println("参数名："+name+" 对应的值:" + value);
        }
        // key是参数名， value是参数值
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            String[] value = entry.getValue();
            System.out.println("参数名："+entry.getKey()+" 对应的值:" + Arrays.toString(value));
        }
            //HttpServletRequest是Servlet的子接口, 通过HttpServletRequest的方法获取http相关信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取uri
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
        // 获取请求的方法
        String method = httpServletRequest.getMethod();
        System.out.println(method);
        // 获取查询字符串(http中?后面的字符串)
        String queryString = httpServletRequest.getQueryString();
        System.out.println(queryString);
        //获取servlet请求路径
        httpServletRequest.getServletPath();
        /*
         *  ServletResponse的常见方法：
         *      getWriter(): 返回PrintWriter对象，调用该对象的print()方法，将把print()中的参数直接打印
         *      setContentType(String var1): 设置相应数据的文件类型
         *      setCharacterEncoding(String var1): 设置字符集
         *      getOutputStream(): 获取输出流
         *      子接口HttpServletResponse接口的 sendRedirect(String location): 请求重定向
         */
        servletResponse.setContentType("text/plain");
        servletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.print("您的信息正在通过检查...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
