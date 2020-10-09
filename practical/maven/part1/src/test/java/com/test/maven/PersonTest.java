package com.test.maven;

import com.guyi.maven.Person;
import org.junit.Test;

public class PersonTest {
    @Test
    public void test(){
        Person person = new Person();
        person.setName("爱莉");
        person.setAge(18);
        person.setDescription("最喜欢八八");

        System.out.println(person);
    }
}
