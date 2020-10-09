package com.jpa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * jpa的基本注解：
 *  - @Entity: 用于实体类声明语句之前，指出该java类为实体类，将映射到指定的数据库表。
 *  - @Id: 用于声明一个实体类的属性映射为数据库的主键列，@Id可置于属性的getter方法之上
 *  - @GeneratedValue: 用于标注主键的生成策略，通过strategy属性指定。默认情况（auto）下，jpa
 *  自动选择一个最适合底层数据库的主键生成策略。 可选策略：
 *      - identity: 采用数据库id自增长的方式来自增主键字段，Oracle不支持
 *      - auto： 默认情况
 *      - sequence：通过序列产生主键，通过@SequenceGenerator注解指定序列名，mysql不支持
 *      - table： 通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库的移植
 * - @Basic： 表示一个简单的属性到数据库表的字段的映射，对于没有任何标准的getxxx()方法默认为@Basic
 *
 * - @Column: 常用属性是name，用于设置映射到数据库表的字段名，还有属性unique，nullable，length
 *      columnDefinition属性表示该字段在数据库中的实际类型
 *
 * - @Transient: 表示该属性并非一个到数据库表的字段的映射
 *
 * - @Temporal: 在进行属性映射时可使用@Temporal注解来调整Date的精度（date, time, timestamp）
 */
// 用注解的方式来描述持久化类与数据库表的对应关系
@Entity
@Table(name = "customers")  // 对应表名
//@Cacheable(value = true)    // 使用二级缓存
@NamedQuery(name="testNamedQuery", query = "from Customer c where c.id=?1")
public class Customer {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private Date birth;

    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Customer(String name, String email, Integer age, Date birth) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.birth = birth;
    }

    @Id     // 表示主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自动生成策略
    @Column(name = "id")   //指定字段名
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Temporal(TemporalType.DATE)  // 只保存日期
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    // 映射一对多的关系, cascade 级联操作触发:
    // CascadeType.PERSIST 级联持久化 ( 保存 ) 操作
    // CascadeType.MERGE 级联更新 ( 合并 ) 操作
    // CascadeType.REFRESH 级联刷新操作，只会查询获取操作
    // CascadeType.REMOVE 级联删除操作
    // CascadeType.ALL 级联以上全部操作
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //多的一方为关系维护端，关系维护端负责外键记录的更新，关系被维护端没有权利更新外键记录
    @JoinColumn(name = "customer_id")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Transient
    public void getInfo() {
        System.out.println("不映射");
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                '}';
    }
}
