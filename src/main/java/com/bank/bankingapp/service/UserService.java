package com.bank.bankingapp.service;


import com.bank.bankingapp.model.User;
import com.bank.bankingapp.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private TransactionService transactionService;

    int id=101;

    public String register(String name, String email, String password, long phoneNo, LocalDate dob) {

        int tempId=id*1000;
        int min=100, max=1000;
        tempId += (min + (int)(Math.random() * ((max - min) + 1)));//to generate userid
        User user=new User(tempId,name,email,password,phoneNo,dob,0);
        userRepo.save(user);
        return "your id is "+tempId;
        //check the database for any existing acc.no = generated. If suppose regenerate the id. --> yet to be finished
    }

    public String withdraw(int id, String password, int withdrawalAmount) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        if (withdrawalAmount > user.getBalance()) {  // Fix: Corrected condition
            throw new IllegalArgumentException("Insufficient balance");
        }

        user.setBalance(user.getBalance() - withdrawalAmount);
        userRepo.save(user);
        transactionService.storeTransactions(id,null,"withdraw",withdrawalAmount);

        return "Withdrawal successful. Your balance is " + user.getBalance();
    }


    public String authenticate(int id, String password) throws Exception { //login

        User user= userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return "successfully logged in";
    }

    public void deposit(int id, String password, int amount) {

       User user= userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
       user.setBalance(user.getBalance() + amount);
       userRepo.save(user);
       transactionService.storeTransactions(id,null,"deposit",amount);
    }

    @Transactional
    public String transfer(int fromId, int toId, int amount) {
        User userFrom = userRepo.findById(fromId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        User userTo = userRepo.findById(toId).orElseThrow(() -> new IllegalArgumentException("User not found."));
        if (amount > userFrom.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        userFrom.setBalance(userFrom.getBalance()-amount);
        userTo.setBalance(userTo.getBalance() + amount);

        userRepo.save(userFrom);
        userRepo.save(userTo);

        transactionService.storeTransactions(fromId,toId,"transfer",amount);
        return "Transfer successful. Your balance is " + userFrom.getBalance();
    }

    public String getbalance(int id) {
        User user=userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
        return "your balance is : "+ user.getBalance();
    }

    public User getaccdetails(int id) {
        User user=userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
        return user;
    }
}
