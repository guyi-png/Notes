package com.custom.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ParentTag extends SimpleTagSupport {
    private String name = "粑粑";

    public String getName() {
        return name;
    }

    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("父亲的name: "+name);
        //getJspBody可以获取标签体(获取不了子标签的引用),
        // 然后使用子标签(父标签的内容),若没有invoke()则子标签不会执行
        getJspBody().invoke(null);
    }
}
