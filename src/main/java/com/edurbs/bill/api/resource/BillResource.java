package com.edurbs.bill.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.bill.api.exceptionhandler.BillExceptionHandler.Error;
import com.edurbs.bill.api.model.Bill;
import com.edurbs.bill.api.repository.filter.BillFilter;
import com.edurbs.bill.api.repository.projection.BillProjection;
import com.edurbs.bill.api.service.BillService;
import com.edurbs.bill.api.service.exception.PersonInactiveException;
import com.edurbs.bill.api.service.exception.PersonInexistentException;





@RestController
@RequestMapping("/bills")
public class BillResource {

    @Autowired
    private BillService billService;

    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')")
    public ResponseEntity<Bill> findById(@PathVariable Long id) {
        return billService.findById(id);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')")
    public Page<Bill> filter(BillFilter billFilter, Pageable pageable) {
        return billService.filter(billFilter, pageable);
    }
    
    @GetMapping(params = "projection")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and hasAuthority('SCOPE_read')")
    public Page<BillProjection> project(BillFilter billFilter, Pageable pageable) {
        return billService.project(billFilter, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and hasAuthority('SCOPE_write')")
    @ResponseStatus(HttpStatus.CREATED)
    public Bill create(@Valid @RequestBody Bill bill, HttpServletResponse response) {
        return billService.create(bill, response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and hasAuthority('SCOPE_write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id){
        billService.remove(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and hasAuthority('SCOPE_write')")
    public ResponseEntity<Bill> update(@PathVariable Long id, @Valid @RequestBody Bill bill) {
        try {
            Bill savedBill = billService.update(id, bill);
            return ResponseEntity.ok(savedBill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        
     
    }

    @ExceptionHandler({PersonInactiveException.class})
    public ResponseEntity<Object> handlePersonInactiveException(PersonInactiveException ex){
        return getBodyWithError(ex, "person.inactive");        
    } 
    
    @ExceptionHandler({PersonInexistentException.class})
    public ResponseEntity<Object> handlePersonInexistentException(PersonInexistentException ex){
        return getBodyWithError(ex, "person.inexistent");
    }


    private ResponseEntity<Object> getBodyWithError(RuntimeException ex, String errorMessage) {        
        String userMessage = messageSource.getMessage(errorMessage, null,
            LocaleContextHolder.getLocale());
        String debugMessage = ex.toString();
        List<Error> body = Arrays.asList(new Error(userMessage, debugMessage));        
        return ResponseEntity.badRequest().body(body);
    }


    
}
