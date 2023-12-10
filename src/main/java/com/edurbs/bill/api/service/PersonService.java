package com.edurbs.bill.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edurbs.bill.api.model.Person;
import com.edurbs.bill.api.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person updatePerson(Long id, Person person){
        Person personToUpdate = findPersonById(id);
        BeanUtils.copyProperties(person, personToUpdate, "id");        
        return personRepository.save(personToUpdate);
    }
    
    public void activate(Long id) {
        Person person = findPersonById(id);
        person.activate();
        personRepository.save(person);
    }
    
    public void inactivate(Long id) {
        Person person = findPersonById(id);
        person.inactivate();
        personRepository.save(person);
    }

    private Person findPersonById(Long id) {
        Person personToUpdate = personRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(1));
        return personToUpdate;
    }

}
