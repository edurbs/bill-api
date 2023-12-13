package com.edurbs.bill.api.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edurbs.bill.api.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("SELECT b FROM Bill b WHERE (:description is null or b.description LIKE %:description%) " 
            + "and (:fromDueDate is null or b.dueDate >= :fromDueDate) "
            + "and (:toDueDate is null or b.dueDate <= :toDueDate)")            
    Page<Bill> queryFiltredBills(Pageable pageable, @Param("description") String description, @Param("fromDueDate") LocalDate fromDueDate,
            @Param("toDueDate") LocalDate toDueDate);            
}
