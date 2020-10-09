package com.springData.test;

import com.springData.entity.Person;
import com.springData.repository.PersonRepository;
import com.springData.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test {
    private static ApplicationContext ctx = null;
    private static PersonService personService = null;

    static {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        personService = ctx.getBean(PersonService.class);
    }

    public static void main(String[] args) {
//        testDataSource();
//        testGet();
//        testKeyWord();
//        testQueryAnnotation();
//        testModifying();
//        testCurdRepository();
//        testPagingAndSortingRepository();
//        testJpaRepository();
        testJpaSpecificationExecutor();
    }

    // 实现带查询条件的分页：调用JpaSpecificationExecutor的
    // Page<T> findAll(Specification<T> spec, Pageable pageable)方法
    // Specification封装了JPA Criteria查询条件
    // Pageable封装了请求分页信息
    private static void testJpaSpecificationExecutor() {
        PersonRepository repository = ctx.getBean(PersonRepository.class);

        PageRequest pageRequest = PageRequest.of(1, 2);
        Specification<Person> specification = new Specification<Person>() {
            /**
             * @param root 代表查询的实体类
             * @param criteriaQuery 可以从中得到root对象，还可以添加查询条件等
             * @param criteriaBuilder 用于创建Criteria相关对象的工厂，可以从中获取Predicate对象
             * @return Predicate 类型，代表一个查询条件
             */
            @Override
            public Predicate toPredicate(Root<Person> root,
                CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path<Integer> path = root.get("id");
                return criteriaBuilder.gt(path, 5);
            }
        };

        Page<Person> page = repository.findAll(specification, pageRequest);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前第几页:" + (page.getNumber()+1));
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("当前页面的list:" + page.getContent());
        System.out.println("当前页面的记录数："+ page.getNumberOfElements());
    }

    private static void testJpaRepository() {
        PersonRepository repository = ctx.getBean(PersonRepository.class);
        Person person = new Person("奥索托", 30, new Date());
        person.setId(10);

        Person person1 = repository.saveAndFlush(person);

        System.out.println(person == person1); //false
    }

    private static void testPagingAndSortingRepository() {
        PersonRepository repository = ctx.getBean(PersonRepository.class);
        // pageNo从0开始
        int pageNo = 2;
        int pageSize = 2;
        // sort 封装了排序的信息
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "age");
        Sort sort = Sort.by(order, order1);
        // pageable接口通常使用其PageRequest实现类，其中封装了需要分页的信息
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Person> page = repository.findAll(pageRequest);

        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前第几页:" + (page.getNumber()+1));
        System.out.println("总页数:" + page.getTotalPages());
        System.out.println("当前页面的list:" + page.getContent());
        System.out.println("当前页面的记录数："+ page.getNumberOfElements());
    }

    private static void testCurdRepository() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("亚历山大", 20, new Date()));
        persons.add(new Person("小智", 15, new Date()));
        persons.add(new Person("银河", 23003, new Date()));
        personService.savePersons(persons);
    }

    private static void testModifying() {
        // update/delete 需要支持事务
        personService.updatePersonAge(2 , 17);

    }

    private static void testQueryAnnotation() {
        PersonRepository repository = ctx.getBean(PersonRepository.class);
        Person person = repository.getMaxIdPerson();
        System.out.println(person);

        Person person1 = repository.getByNameParam("苏芳");
        System.out.println(person1);

        long total = repository.getTotal();
        System.out.println("共有记录： "+total);
    }

    private static void testKeyWord() {
        PersonRepository repository = (PersonRepository) ctx.getBean("personRepository");
        List<Person> people = repository.getByNameStartingWithAndIdLessThan("爱", 2);
        System.out.println(people);

        List<Person> people1 = repository.getByAgeInOrBirthdayGreaterThan(Arrays.asList(10, 16, 17), new Date());
        System.out.println(people1);
    }

    private static void testGet() {
        PersonRepository repository = (PersonRepository) ctx.getBean("personRepository");
        Person person = repository.getByName("爱莉");
        System.out.println(person);
    }

    private static void testDataSource() {
        DataSource dataSource = (DataSource)ctx.getBean("dataSource");
        try {
            System.out.println(dataSource.getConnection());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
