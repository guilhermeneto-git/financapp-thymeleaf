package com.gneto.financapp.controller;

import com.gneto.financapp.entity.Account;
import com.gneto.financapp.entity.Category;
import com.gneto.financapp.entity.Type;
import com.gneto.financapp.entity.User;
import com.gneto.financapp.service.AccountService;
import com.gneto.financapp.service.CategoryService;
import com.gneto.financapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService, CategoryService categoryService) {
        this.accountService = accountService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/insert")
    public String insert(Model model, @RequestParam("userid") String userId) {
        User user = userService.findById(userId);
        Account newAccount = new Account(user);

        model.addAttribute("account", newAccount);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", Type.values());

        return "/accounts/account-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("account") Account account) {
        System.out.println(account);

        return "redirect:/accounts/insert?userid="+account.getUser().getId();
    }

}
