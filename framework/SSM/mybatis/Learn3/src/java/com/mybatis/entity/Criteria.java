package com.mybatis.entity;

public class Criteria {
    private User user;
    private Integer maxId;
    private UserTwo userTwo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMaxId() {
        return maxId;
    }

    public void setMaxId(Integer maxId) {
        this.maxId = maxId;
    }

    public UserTwo getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserTwo userTwo) {
        this.userTwo = userTwo;
    }
}
