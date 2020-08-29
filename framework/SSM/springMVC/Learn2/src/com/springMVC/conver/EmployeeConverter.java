package com.springMVC.conver;

import com.springMVC.dao.DepartmentDAO;
import com.springMVC.entity.Department;
import com.springMVC.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Spring定义了3种类型的转换器接口：
 *      - Converter<S, T> 将S类型转化为T类型对象
 *      - ConverterFactory 将相同系列多个同质 Converter封装在一起，如果想将一种类型的对象
 *      转化为另一种类型及其子类的对象可以考虑使用
 *      - GenericConverter 会根据源类型对象及目标类对象所在的宿主类中的上下文信息进行类型转换
 * 实现任意一个转换器接口都可以作为自定义转换器注册到ConversionServiceFactoryBean中
 *
 * 将字符串转换为Employee对象
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {
    private DepartmentDAO departmentDAO;

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public Employee convert(String s) {
        String[] strings = s.split("-");
        if (strings.length == 3){
            String name = strings[0];
            int age  = Integer.parseInt(strings[1]);
            Department department = new Department();
            department.setId(Integer.parseInt(strings[2]));
            department.setDepartmentName(departmentDAO.getDepartment(department.getId()).getDepartmentName());
            return new Employee(null, name, age, department);
        }
        return null;
    }
}
