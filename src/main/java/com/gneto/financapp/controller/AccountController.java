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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/list")
    public String list(Model model, @RequestParam("userid") String userId) {
        User user = userService.findById(userId);
        List<Account> accounts = accountService.findAllByUserAndByTypeAndByDueMonth(user, Type.EXPENSE, new Date(System.currentTimeMillis()));

        model.addAttribute("accounts", accounts);

        return "/accounts/accounts-list";
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
    public String save(@ModelAttribute("account") Account account, BindingResult bindingResult) {
        System.out.println(account);

        if (bindingResult.hasErrors()) {
            return "/accounts/account-form";
        }

        accountService.save(account);

        return "redirect:/accounts/list?userid="+account.getUser().getId();
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("accountId") Integer id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", Type.values());

        return "/accounts/account-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("accountId") Integer id) {
        System.out.println("step 1");

        accountService.deleteById(id);

        return "redirect:/accounts/list?userid=guilherme";
    }

}
