1. 什么是ajax？
    不用刷新页面，但可以和服务端进行通信的方式，
    使用Ajax的主要方式是XMLHttpRequest对象
    
2. Ajax传输数据的3种方式
    XML： 笨重，通用
    HTML： 不用解析，直接使用
    JSON： 小巧，有面向对象的特征
3. 使用jQuery完成ajax操作
    1. load: 可以用于HTML文档的元素节点，把结果直接加为对应节点的子元素，load方法加载后的数据通常是HTML片段
        ```javascript
            let $obj = ...
            let url = ...
            let args = {"key":"value"...}
            $obj.load(url, args)    
        ```
    2. $.get, $.post, $.getJSON更加灵活
        ```javascript
            let url = ...
            let args = {"key":"value"...}
            $.get(url, args, function(data){
                $(data).find("xx").text();  //xml
                // or
                data.xx.xx;  //json
            })
            
        ```
       ```javascript
           let url = ...
           let args = {"key":"value"...}
           $.getJSON(url, args, function(data){
               data.xx.xx;
           })
       
       //or
          $.get(url, args, function(data){
               data.xx.xx;
          }, "JSON"); 
       ```
       
       
    