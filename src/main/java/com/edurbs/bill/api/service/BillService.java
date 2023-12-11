package com.edurbs.bill.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edurbs.bill.api.model.Bill;
import com.edurbs.bill.api.repository.BillRepository;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> findAll(){
        return billRepository.findAll();
    }

    public ResponseEntity<Bill> findById(Long id) {
        return billRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
