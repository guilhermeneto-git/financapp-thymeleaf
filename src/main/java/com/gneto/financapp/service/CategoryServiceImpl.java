package com.gneto.financapp.service;

import com.gneto.financapp.dao.CategoryRepository;
import com.gneto.financapp.entity.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> result = categoryRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new EntityNotFoundException("Category not found by id - " + id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Category not deleted by id - " + id);
        }

        categoryRepository.deleteById(id);
    }

}
