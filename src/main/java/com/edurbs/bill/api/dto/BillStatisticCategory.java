package com.edurbs.bill.api.dto;

import java.math.BigDecimal;
import java.util.Locale.Category;

public class BillStatisticCategory {

    private Category category;
    private BigDecimal total;

    public BillStatisticCategory(Category category, BigDecimal total) {
        this.category = category;
        this.total = total;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    

}
