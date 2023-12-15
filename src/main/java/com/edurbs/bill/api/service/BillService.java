package com.edurbs.bill.api.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edurbs.bill.api.event.ResourceCreatedEvent;
import com.edurbs.bill.api.model.Bill;
import com.edurbs.bill.api.model.Person;
import com.edurbs.bill.api.repository.BillRepository;
import com.edurbs.bill.api.repository.PersonRepository;
import com.edurbs.bill.api.repository.filter.BillFilter;
import com.edurbs.bill.api.repository.projection.BillProjection;
import com.edurbs.bill.api.service.exception.PersonInactiveException;
import com.edurbs.bill.api.service.exception.PersonInexistentException;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonRepository personRepository;

    public List<Bill> findAll(){
        return billRepository.findAll();
    }

    public ResponseEntity<Bill> findById(Long id) {
        return findBillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    public Bill create(@Valid Bill bill, HttpServletResponse response) {
        Bill billSaved = billRepository.save(bill);
        findPersonById(bill);                
        publisher.publishEvent(new ResourceCreatedEvent(this, response, billSaved.getId()));
        return billSaved;
    }

    public Page<Bill> filter(BillFilter billFilter, Pageable pageable) { 
        
        Page<Bill> page = billRepository.queryFiltredBills(
                pageable, billFilter.getDescription(), billFilter.getFromDueDate(), billFilter.getToDueDate()
            );
        Long total = page.getTotalElements();

        return new PageImpl<>(page.getContent(), pageable, total);
    }

    public Page<BillProjection> project(BillFilter billFilter, Pageable pageable) {
        Page<BillProjection> page = billRepository.queryFiltredBillsProjection(
                pageable, billFilter.getDescription(), billFilter.getFromDueDate(), billFilter.getToDueDate()
            );
        Long total = page.getTotalElements();

        return new PageImpl<>(page.getContent(), pageable, total);
    }

    public void remove(Long id) {        
        billRepository.deleteById(id);        
    }

    private Person findPersonById(Bill bill) {
        Person person = personRepository.findById(bill.getPerson().getId())
                .orElseThrow(()-> new PersonInexistentException());
        if(!person.isActive()){
            throw new PersonInactiveException();
        }
        return person;
    }

    private Optional<Bill> findBillById(Long id) {
        return billRepository.findById(id);
    }

    


}
