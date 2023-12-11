package com.edurbs.bill.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDueDate;
}
