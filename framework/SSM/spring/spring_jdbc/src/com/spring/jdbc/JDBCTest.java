package com.spring.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest {
    private static final ApplicationContext ctx = new
            ClassPathXmlApplicationContext("spring-config.xml");

    private static final NamedParameterJdbcTemplate namedParameterJdbcTemplate
            = ctx.getBean(NamedParameterJdbcTemplate.class);



    public static void main(String[] args) {
        testNamedParameterJdbcTemplate();
    }



    public static void testDataSource(){
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        try {
            Connection connect = dataSource.getConnection();
            System.out.println(connect);
            //      ......
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void testJdbcTemplateUpdate(){
        // 使用JdbcTemplate
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        // 执行 对数据库的 update 操作
        String sql = "UPDATE persons SET age = ? WHERE id = ?";
        jdbcTemplate.update(sql, 41, 2);
    }

    public static void testJdbcTemplateBatchUpdate(){
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        // 批量update
        String sql = "UPDATE persons SET age = ? WHERE id = ?";
        List<Object[]> batches = new ArrayList<>();
        batches.add(new Object[]{38 , 1});
        batches.add(new Object[]{40, 2});
        batches.add(new Object[]{15, 3});

        jdbcTemplate.batchUpdate(sql, batches);
    }


    public static void testJdbcTemplateQuery(){
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        // 查询单行数据
        String sql = "SELECT * FROM persons WHERE id = ?";
        // Person person = jdbcTemplate.queryForObject(sql, Person.class,1);
        //  将类row map, RowMapper的常用实现类为BeanPropertyRowMapper，
        //  使用sql中的列名或别名与类的属性名映射, 例如first_name -> firstName
        RowMapper<Person> rm = new BeanPropertyRowMapper<>(Person.class);
        Person person = jdbcTemplate.queryForObject(sql, rm, 3);
        System.out.println(person);
    }

    public static void testJdbcTemplateQueryForList(){
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        // 查询数据集
        String sql = "SELECT * FROM persons WHERE id < ?";
        RowMapper<Person> rm = new BeanPropertyRowMapper<>(Person.class);
        // 不是调用jdbcTemplate.queryForList();
        List<Person> persons = jdbcTemplate.query(sql, rm, 4);

        persons.forEach(System.out::println);
    }

    public static void testJdbcTemplateQueryForValue(){
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        // 查询单列数据，统计数据
        String sql = "SELECT count(*) FROM persons";
        Long value = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(value);
    }

    /**
     *  使用具名参数时，可以使用update(String sql, SqlParameterSource paramSource)的方法进行更新操作
     *  sql语句中的参数名和类的属性名可以一致， 使用SqlParameterSource的实现类BeanPropertySqlParameterSource
     *  可以将类做为参数
     */
    public static void testNamedParameterJdbcTemplate(){
        //  :xx 为参数起名字
        String sql = "INSERT INTO persons(`name`, age, description) VALUES(:name, :age, :description)";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("name", "老大爷");
//        paramMap.put("age", 80);
//        paramMap.put("description", "身体棒");
//        namedParameterJdbcTemplate.update(sql, paramMap);

        Person person = new Person();
        person.setName("彼得托夫斯基");
        person.setAge(17);
        person.setDescription("就这");

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person);
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
