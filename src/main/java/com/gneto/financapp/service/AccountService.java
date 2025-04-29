package com.gneto.financapp.service;

import com.gneto.financapp.entity.Account;
import com.gneto.financapp.entity.AccountType;
import com.gneto.financapp.entity.User;

import java.util.Date;
import java.util.List;

public interface AccountService {

    Account findById(Integer id);
    Account save(Account save);
    void deleteById(Integer id);

    List<Account> findAllByUserAndByTypeAndByDueMonth(User user, AccountType type, Date date);
    List<Account> findAllByUserAndByTypeAndByDueDay(User user, AccountType type, Date date);

}
