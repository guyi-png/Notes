package com.custom.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/*
    Fragment：片段，片断，碎片段
 */
public class TestFragment extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspFragment jspBody = getJspBody(); //获取标签体
        StringWriter sw = new StringWriter();
        jspBody.invoke(sw); // 参数writer为标签体内容输出的字符流，若为null则是out
        String content = sw.toString().toUpperCase();
        getJspContext().getOut().print(content);
    }
}
