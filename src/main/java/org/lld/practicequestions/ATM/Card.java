package org.lld.practicequestions.ATM;

import java.time.LocalDate;

public class Card {
    private String cardNumber;
    private int cvv;
    private int pin;
    private BankAccount linkedAccount;

    public Card(String cardNumber, int pin, int cvv, BankAccount bankAccount){
        this.cardNumber = cardNumber;
        this.cvv=cvv;
        this.pin=pin;
        this.linkedAccount = bankAccount;
    }

    public int getPin(){
        return this.pin;
    }
    public double getBalance(){
        return linkedAccount.balance;
    }
    public void deductAccountBalance(int amount){
        linkedAccount.deduct(amount);
    }
}
