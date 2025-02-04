package com.bank.bankingapp.service;


import com.bank.bankingapp.model.User;
import com.bank.bankingapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

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

        return "Withdrawal successful. Your balance is " + user.getBalance();
    }


}
