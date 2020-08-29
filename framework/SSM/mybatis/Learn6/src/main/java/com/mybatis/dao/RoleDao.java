package com.mybatis.dao;


import com.mybatis.entity.Role;

import java.util.List;

public interface RoleDao {
    /**
     * 获取所有的角色
     * @return
     */
    List<Role> getAll();
}
