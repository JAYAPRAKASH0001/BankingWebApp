package com.bank.bankingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int sno;
    int fromId;
    Integer toId;
    String type;
    int amount;
    public Transactions(int fromId, Integer toId, String type, int amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.type = type;
        this.amount = amount;
    }
}
