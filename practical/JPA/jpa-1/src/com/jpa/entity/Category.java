package com.jpa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, name = "category_name")
    private String categoryName;
    // 用@ManyToMany注解来映射多对多关联关系
    // 使用@JoinTable来映射中间表， @JoinTable中name是中间表的名字
    // @JoinColumns映射当前所在的表在中间表中的外键， 在@JoinColumns中name指定外键列的列名，
    // referencedColumnName指定外键关联当前表的哪一列
    // @inverseJoinColumns映射关联的类（另一个多的一方的类）所在中间表的外键
    @ManyToMany
    // 生成中间表
    @JoinTable(name = "item_category", joinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    private Set<Item> items = new HashSet<>();

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
