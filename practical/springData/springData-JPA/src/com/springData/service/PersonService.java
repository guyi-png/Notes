package com.springData.service;

import com.springData.entity.Person;
import com.springData.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void updatePersonAge(Integer id, Integer age){
        personRepository.updatePersonAge(id, age);
    };

    @Transactional
    public void savePersons(List<Person> persons){
        personRepository.saveAll(persons);
    }
}
