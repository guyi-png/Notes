package com.springMVC.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器, 需要实现接口 HandlerInterceptor， 还需要去配置
 */
public class HelloInterceptor implements HandlerInterceptor {
    /**
     * 该方法在目标方法之前被调用
     * 若返回值为true， 则继续调用后续的拦截器和目标方法
     * 若返回值为false， 则不会调用后续的拦截器和目标方法
     * 该方法可以做权限，日志，事务等
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("HelloInterceptor preHandle");
        return true;
    }

    /**
     * 在调用目标方法之后， 但在渲染视图之前调用
     * 可以对请求域中的属性或视图做修改
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("HelloInterceptor postHandle");
    }

    /**
     * 该方法会在整个请求完成 , 渲染视图之后调用
     * 可以用来释放资源
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("HelloInterceptor afterCompletion");
    }
}
