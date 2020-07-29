package com.custom.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class ForEachTag extends SimpleTagSupport {
    private Object items;
    private String var;

    public void setItems(Object items) {
        this.items = items;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void doTag() throws JspException, IOException {
        //获取标签体内容
        JspFragment jspBody = getJspBody();
        // 如果是Collection的子类
        if (items instanceof Collection) {
            Collection<?> items = (Collection<?>) this.items;
            // 遍历集合
            for (Object obj : items) {
                // 把遍历得到的元素放入域中
                getJspContext().setAttribute(var, obj);
                // 输出到页面
                jspBody.invoke(null);
            }
        } else if(items instanceof Map) { //如果是Map的子类
            Map<?, ?> items = (Map<?, ?>) this.items;
            // 遍历集合
            for (Map.Entry<?,?> entry : items.entrySet()){
                // 把遍历得到的元素放入域中
                getJspContext().setAttribute(var, entry);
                // 获取标签体内容并输出到页面
                jspBody.invoke(null);
            }
        }else{
            getJspContext().getOut().print("类型无效");
        }
    }
}
