package com.edurbs.bill.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edurbs.bill.api.model.Category;
import com.edurbs.bill.api.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping()
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

}