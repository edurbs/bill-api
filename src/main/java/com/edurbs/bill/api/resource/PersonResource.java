package com.edurbs.bill.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.bill.api.event.ResourceCreatedEvent;
import com.edurbs.bill.api.model.Person;
import com.edurbs.bill.api.repository.PersonRepository;
import com.edurbs.bill.api.service.PersonService;





@RestController
@RequestMapping("/persons")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getOne(@PathVariable Long id) {
        return personRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    

    @PostMapping()
    public ResponseEntity<Person> create(@Valid @RequestBody Person entity, HttpServletResponse response) {
        Person newPerson = personRepository.save(entity);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, newPerson.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    } 

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        personRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person remove(@PathVariable Long id, @Valid @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        personService.activate(id);
    }

    @PutMapping("/{id}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long id) {
        personService.inactivate(id);
    }



}
