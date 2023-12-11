package com.edurbs.bill.api.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="person")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id; 

    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @NotNull
    private Boolean active;

    @Embedded
    private Address address;
    
    public void activate(){
        this.active=true;
    }

    public void inactivate(){
        this.active=false;
    }

    public boolean isActive(){
        return this.active == true;
    }

    public boolean isInactive(){
        return this.active == false;
    }
}
