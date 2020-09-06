package com.composite;

/**
 * 组合模式：
 *      结构型设计模式
 *      又叫部分整体模式，它创建了对象组的树形结构，将对象组合成树状结构以表示 “整体与部分”
 *      的层次关系
 *      组合能让客户以一致的方式处理个别对象以及组合对象
 *
 *    组合模式的角色及职责：
 *        Component： 组合中对象声明接口，在适当情况下，实现所有类共有的接口默认行为，用于访问和管理
 *        Component的子部件， Component可以是抽象类或接口
 *        Leaf： 在组合中表示叶子节点， 叶子节点没有子节点
 *        Composite： 非叶子节点， 用于存储子部件， 在Component接口中实现子部件的相关操作，
 *        如 add, delete etc.
 *
 *      jdk中HashMap用到了组合模式
 *          Map接口 与 这的 OrganizationComponent抽象类 一样
 *          HashMap 就是一个 Composite
 *          HashMap静态内部类 Node 是叶子节点, Node没有put， remove方法
 *    适用场景： 需要遍历组织机构，或者处理的对象具有树形结构时，非常适合使用组合模式
 *    如果节点和叶子节点有很多差异性，比如很多方法和属性都不一样，则不适合适用组合模式
 *
 */
public class Client {
    public static void main(String[] args) {
        // 从大到小创建
        OrganizationComponent university = new University("清华大学", "ok");
        OrganizationComponent college = new College("计算机学院", "yes");
        OrganizationComponent college1= new College("信息技术学院", "nice");
        OrganizationComponent department = new Department("软件工程系", "good");

        // 从小到大添加
        college.add(department);
        college1.add(department);
        university.add(college);
        university.add(college1);

        //打印
        university.print();
    }
}
