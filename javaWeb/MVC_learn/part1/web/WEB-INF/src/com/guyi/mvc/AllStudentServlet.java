package com.guyi.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDAO dao = new StudentDAO();
        List<Student> students = dao.getAll(); //从数据库中获取数据

        req.setAttribute("students", students);
        req.getRequestDispatcher("/student.jsp").forward(req,resp);
    }
}
