package com.codecrackers.bankapi.repository;

import com.codecrackers.bankapi.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

     @Query(value = "select * from deposits where payee_id = ?1", nativeQuery = true)
     List<Deposit> findAllDepositsByAccountId(Long accountId);

}
