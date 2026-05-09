package org.lld.practicequestions.ATM;

public class BankAccount {
    String userName;
    String accountNumber;
    double balance;

    public BankAccount(String userName, String accountNumber, double balance){
        this.accountNumber=accountNumber;
        this.userName=userName;
        this.balance = balance;
    }

    public void deduct(int amount){
        balance-=amount;
    }

}
