package com.springMVC.handlers;

import com.springMVC.entity.Address;
import com.springMVC.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@RequestMapping("/handlerTwo")
// value对应key名，有对应的key名就加， types对应value的类型，有该类型就加
@SessionAttributes(value = {"user"}, types = {Address.class}) //此注解只作用于类级别
public class HandlerTwo {
    private final String SUCCESS = "success";

    /**
     * 将model装入session域
     * @param map
     * @return
     */
    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Map<String, Object> map){
        User user = new User();
        user.setAddress(new Address("大鞑贾", "十字街"));
        user.setUsername("阿冲");
        user.setPassword("jj27310ks");

        //由于类上面有声明@SessionAttributes()注解并且指定了key("user")，
        // 这时会同时将user放入session域
        map.put("user", user);
        return SUCCESS;
    }


    /**
     * 有@ModelAttribute注解的方法， 会再每个目标方法执行之前被SpringMVC调用
     *  模拟从数据库获取数据,
     * 与下面的方法一起
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false)Integer id,
                        Map<String, Object> map){
        if (id != null){
            User user = new User(1, "阿冲", "12345");
            System.out.println("从数据库中获取到数据"+user);
            map.put("user", user);
        }
    }

    /**
     * 运行流程：
     * 1.执行@modelAttribute注解修饰的方法：从数据库中获取数据保存到对象中，把对象放入map中，key为"user"
     * 2.SpringMVC从map中取出user对象，并把表单的请求参数值赋给该user对象的属性
     * 3.SpringMVC传入目标方法的参数中
     *
     * 注意： 如果目标方法的参数没用@ModeelAttribute修饰，
     * 在@ModelAttribute修饰的方法中，
     * 放入map时的key名"user"要和目标方法参数的类型名（首字母小写后）一样
     * 获取表单的数据和数据库的数据
     *
     * 源码的分析流程：
     * 1.调用@ModelAttribute注解修饰的方法，实际上把@ModelAttribute方法中map的数据放入implicitModel中
     * 2.解析请求处理器的目标参数，实际上该目标参数来自WebDataBinder对象的target属性
     * 3.WebDataBinder对象的创建：
     *      ① 确定objectName属性： 若传入的attrName属性值为 "" ,则objectName为类名第一个字母小写，
     *      若目标方法显式使用了@ModelAttribute来修饰参数，则attrName值即为@ModelAttribute的value属性值
     *      ② 确定target属性：在implicitModel中查找attrName对应的属性值(通过名字)，若存在则赋值，
     *      若不存在则会查看当前类是否被@SessionAttributes修饰，是 且session中有指定与attrName匹配的值，
     *      则在session中查找attrName对应的属性值，session中没有对应的属性值，则抛出异常。
     *      若当前类没有被@SessionAttributes修饰，或@SessionAttributes中没有使用value指定key和attrName匹配，
     *      则通过反射创建pojo对象
     * 4.SpringMVC把表单的请求参数赋给了WebDataBinder的target属性
     * 5.SpringMVC把WebDataBinder的attrName和target赋给implicitModel，然后存入request中
     * 6.把WebDataBinder的target作为参数传递给目标方法的入参
     *
     * @param user
     * @return
     */
    @RequestMapping("/testModeAttribute")
    public String testModeAttribute(@ModelAttribute(value = "user")User user){
        System.out.println("修改:"+user);
        return SUCCESS;
    }

    /**
     * 关于springMVC视图解析原理的学习
     * https://blog.csdn.net/fly_captain/article/details/83150129
     * @return
     */
    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver(){
        System.out.println("testViewAndViewResolver");
        return SUCCESS;
    }

    /**
     * 通过名字映射到视图类(HelloView类)
     * @return
     */
    @RequestMapping("/testView")
    public String testView(){
        System.out.println("testView");
        return "helloView";
    }

    /**
     * 如果返回的字符串中带 forward: 或 redirect: 前缀时，
     * SpringMVC 会对他们进行特殊处理,
     * 将 forward: 和 redirect: 当成指示符，
     * 其后的字符串作为 URL来处理
     *
     * 声明使用重定向的方式
     * @return
     */
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:/index.jsp";
    }
}
