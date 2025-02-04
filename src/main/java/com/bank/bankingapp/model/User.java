package com.bank.bankingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private int Id;
    private String Name;
    private String Email;
    private String Password;
    private long PhoneNo;
    private LocalDate Dob;
    private long Balance;


}
