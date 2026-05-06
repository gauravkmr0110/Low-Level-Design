package org.lld.practicequestions.ATM.ATMStates;

import org.lld.practicequestions.ATM.ATM;
import org.lld.practicequestions.ATM.Card;
import org.lld.practicequestions.ATM.TransactionType;

public abstract class ATMState {
    public void insertCard(ATM atm, Card card){
        System.out.println("Something went wrong");
    }

    public void authenticatePin(ATM atm, Card card, int pin){
        System.out.println("Something went wrong");
    }

    public void selectOperation(ATM atm, Card card, TransactionType txnType){
        System.out.println("Something went wrong");
    }

    public void withdrawCash(ATM atm, Card card, int amount){
        System.out.println("Something went wrong");
    }

    public void displayBalance(ATM atm, Card card){
        System.out.println("Something went wrong");
    }

    public void returnCard(ATM atm, Card card){
        System.out.println("Something went wrong");
    }
    public void exit(ATM atm){
        System.out.println("Something went wrong");
    }
}
