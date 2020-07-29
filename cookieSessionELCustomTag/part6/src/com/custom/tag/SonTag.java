package com.custom.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class SonTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        // JspTag是一个空接口，目的是统一SimpleTag和Tag
        // 获取父标签的引用
        JspTag parent = getParent();
        // 先转为ParentTag ，再获取父标签的name属性
        ParentTag parentTag = (ParentTag) parent;
        String name = parentTag.getName();
        getJspContext().getOut().println("我的"+name);
    }
}
