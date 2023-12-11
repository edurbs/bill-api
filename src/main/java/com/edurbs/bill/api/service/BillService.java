package com.edurbs.bill.api.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edurbs.bill.api.event.ResourceCreatedEvent;
import com.edurbs.bill.api.model.Bill;
import com.edurbs.bill.api.repository.BillRepository;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public List<Bill> findAll(){
        return billRepository.findAll();
    }

    public ResponseEntity<Bill> findById(Long id) {
        return billRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public Bill create(@Valid Bill bill, HttpServletResponse response) {
        Bill billSaved = billRepository.save(bill);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, billSaved.getId()));
        return billSaved;
    }
}
