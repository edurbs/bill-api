package com.edurbs.bill.api.repository.bill;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.edurbs.bill.api.model.Bill;
import com.edurbs.bill.api.repository.filter.BillFilter;

public class BillRepositoryImpl implements BillRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Bill> filter(BillFilter billFilter) {
        
        return null;
    }

}
