package com.springMVC.handlerException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TestHandlerException {

    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerArithmeticException(Exception exception){
        System.out.println(" (^_^) ha ha 出现错误："+ exception);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
