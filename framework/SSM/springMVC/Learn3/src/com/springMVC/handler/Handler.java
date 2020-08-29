package com.springMVC.handler;

import com.springMVC.handlerException.UsernameNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class Handler {
    public Handler(){
        System.out.println("Handler's Constructor");
    }

    private final String SUCCESS = "success";
    private final String ERROR = "error";

    @RequestMapping("/message")
    public String message(){
        return SUCCESS;
    }

    /**
     * 接收请求参数， 做除法
     * @param x
     * @return
     */
    @RequestMapping("testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(@RequestParam("x")Integer x){
        int y = 100 / x;   //当 100 / 0 时报错
        System.out.println(y);
        return SUCCESS;
    }

    /**
     * 该方法移至 同包下的 handlerException.HandlerException中
     *
     * 在有@ExceptionHandler注解的方法中， 其入参中可以加入Exception类型的参数，
     * 但不能传入Map， 若希望把异常信息传到页面上，可以通过ModelAndView返回
     *      如果出错， 在当前类（Handler）中找不到对应的异常，会去 @ControllerAdvice
     *      注解了的类中查找对应的异常处理
     */
//    @ExceptionHandler({ArithmeticException.class})
//    public ModelAndView handlerArithmeticException(Exception exception){
//        System.out.println("出现错误："+ exception);
//
//        ModelAndView modelAndView = new ModelAndView(ERROR);
//        modelAndView.addObject("exception", exception);
//        return modelAndView;
//    }



//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "出错") // 给出错误页面
    @RequestMapping("testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(@RequestParam("x")Integer x){
        if (x != 123){
            throw new UsernameNotMatchException();
        }else{
            System.out.println("ok");
        }
        return SUCCESS;
    }

    // DefaultHandlerExceptionResolver对一些特殊的异常
    // (如：NoSuchRequestHandingMethodException等)进行处理

    /**
     * 通过配置异常的映射 来捕获异常
     * @param x
     * @return
     */
    @RequestMapping("testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("x")Integer x){
        String[] vals = new String[10];
        Arrays.fill(vals, "嗯？");
        System.out.println(vals[x]);
        return SUCCESS;
    }
}
