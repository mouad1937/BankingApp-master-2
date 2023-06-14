package com.codecrackers.bankapi.repository;

import com.codecrackers.bankapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
    Iterable<Transaction> findAllTransactionsByAccountId(Long accountId);
}
