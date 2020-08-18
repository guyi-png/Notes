### 使用技术：
    mysql基础
    JDBC
    javaWEB基础
    jQuery.js
    AJAX
    ... 
    
### 功能分析
    客户：
        查看图书信息：
            查看图书详细信息
            使用条件查看图书信息
            对图书展示进行翻页
        把图书加入购物车
        查看购物车
        修改购物车：
            清空购物车
            修改购物车单本图书的数量
            删除单本图书
        结账：
            填写用户名和账号信息
        查看交易记录
        
 ### 总体架构
    MVC设计模式：
        - Model: POJO(Plain Old Java Object)
        - Controller: Servlet
        - View: JSP + EL + JSTL
              
###  技术选型
    数据库： mysql
    数据源(池): druid
    JDBC工具: DBUtils
    事务解决方案： Filter + ThreadLocal
    AJAX解决方案: jQuery + javascript + JSON + google-gson
    层之间解耦方案: 工厂设计模式
    
### 实体类设计
    Account <-- 
    User --> 
    Trade --> TradeItem --> 
    Book <-- ShoppingCartItem <--
    shoppingCart
    
### 数据表设计
    account_table
    user_table
    trade_table
    book_table
    trade_item_table
        
### DAO层设计
    DAO <interface>
    BaseDAO implements DAO
    BookDAO <interface>
    BookDAOImpl extends BaseDAO implements BaseDAO
    
