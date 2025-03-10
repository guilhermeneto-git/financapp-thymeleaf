package com.gneto.financapp.service;

import com.gneto.financapp.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Integer id);

    Category save(Category category);

    void deleteById(Integer id);

}
