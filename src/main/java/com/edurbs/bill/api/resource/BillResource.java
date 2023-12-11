package com.edurbs.bill.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.bill.api.model.Bill;
import com.edurbs.bill.api.service.BillService;


@RestController
@RequestMapping("/bills")
public class BillResource {

    @Autowired
    private BillService billService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bill> findAll() {
        return billService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> findById(@PathVariable Long id) {
        return billService.findById(id);
    }
    
    
}
