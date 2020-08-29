package com.springMVC.handler;

import com.springMVC.dao.DepartmentDAO;
import com.springMVC.dao.EmployeeDAO;
import com.springMVC.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/emps")
public class EmployeeHandler {
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    /// 展示员工列表处理
    @RequestMapping("/list")
    public String list(Map<String, Object> map){
        map.put("employees", employeeDAO.getAll());
        return "list";
    }

    /// 添加员工页面处理
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map){
        map.put("departments", departmentDAO.getDepartmentAll());
        map.put("employee", new Employee());
        return "input";
    }

    // 修改员工页面处理
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id")Integer id, Map<String, Object> map){
        map.put("employee", employeeDAO.get(id));
        map.put("departments", departmentDAO.getDepartmentAll());
        return "input";
    }

    // 添加员工处理
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String add(@Valid Employee employee, BindingResult result, Map<String, Object> map){
        //@Valid注解校验Employee的属性是否合法，需要校验的bean对象和其绑定的结果对象或
        //错误对象成对出现时，他们之间不允许声明其他入参
        System.out.println(employee); //打印Employee对象信息


        if (result.getErrorCount() > 0){  // 打印属性格式化,校验等错误信息
            System.out.println("存在问题");
            for (FieldError fieldError :result.getFieldErrors()){
                System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
            }
            // 若出错，则转向某个页面, 可以考虑国际化问题
            map.put("departments", departmentDAO.getDepartmentAll());
            return "input";
        }

        int id = employeeDAO.getAll().size() + 1;
        ///  我人傻了
        employee.getDepartment().setDepartmentName(
                departmentDAO.getDepartment(employee.getDepartment().getId())
                        .getDepartmentName()
        );
        employee.setId(id);

        employeeDAO.save(employee);
        return "redirect:/emps/list";
    }

    // 修改时不修改name属性，从数据库(模拟)取出
    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false)Integer id,
                              Map<String, Object> map){
        if (id != null){   //只有更新操作有id请求参数
            map.put("employee", employeeDAO.get(id)); //注意key
        }
    }

    // 修改员工处理
    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDAO.save(employee);
        return "redirect:/emps/list";
    }



    // 删除员工处理
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id")Integer id){
        employeeDAO.delete(id);
        return "redirect:/emps/list";
    }




    /**
     * 有@InitBinder注解的方法，可以对WebDataBinder对象进行初始化
     * WebDataBinder是DataBinder的子类，用于完成由表单字段到javaBean属性的绑定
     * @InitBinder方法不能由返回值， 必须声明为void
     * @InitBinder方法的参数通常为WebDataBinder类型
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){

    }

    /**
     * 需要jackson包处理json，https://mvnrepository.com/open-source/json-libraries
     * 在使用 @RequestMapping后，返回值通常解析为跳转路径，
     * 但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，
     * 而是直接写入 HTTP response body 中。
     * 比如异步获取 json 数据，加上@ResponseBody后，
     * 会直接返回 json 数据。@RequestBody 将 HTTP 请求正文插入方法中，
     * 使用适合的 HttpMessageConverter 将请求体写入某个对象。
     * @return
     */
    @RequestMapping("/testJson")
    @ResponseBody
    public Collection<Employee> testJson(){
        return employeeDAO.getAll();
    }

    @RequestMapping("/testMessageConverter")
    @ResponseBody
    public String testMessageConverter(@RequestBody String body){
        System.out.println("body:"+ body); // 打印请求体
        // return的结果直接响应到页面
        return "olg"+ new Date();
    }

    /**
     * ResponseEntity<T>：表示整个HTTP响应：状态代码，标题和正文。
     * 因此，我们可以使用它来完全配置HTTP响应，它是一个对象。
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpServletRequest request) throws IOException {
        // 响应体， 读取某个文件
        byte[] body = null;
        InputStream is = request.getServletContext().getResourceAsStream("/files/lyric.txt");
        body = new byte[is.available()];
        is.read(body);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=lyric.txt");
        // 设置状态码
        HttpStatus status = HttpStatus.OK;  //200
        // 响应实体
        return new ResponseEntity<>(body, headers, status);
    }

    /**
     * 需要去配置文件配置MultipartResolver,
     * 还要导包commons-fileupload.jar，commons-io.jar
     * 如果上传了多个文件可以考虑使用集合
     * @param desc
     * @param file
     * @return
     */
    @RequestMapping("/testFileupload")
    public String testFileupload(@RequestParam("desc")String desc,
                                 @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("描述: "+desc); //打印描述
        InputStream is = file.getInputStream();
        System.out.println("文件名: "+file.getOriginalFilename());
        return "success";
    }
}
