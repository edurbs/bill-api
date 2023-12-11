package com.edurbs.bill.api.repository.bill;

import java.util.List;

import com.edurbs.bill.api.model.Bill;
import com.edurbs.bill.api.repository.filter.BillFilter;

public interface BillRepositoryQuery {
    public List<Bill> filter(BillFilter billFilter);
}
