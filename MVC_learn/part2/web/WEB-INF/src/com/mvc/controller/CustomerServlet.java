package com.mvc.controller;

import com.mvc.model.customer.Customer;
import com.mvc.model.customer.CustomerDAO;
import com.mvc.model.dao.Criteria;
import com.mvc.model.dao.CustomerDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

public class CustomerServlet extends HttpServlet {
    /*
     *  面向接口编程:
     *  在类中调用接口的方法，而不必关心具体的实现。这将有利于代码的解耦。使程序有更好的可移植性和可扩展性
     */
    //    private final CustomerDAO customerDAO = new CustomerDAOImpl(); //使用数据库(mysql)的相关操作实现类
    //    private final CustomerDAO customerDAO = new CustomerDAOXmlImpl(); // 使用xml存储数据的相关操作实现类
    private CustomerDAO customerDAO;

    public void  init() {
        try {
            // 从配置文件中读取以什么做存储
            InputStream is = getClass().getClassLoader().getResourceAsStream("switch.properties");
            Properties properties = new Properties();
            properties.load(is);
            String type = properties.getProperty("type");
            // 获取单例对象
            CustomerDAOFactory factory = CustomerDAOFactory.getInstance();
            // 以类型名找具体实现类
            factory.setType(type);
            customerDAO = factory.getCustomerDAO(); //返回CustomerDAO的具体实现类的对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath(); //获取servlet的path
        // 取其中的相应方法
        String methodName = servletPath.substring(1, servletPath.indexOf(".customer"));

        //System.out.println(HttpServletRequest.class); //interface javax.servlet.http.HttpServletRequest
        //System.out.println(req.getClass());   //class org.apache.catalina.connector.RequestFacade

        try {
            // 通过获取的方法名，利用反射获得相应的方法
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            // 调用方法
            method.invoke(this, req, resp);

        } catch (Exception e) {
            resp.sendRedirect("error.jsp");
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        int id = 0;
        try{
            id = Integer.parseInt(idStr); // 尝试将queryString转为int类型
            customerDAO.deleteById(id);     // 从数据库中删除相应的数据
        }catch (Exception e){
            e.getStackTrace();
        }
        resp.sendRedirect("select.customer");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        // 在修改时呈现修改页面
        // 获取请求参数
        String idStr = req.getParameter("id");
        int id = -1;
        try {
            id = Integer.parseInt(idStr);
            // 调用CustomerDAO的getCustomerById(id)获取id对应的Customer对象
            Customer customer = customerDAO.getCustomerById(id);
            // 将customer放入request中
            req.setAttribute("customer",customer);
            // 转发
            req.getRequestDispatcher("updateCustomer.jsp").forward(req, resp);

        } catch (Exception e) {
            resp.sendRedirect("error.jsp");
            e.printStackTrace();
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        // 从修改页面获取信息
        String id = req.getParameter("id");
        String originalName = req.getParameter("originalName");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        // 现在可以修改信息
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(id));
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);

        // 验证原来的name与现在修改的name是否相同
        if (originalName.equalsIgnoreCase(name)){
            req.setAttribute("message", "请修改CustomerName信息");
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("updateCustomer.jsp").forward(req,resp);
            return;
        }
        // 查数据库中是否有对应的name
        long count = customerDAO.getCountWithName(name);
        if (count > 0){
            req.setAttribute("message", "名字 \""+name+"\" 重复了，请重新输入");
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("updateCustomer.jsp").forward(req,resp);
            return;
        }
        // 修改数据库的数据
        customerDAO.alter(customer);
        // 重定向到select.customer
        resp.sendRedirect("select.customer");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 从请求参数中获取信息
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        // 将数据封装到对象中
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);
        // 验证name是否被占用
        long count = customerDAO.getCountWithName(name);
        if (count > 0){
            req.setAttribute("message", "名字 \""+name+"\" 重复了，请重新输入");
            req.getRequestDispatcher("addNewCustomer.jsp").forward(req,resp);
            return;
        }
        // 向数据库插入数据
        customerDAO.insert(customer);
        // 重定向到success.jsp页面
        // 重定向可以避免表单的重复提交问题
        resp.sendRedirect("success.jsp");
    }

    private void select(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        Criteria criteria = new Criteria(name, address, phone);

        List<Customer> customers = customerDAO.getForListByCriteria(criteria);
        // 将从数据库中获取的数据显示到页面
        // 先将集合装入request
        req.setAttribute("customers", customers);
        // request需要相同，所有使用转发
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
