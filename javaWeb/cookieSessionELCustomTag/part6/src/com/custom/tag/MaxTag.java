package com.custom.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/*
    SimpleTagSupport implements SimpleTag
 */
public class MaxTag extends SimpleTagSupport {
    private String num1;
    private String num2;

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        int a,b;
        JspWriter out = pageContext.getOut();
        try{
            a = Integer.parseInt(num1);
            b = Integer.parseInt(num2);
            // 返回大的数
            out.print(a>b? num1:num2);
        }catch (ClassCastException e){
            out.println("输入的属性格式不对");
        }
    }
}
