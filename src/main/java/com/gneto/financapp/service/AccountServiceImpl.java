package com.gneto.financapp.service;

import com.gneto.financapp.dao.AccountRepository;
import com.gneto.financapp.entity.Account;
import com.gneto.financapp.entity.AccountType;
import com.gneto.financapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void deleteById(Integer id) {
        System.out.println("step 2");

        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> findAllByUserAndByTypeAndByDueMonth(User user, AccountType type, Date date) {
        return accountRepository.findAllByUserAndByTypeAndByDueMonth(user, type, date);
    }

    @Override
    public List<Account> findAllByUserAndByTypeAndByDueDay(User user, AccountType type, Date date) {
        return accountRepository.findAllByUserAndByTypeAndByDueDay(user, type, date);
    }

}
