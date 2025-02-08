package com.bank.bankingapp.service;

import com.bank.bankingapp.model.Transactions;
import com.bank.bankingapp.repository.TransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionsRepo transactionsRepo;


    public void storeTransactions(int fromId, Integer toId, String description, int amount) {
        Transactions transaction = new Transactions(fromId, toId, description, amount);
        transactionsRepo.save(transaction);
    }

    public List<Transactions> getTransactions(int id){
        List<Transactions> list=transactionsRepo.findByFromId(id);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }
}
