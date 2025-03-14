package com.gneto.financapp.dao;

import com.gneto.financapp.entity.Account;
import com.gneto.financapp.entity.Type;
import com.gneto.financapp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
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
    public Account findById(Integer id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return entityManager.merge(account);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        System.out.println("step 3");

        Account account = entityManager.find(Account.class, id);
        entityManager.remove(account);
    }

    @Override
    public List<Account> findAllByUserAndByTypeAndByDueMonth(User user, Type type, Date dueDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dueDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        TypedQuery<Account> query = entityManager.createQuery(
                "select ac from Account ac where ac.user = :user and ac.type = :type and " +
                        "(ac.dueDate between :startDate and :endDate) " +
                        "order by ac.dueDate asc, ac.name asc",
                Account.class
        );
        query.setParameter("user", user);
        query.setParameter("type", type);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        return query.getResultList();
    }


    @Override
    public List<Account> findAllByUserAndByTypeAndByDueDay(User user, Type type, Date dueDate) {
        TypedQuery<Account> query = entityManager.createQuery(
                "select ac from Account ac where ac.user = :user and ac.type = :type and ac.dueDate = :dueDate",
                Account.class
        );
        query.setParameter("user", user);
        query.setParameter("type", type);
        query.setParameter("dueDate", dueDate);

        return query.getResultList();
    }
}
