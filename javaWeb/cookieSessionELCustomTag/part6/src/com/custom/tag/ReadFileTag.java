package com.custom.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadFileTag extends SimpleTagSupport {
    private String src;

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        try (   InputStream is = pageContext.getServletContext().getResourceAsStream(src);
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))      ){
            String str = null;
            while ((str = br.readLine()) != null){
                pageContext.getOut().write(str);
                pageContext.getOut().write("<br>");
            }
        }
    }
}
