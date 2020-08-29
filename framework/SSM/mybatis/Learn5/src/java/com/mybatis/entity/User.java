package com.mybatis.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *   mysql创建user表(主表)
 *   CREATE TABLE `user` (
 *     `id` int(11) NOT NULL AUTO_INCREMENT,
 *     `username` varchar(32) NOT NULL,
 *     `birthday` datetime DEFAULT NULL,
 *     `sex` char(1) DEFAULT NULL,
 *     `address` varchar(60) DEFAULT NULL,
 *     PRIMARY KEY (`id`)
 *   ) ENGINE=INNODB DEFAULT CHARSET=utf8
 *
 * mysql创建 中间表，关联 user表和role表
 * CREATE TABLE user_role (
 *  uid INT NOT NULL,
 *  rid INT NOT NULL,
 *  CONSTRAINT user_role_uid_user FOREIGN KEY(uid) REFERENCES `user`(id),
 *  CONSTRAINT user_role_rid_role FOREIGN KEY(rid) REFERENCES role(id),
 *  PRIMARY KEY(uid, rid)
 * );
 */
public class User implements Serializable {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    // 一对多的关系映射， 主表实体应该包含从表实体的集合引用
    List<Account> accounts;
    // 多对多的关系映射， 一个用户可以具备多个角色
    List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                ", roles=" + roles +
                '}';
    }
}
