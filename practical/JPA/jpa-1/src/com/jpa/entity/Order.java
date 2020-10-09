package com.jpa.entity;

import javax.persistence.*;

/**
 * ManyToOne（多对一）单向：不产生中间表，但可以用@Joincolumn（name="  "）
 * 来指定生成外键的名字，外键在多的一方表中产生！
 * OneToMany（一对多）单向：会产生中间表，此时可以用@onetoMany @JoinColumn（name=" "）避免产生中间表，
 * 并且指定了外键的名字（别看@joincolumn在一的一方中写着，但它存在在多的一方那个表中）
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_name", length = 50)
    private String orderName;
    //映射多对一的关系
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")  // 映射外键关系,name指定与外键对应的字段名
    private Customer customer;

    public Order() {
    }

    public Order(String orderName, Customer customer) {
        this.orderName = orderName;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", customer=" + customer +
                '}';
    }
}
