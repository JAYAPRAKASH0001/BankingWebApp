package com.bank.bankingapp.repository;

import com.bank.bankingapp.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions,Integer> {

    @Query(nativeQuery = true,
    value ="select * from transactions where from_id=:id")
    List<Transactions> findByFromId(int id);

}
