package com.edurbs.bill.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.bill.api.event.ResourceCreatedEvent;
import com.edurbs.bill.api.model.Category;
import com.edurbs.bill.api.repository.CategoryRepository;



@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping()
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    @PostMapping()    
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category newCategory = categoryRepository.save(category);        
        publisher.publishEvent(new ResourceCreatedEvent(this, response, newCategory.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);        
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Long id) {        
        return categoryRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    
    
    
}
