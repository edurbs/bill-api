package com.edurbs.bill.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="bill")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull    
    @NotBlank
    @Size(max = 50)
    private String description;

    @NotNull
    @Column(name="due_date")
    private LocalDate dueDate;

    @Column(name="pay_date")
    private LocalDate payDate;
        
    private BigDecimal amount;

    private String notes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BillType type;

    @ManyToOne
    @JoinColumn(name="id_category")
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name="id_person")
    @NotNull
    private Person person;

}
