package com.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "cashier")
public class CashierImpl implements Cashier{
    private BookShopService bookShopService;

    @Autowired
    public void setBookShopService(BookShopService bookShopService) {
        this.bookShopService = bookShopService;
    }

    /**
     *  事务的传播属性：当事务方法被另一个事务方法调用时，必须指定事务应该如何传播
     *  事务的传播行为可以由传播属性指定
     *  spring定义的7个传播行为： propagation（传播）
     *      1、PROPAGATION.REQUIRED：如果存在一个事务，则支持当前事务。
     *      如果没有事务则开启一个新的事务。
     *
     *      2、PROPAGATION.REQUIRES_NEW：使用PROPAGATION_REQUIRES_NEW,
     *      需要使用 JtaTransactionManager作为事务管理器。 它会开启一个新的事务。
     *      如果一个事务已经存在，则先将这个存在的事务挂起。
     *
     *      3、PROPAGATION.MANDATORY：如果已经存在一个事务，支持当前事务。
     *      如果没有一个活动的事务，则抛出异常。
     *
     *      4、PROPAGATION.SUPPORTS：如果存在一个事务，支持当前事务。
     *      如果没有事务，则非事务的执行。
     *
     *      5、PROPAGATION.NOT_SUPPORTED：总是非事务地执行，并挂起任何存在的事务。
     *      使用PROPAGATION.NOT_SUPPORTED,
     *      也需要使用JtaTransactionManager作为事务管理器
     *
     *      6、PROPAGATION.NEVER：总是非事务地执行，如果存在一个活动事务，则抛出异常。
     *
     *      7、PROPAGATION.NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中。
     *      如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。
     */
    // 此方法是一个事务方法
    @Transactional(propagation = Propagation.REQUIRED,// 指定事务的传播行为
    isolation = Isolation.READ_COMMITTED, //指定隔离级别
    noRollbackFor = {AccountException.class},//默认事务对所有运行时异常进行回滚，可以通过属性设置不对 哪些异常进行回滚
    readOnly = false,// 可以指定该事务是否只读，只读事务只读取数据不更新数据，可以帮助数据库引擎优化事务
    timeout = -1) //timeout可以指定事务可以占用的时间,若超时就强制回滚
    @Override
    public void checkout(String username, List<String> isbnList) {
        for (String isbn : isbnList){
            bookShopService.purchase(username, isbn);  // 调用一个事务方法
            // 假设有一个事务方法出错，默认propagation = Propagation.REQUIRED所有事务都会回滚
        }
    }
}
