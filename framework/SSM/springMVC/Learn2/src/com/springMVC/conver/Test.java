package com.springMVC.conver;

import com.springMVC.dao.EmployeeDAO;
import com.springMVC.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class Test {
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // 接收 由字符串转化为Employee的对象
    @RequestMapping("/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee")Employee employee,
                                Map<String, Object> map){
        employeeDAO.save(employee);
        map.put("employees", employeeDAO.getAll());
        return "list";
    }
}
