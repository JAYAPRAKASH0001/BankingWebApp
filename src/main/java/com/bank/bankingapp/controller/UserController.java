package com.bank.bankingapp.controller;


import com.bank.bankingapp.model.User;
import com.bank.bankingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /* TODO:
       1. Registration
            - POST request: That gets the user details and save it in db.
       2. Withdraw
            - POST requset: Validates the amount balance and approve the withdrawal req.
       3. Deposit
            - POST request: Validate the user and update the balance.
       4. Transaction
            - POST request: Validates the users and balance, then perform a valid transaction.
       5. Account View -> Balance
            - GET request: That shows the details of Account Holder.
     */

    @PostMapping("register")
    public String register(@RequestParam("Name") String name
                            ,@RequestParam("Email") String email
                            ,@RequestParam("Password") String password
                            ,@RequestParam("PhoneNo") long phoneNo
                            ,@RequestParam("Dob") LocalDate dob) {
        return userService.register(name,email,password,phoneNo,dob);
    }
    @PostMapping("withdraw")
    public String withdraw(@RequestParam("Password") String password ,
                           @RequestParam("Id") int id,
                           @RequestParam("withdrawlAmount") int withdrawlAmount)
    {
        try{
            return userService.withdraw(id,password,withdrawlAmount);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

}
