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
}
