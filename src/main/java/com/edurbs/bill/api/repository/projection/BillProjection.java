package com.edurbs.bill.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.edurbs.bill.api.model.BillType;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BillProjection {

    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate payDate;
    private BigDecimal amount;
    private BillType type;
    private String category;
    private String person;

}
