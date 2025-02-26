package com.bank.bankingapp.controller;


import com.bank.bankingapp.model.User;
import com.bank.bankingapp.model.Transactions;
import com.bank.bankingapp.service.UserService;
import com.bank.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

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
    public String withdraw(@RequestParam("Id") int id,
                           @RequestParam("Password") String password ,
                           @RequestParam("withdrawlAmount") int withdrawlAmount)
    {
        try{
            return userService.withdraw(id,password,withdrawlAmount);

        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("login")
    public String login(@RequestParam("Id") int id
                        ,@RequestParam("Password") String password) {
        try {
            return userService.authenticate(id, password);
        }catch (Exception e) {
            return e.getMessage();
        }
    }
    @PostMapping("deposit")
    public String deposit(@RequestParam("Id") int id,
                          @RequestParam("Password") String password,
                          @RequestParam("amount") int amount){
        try {
            userService.deposit(id, password, amount);
        }catch (Exception e) {
            return e.getMessage();
        }
        return "deposit successful";
    }
    @GetMapping("transactions")
    public List<Transactions> transactions(@RequestParam("Id") int id) {
        return transactionService.getTransactions(id);
    }
    @PostMapping("transfer")
    public String transfer(@RequestParam("fromId") int fromId,
                           @RequestParam("toId") int toId,
                           int amount) {
        try {
            return userService.transfer(fromId,toId,amount);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @GetMapping("getbalance")
    public String getbalance(int id) {
        try{
            return userService.getbalance(id);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("accountdetails")
    public User getaccdetails(int id){
        return userService.getaccdetails(id);
    }
}
