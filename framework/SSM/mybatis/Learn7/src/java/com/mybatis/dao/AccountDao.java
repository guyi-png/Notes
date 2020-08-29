package com.mybatis.dao;

import com.mybatis.entity.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AccountDao {
    @Select({"select * from `account`"})
    // 配置多对一
    @Results(id = "accountMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "uid", column = "uid"),
            @Result(property = "balance", column = "balance"),
            @Result(
                    property = "user",
                    column = "uid",   // 依照该列去查询
                    one = @One(select = "com.mybatis.dao.UserDao.findById",
                            fetchType = FetchType.EAGER)  // 立即得到
            )
    })
    List<Account> getAll();

    /**
     * 根据用户id查询其账户信息
     * @param uid
     * @return
     */
    @Select("select * from `account` where uid = #{uid}")
    List<Account> getByUid(Integer uid);
}
