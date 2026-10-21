package com.benjaminknust.payments;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class AccountRepository {

    @PersistenceContext
    private EntityManager em;

    public Account save(Account account) {
        em.persist(account);
        return account;
    }

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(em.find(Account.class, id));
    }
}