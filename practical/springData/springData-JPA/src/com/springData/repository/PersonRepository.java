package com.springData.repository;

import com.springData.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository是一个空接口即是一个标记接口
 * 若接口继承了 Repository接口则该接口会被ioc容器识别为一个
 * repository bean， 纳入到ico容器中进而可以在该接口中定义一定规范的方法
 * 也可以通过@RepositoryDefinition注解来替代继承Repository接口
 *
 * 在repository子接口中声明方法：
 * 1. 不可以随便声明，要符合一定规范
 * 2. 查询方法以find | read | get 开头
 * 3. 涉及条件查询时，条件的属性以关键字连接
 * 4. 注意条件属性以首字母大写
 * 5. 支持级联查询，若当前类有符合条件的属性，则优先使用，而不使用级联属性，
 * 要用级联属性可用 "_"连接
 *
 * 使用@Query注解可以自定义jpql语句以实现更灵活的查询
 */
//@RepositoryDefinition(domainClass = Person.class, idClass=Integer.class)
//public interface PersonRepository extends Repository<Person, Integer> {
//public interface PersonRepository extends CrudRepository<Person, Integer> {
//public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
public interface PersonRepository extends JpaRepository<Person, Integer>,
        JpaSpecificationExecutor<Person> {

    // 根据name获取Person
    Person getByName(String name);

    // where name like ?% and id < ?
    List<Person> getByNameStartingWithAndIdLessThan(String name, Integer id);

    // where age in(?, ?, ?) or birthday < ?
    List<Person> getByAgeInOrBirthdayGreaterThan(List<Integer> ages, Date birth);

    @Query("select p from Person p where p.id = (select max(id) from Person)")
    Person getMaxIdPerson();

    // 为@Query注解传递参数的方式
//    @Query("select p from Person p where p.name = ?1")
    @Query("select p from Person p where p.name = :name")
    Person getByNameParam(@Param("name")String name);

    // 使用本地的sql语句
    @Query(value="select count(*) from persons", nativeQuery=true)
    long getTotal();

    // 默认情况下，springData的每个方法上有事务，但都是一个只读事务，不能完成修改操作
    // jpql 不支持INSERT
    @Modifying // 表示使用 dml
    @Query("update Person p set p.age = ?2 where p.id = ?1")
    void updatePersonAge(Integer id, Integer age);
}
