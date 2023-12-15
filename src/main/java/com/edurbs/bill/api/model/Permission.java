package com.edurbs.bill.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="permission")
@Data
public class Permission {

    @Id    
    private Long id;

    @NotBlank
    private String description;
}
