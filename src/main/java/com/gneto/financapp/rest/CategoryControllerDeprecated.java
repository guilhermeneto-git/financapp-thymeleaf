package com.gneto.financapp.rest;

import com.gneto.financapp.entity.Category;
import com.gneto.financapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/none")
@Deprecated
public class CategoryControllerDeprecated {

    private final CategoryService categoryService;

    @Autowired
    public CategoryControllerDeprecated(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    public Category findById(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.findById(categoryId);
    }

    @PostMapping
    public Category add(@RequestBody Category category) {
        category.setId(null);
        return categoryService.save(category);
    }

    @PutMapping
    public Category update(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteById(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteById(categoryId);
    }

}
