package com.edurbs.bill.api.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Embeddable
public class Address {

    @Size(max = 255)
    private String street;

    @Size(max = 10)
    private String number;

    @Size(max = 100)
    private String complement;

    @Size(max = 100)
    private String neighborhood;

    @Size(max = 100)
    private String city;

    @Size(max = 2)
    private String state;

    @Size(max = 10)
    private String pobox;
}
