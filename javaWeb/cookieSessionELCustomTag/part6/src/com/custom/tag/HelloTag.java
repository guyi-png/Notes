package com.custom.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

import java.io.IOException;

/**
 * 自定义标签，
 * 实现SimpleTag接口
 */
public class HelloTag implements SimpleTag {
    private PageContext pageContext;  //pageContext可以获取jsp中的其他8个隐藏对象
    // 通过tld文件的配置后，自定义标签的属性的值最后被赋给了该类的属性
    private String value;
    private String count;

    // 属性在赋值时需要使用setter
    public void setValue(String value) {
        this.value = value;
    }

    public void setCount(String count) {
        this.count = count;
    }

    // 实际自定义标签会执行doTag方法
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = pageContext.getOut();
        out.print("hello world");
        //////////////////////
        // 打印从标签中属性传来的值
        int c = Integer.parseInt(count);
        for (int i= 0;i < c; i++){
            out.print((i+1)+"."+value);
        }
    }

    @Override
    public void setParent(JspTag jspTag) {
        System.out.println("setParent");
    }

    @Override
    public JspTag getParent() {
        System.out.println("getParent");
        return null;
    }

    @Override
    public void setJspContext(JspContext jspContext) {  //由jsp引擎来调用
        // PageContext是JspContext的子类
//        System.out.println(jspContext instanceof PageContext); //true
        assert jspContext instanceof PageContext;
        pageContext = (PageContext)jspContext;
    }

    @Override
    public void setJspBody(JspFragment jspFragment) {
        System.out.println("setJspBody");
    }
}
