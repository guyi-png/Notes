package com.custom.tag.choose;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class OtherwiseTag  extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        ChooseTag parent = (ChooseTag) getParent();
        if (parent.isFlag()){
            getJspBody().invoke(null);
        }
    }
}
