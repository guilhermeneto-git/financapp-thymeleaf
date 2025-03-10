package com.gneto.financapp.controller;

import com.gneto.financapp.entity.Category;
import com.gneto.financapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/financapp/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String showCategoryList(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        return "/categories/categories-list";
    }

    @GetMapping("/insert")
    public String insert(Model model) {
        model.addAttribute("category", new Category());

        return "/categories/category-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/categories/category-form";
        }

        categoryService.save(category);

        return "redirect:/financapp/categories/list";
    }

    @GetMapping("/edit")
    public String showCategoryFormForUpdate(@RequestParam("categoryId") Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id));

        return "/categories/category-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("categoryId") Integer id) {
        categoryService.deleteById(id);

        return "redirect:/financapp/categories/list";
    }

}
