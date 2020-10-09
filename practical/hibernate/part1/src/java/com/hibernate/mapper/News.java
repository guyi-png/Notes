package com.hibernate.mapper;

import java.util.Date;

/**
 * 创建持久化java类，需要提供一个无参的构造器，
 * 提供一个标识属性（id）， 为类的持久化字段声明访问（getter/setter）方法，
 * 没有实现任何接口时使用非final类
 * 把持久化类的实例放到 Set 中需要重写equals和hashcode方法
 *
 */
public class News {
    private Integer id;
    private String title;
    private String author;
    private Date date;   //java.util.Date
    private String desc;

    public News() {
    }

    public News(String title, String author, Date date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", desc='" + desc + '\'' +
                '}';
    }
}
