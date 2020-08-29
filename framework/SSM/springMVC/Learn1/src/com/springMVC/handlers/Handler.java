package com.springMVC.handlers;

import com.springMVC.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/handler")  //类的定义处：提供初步的请求的映射信息，相对于WEB应用的根目录
public class Handler {
    private final String HELLO = "hello";
    /**
     * 使用@RequestMapping 注解来映射请求的URL
     * 返回值会通过视图解析器解析为实际的物理视图，
     * InternalResourceViewResolver解析器， 解析后：
     *  通过 prefix + 方法返回值 + suffix 的方式得到实际的物理视图， 然后 转发
     */
    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("hello");
        return HELLO;
    }

    /**
     * value指定URL， method指定请求的方法
     * @return
     */
    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod(){
        System.out.println("testMethod");
        return HELLO;
    }

    /**
     * params指定请求参数，param表示参数值
     * param：表示请求必须包含param的请求参数
     * !param：表示请求不能包含param的请求参数
     * param!=value: 表示请求必须包含param的请求参数且其值不能等于value
     * ...
     * headers指定请求头属性，如Accept-Language等
     * 用法和作用与 params一样
     * @return
     */
    @RequestMapping(
            value = "testParameter",
            params = {"username", "age!=10"},
            headers = "Accept-Language=zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"
    )
    public String testParameter(){
        System.out.println("testParameter");
        return HELLO;
    }

    /**
     * Ant风格资源地址支持的3种匹配符：
     *  *: 匹配文件名中的任意字符
     *  **：匹配多层路径
     *  ?：匹配文件中的一个字符
     *  RequestMapping支持Ant匹配符
     *
     * @return
     */
    @RequestMapping(value = "/testAntPath/*/ok")
    public String testAntPath(){
        System.out.println("testAntPath");
        return HELLO;
    }

    /**
     * @PathVariable 可以来映射URL中的占位符到目标方法的参数中
     * @param message 从url中取来的参数，对应 {message} 的值
     * @return
     */
    @RequestMapping(value = "/testPathVariable/{message}")
    public String testPathVariable(@PathVariable(value = "message") String message){
        System.out.println("message");
        return HELLO;
    }

    /**
     *  HTTP协议中四个表示操作方式的动词： GET POST PUT DELETE
     *  分别对应着四种基本操作：
     *  GET 获取资源
     *  POST 新建资源
     *  PUT 更新资源
     *  DELETE 删除资源
     * @param id
     * @return
     */
    // GET request
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
    public String testRestGET(@PathVariable("id")int id){
        System.out.println("testRest GET:"+id);
        return HELLO;
    }

    // POST request
    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRestPOST(){
        System.out.println("testRest POST");
        return HELLO;
    }

    // PUT request
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
    public String testRestPUT(@PathVariable("id")int id){
        System.out.println("testRest PUT:"+id);
        return HELLO;
    }

    // DELETE request
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDELETE(@PathVariable("id")int id){
        System.out.println("testRest DELETE:"+id);
        return HELLO;
    }

    /**
     * 再方法参数前加注解
     * @RequestParam(value=请求参数名,required=false未传不报错,defaultValue=""默认值)
     * 将请求参数传给方法参数
     * @param username 从请求参数username获取
     * @param password 从请求参数password获取
     * @return
     */
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam(value = "username", required = false)String username,
                                   @RequestParam("password")String password){
        System.out.println("username and password: "+ username+" "+password);
        return HELLO;
    }

    /**
     * 再方法参数前加注解
     * @RequestHeader(value=请求头的属性名)
     * 将请求头的属性信息传给方法参数
     * @param cookie
     * @return
     */
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Cookie") String cookie){
        System.out.println(cookie);
        return HELLO;
    }

    /**
     * 与上一样
     * @return
     */
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID")String jsessionid){
        System.out.println("JSESSIONID"+ jsessionid);
        return HELLO;
    }

    /**
     *  springMVC会按请求参数名和pojo(简单的Java对象，实际就是普通JavaBeans)
     *  的属性名进行自动匹配，自动为该对象填充属性值
     * @param user
     * @return
     */
    @RequestMapping(value = "/testPojo", method = RequestMethod.POST)
    //当请求对应的url时，post的表单数据通过名字初始化了user的属性
    public String testPojo(User user){
        System.out.println("testPojo:" + user);
        return HELLO;
    }

    /**
     * 可以使用原生的ServletAPI作为参数的 类型有
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Locale
     * InputStream
     * OutputStream
     * Reader
     * Writer
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/testServletAPI")
    public void testServletAPI(HttpServletRequest request,
                               HttpServletResponse response, Writer writer){
        System.out.println(request);
        System.out.println(response);
        try {
            writer.write("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法的返回值可以是ModelAndView类型
     * 其中可以包含视图 HELLO 即映射的文件名
     * 和模型数据 就是加的那个Attribute
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        String viewName = HELLO;
        ModelAndView modelAndView = new ModelAndView(viewName);
        //  给modelAndView添加模型数据, 相当于setAttribute();
        // 其实底层原理就是 将map放到request.setAttribute()中
        modelAndView.addObject("time", new Date());

        return modelAndView;
    }

    /**
     * 方法可以添加Map类型，也可以是Model，ModelMap类型的参数
     * 此时向map中存放数据时其实底层是调用request.setAttribute(K, V)方法
     * @param map
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        map.put("names", Arrays.asList("赛罗", "泰迦","银河"));
        return HELLO;
    }
    /*
    handler方法返回值总结：
        1. 返回ModelAndView ：带着数据，返回视图路径

        2. 返回void ：数据通过形参 Model model 或者 ModelMap model，
        但是没办法return视图。如果需要返回视图得通过request或response。
        这种比较适合ajax请求，但是给ajax返回数据得是json格式数据。

        3. 返回字符串：返回视图路径，数据通过形参 Model model 或者 ModelMap model
     */
}
