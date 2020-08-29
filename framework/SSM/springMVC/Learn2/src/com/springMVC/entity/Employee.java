package com.springMVC.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.sql.Date;

/**
 * JSR 303 基本的校验规则
 * 空检查
 * .@Null 验证对象是否为null
 * .@NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * .@NotBlank 检查约束字符串是不是Null还有被Trim的长度是否大于0, 只对字符串, 且会去掉前后空格.
 * .@NotEmpty 检查约束元素是否为NULL或者是EMPTY.
 *
 * Boolean检查
 * . @AssertTrue 验证 Boolean 对象是否为 true
 * . @AssertFalse 验证 Boolean 对象是否为 false
 *
 * 长度检查
 * . @Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内
 * . @Length(min=, max=) Validates that the annotated string is between min and max included.
 *
 * 日期检查
 * . @Past 验证 Date 和 Calendar 对象是否在当前时间之前，验证成立的话被注释的元素一定是一个过去的日期
 * . @Future 验证 Date 和 Calendar 对象是否在当前时间之后 ，验证成立的话被注释的元素一定是一个将来的日期
 * . @Pattern 验证 String 对象是否符合正则表达式的规则，被注释的元素符合制定的正则表达式，
 * regexp:正则表达式 flags: 指定 Pattern.Flag 的数组，表示正则表达式的相关选项。
 *
 * 数值检查
 * 建议使用在String,Integer类型，不建议使用在int类型上，因为表单值为字符串类型无法直接转换为int，
 * . @Min 验证 Number 和 String 对象是否大等于指定的值
 * . @Max 验证 Number 和 String 对象是否小等于指定的值
 * . @DecimalMax 被标注的值必须不大于约束中指定的最大值.
 * 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度
 * . @DecimalMin 被标注的值必须不小于约束中指定的最小值.
 * 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度
 * . @Digits 验证 Number 和 String 的构成是否合法
 * . @Digits(integer=,fraction=) 验证字符串是否是符合指定格式的数字，
 * integer指定整数精度，fraction指定小数精度。
 * . @Range(min=, max=) 被指定的元素必须在合适的范围内
 * . @Range(min=10000,max=50000,message="range.bean.wage")
 * . @Valid 递归的对关联对象进行校验, 如果关联对象是个集合或者数组,
 * 那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证)
 * . @CreditCardNumber信用卡验证
 * . @Email 验证是否是邮件地址，如果为null,不进行验证，算通过验证。
 * . @ScriptAssert(lang= , script=, alias=)
 * . @URL(protocol=,host=, port=,regexp=, flags=)
 * ...
 */
public class Employee {
    private Integer id;
    @NotEmpty // 不为空，校验的注解，需要引入hibernate validator的包, 注意导包操作
    private String name;
    private int age;
    private Department department;
    // 由于配置了<mvc:annotation-driven/>所以可以使用类型格式的注解，
    // SpringMVC会通过提供的pattern解析对应值
    @Past  // 必须是过去的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    @NumberFormat(pattern="#,###,###.##")
    private double salary;


    public Employee() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee(Integer id, String name, int age, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", department=" + department +
                ", birth=" + birth +
                ", salary=" + salary +
                '}';
    }
}
