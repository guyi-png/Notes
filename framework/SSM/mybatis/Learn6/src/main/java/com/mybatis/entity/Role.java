package com.mybatis.entity;

import java.io.Serializable;
import java.util.List;

/**
 * mysql创建role表
 * CREATE TABLE role(
 * 	id INT AUTO_INCREMENT PRIMARY KEY,
 * 	roleName VARCHAR(30),
 * 	roleDesc VARCHAR(60)
 * ) ENGINE=INNODB DEFAULT CHARSET utf8;
 *
 * mysql创建 中间表，关联 user表和role表
 *CREATE TABLE user_role (
 * 	uid INT NOT NULL,
 * 	rid INT NOT NULL,
 * 	CONSTRAINT user_role_uid_user FOREIGN KEY(uid) REFERENCES `user`(id),
 * 	CONSTRAINT user_role_rid_role FOREIGN KEY(rid) REFERENCES role(id),
 * 	PRIMARY KEY(uid, rid)
 *  );
 */
public class Role implements Serializable {
    private Integer id;
    private String roleName;
    private String roleDesc;

    // 多对多的关系映射，一个角色可以赋予多个用户
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", users=" + users +
                '}';
    }
}
