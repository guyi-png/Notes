package com.custom.tag.choose;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class WhenTag extends SimpleTagSupport {
    private boolean test;  //在标签的表达式后返回的boolean

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (test){
            ChooseTag parent = (ChooseTag) getParent();
            if (parent.isFlag()){
                // 为真时将父标签的flag设为false
                parent.setFlag(false);
                // 输出子标签的内容
                getJspBody().invoke(null);
            }
        }
    }
}
