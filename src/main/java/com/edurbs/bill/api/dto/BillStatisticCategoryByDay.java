package com.edurbs.bill.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.edurbs.bill.api.model.BillType;

public class BillStatisticCategoryByDay {

    private BillType billType;
    private LocalDate day;
    private BigDecimal total;

    public BillStatisticCategoryByDay(BillType billType, LocalDate day, BigDecimal total) {
        this.billType = billType;
        this.day = day;
        this.total = total;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
