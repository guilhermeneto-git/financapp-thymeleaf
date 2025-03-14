package com.gneto.financapp.dao;

import com.gneto.financapp.entity.Account;
import com.gneto.financapp.entity.Type;
import com.gneto.financapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AccountRepository  {

    Account findById(Integer id);

    Account save(Account account);

    void deleteById(Integer id);

    List<Account> findAllByUserAndByTypeAndByDueMonth(User user, Type type, Date date);

    List<Account> findAllByUserAndByTypeAndByDueDay(User user, Type type, Date date);

}
