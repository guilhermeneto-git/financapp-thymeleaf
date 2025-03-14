package com.gneto.financapp.dao;

import com.gneto.financapp.entity.Account;
import com.gneto.financapp.entity.Type;
import com.gneto.financapp.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final EntityManager entityManager;

    @Autowired
    public AccountRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Account save(Account account) {
        return entityManager.merge(account);
    }

    @Override
    public void delete(Account account) {
        entityManager.detach(account);
    }

    @Override
    public List<Account> findAllByUserAndByTypeAndByDueMonth(User user, Type type, Date date) {
        return List.of();
    }

    @Override
    public List<Account> findAllByUserAndByTypeAndByDueDay(User user, Type type, Date date) {
        return List.of();
    }
}
