package com.chainOfResponsibility;

/**
 * 职责链模式：
 *      职责链模式的注意事项和细节
 *      1)将请求和处理分开,实现解耦,提高系统的灵活性
 *      2)简化了对象,使对象不需要知道链的结构
 *      3)性能会受到影响,特别是在链比较长的时候,因此需控制链中最大节点数量,
 *      一般通过在Handler中设置一个最大节点数量,在setNext()方法中判断是否已经超过阀值,
 *      超过则不允许该链建立,避免出现超长链无意识地破坏系统性能
 *      4)调试不方便。采用了类似递归的方式,调试时逻辑可能比较复杂
 *      5)最佳应用场景:有多个对象可以处理同一个请求时,
 *      比如:多级请求、请假/加薪等审批流程、Java Web中Tomcat对Encoding的处理、过滤器
 *
 * springMVC中使用了职责链模式,
 * springMVC中 DispatcherServlet类中 doDispatch()中
 *      HandlerExecutionChain mappedHandler = null;   //先定义了个链
 *
 *      mappedHandler.applyPreHandle(processedRequest, response);
 *          applyPreHandle(HttpServletRequest request, HttpServletResponse response)中:
 *              interceptor.preHandle(request, response, this.handler)；  //调用了拦截器的preHandle()
 *
 *      mappedHandler.applyPostHandle(processedRequest, response, mv);
 *          applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv)中：
 *              interceptor.postHandle(request, response, this.handler, mv); //调用了拦截器的postHandle()
 *
 *       mappedHandler.applyPreHandle(processedRequest, response);
 *          applyPreHandle(HttpServletRequest request, HttpServletResponse response)中:
 *              triggerAfterCompletion(request, response, (Exception)null)中：
 *                  // 该方法调用了拦截器的postHandle()
 *                  interceptor.afterCompletion(request, response, this.handler, ex);
 *
 */
public class Client {
    public static void main(String[] args) {
        // 请求方
        PurchaseRequest request = new PurchaseRequest(111, "很多书籍", 2002);
        // 处理请求方
        DepartmentApprover departmentApprover = new DepartmentApprover("部门负责");
        CollegeApprover collegeApprover = new CollegeApprover("院系负责");
        SchoolApprover schoolApprover = new SchoolApprover("全校负责");

        // 形成职责链, 注意链的顺序, 构成环状
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(schoolApprover);
        schoolApprover.setApprover(departmentApprover);

        // 处理请求
        departmentApprover.processRequest(request);
    }
}
