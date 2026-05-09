package org.lld.practicequestions.ATM.ATMStates;

import org.lld.practicequestions.ATM.ATM;
import org.lld.practicequestions.ATM.Card;

public class CheckBalanceState extends ATMState{
    @Override
    public void displayBalance(ATM atm, Card card) {
        System.out.println("Your current balanceis: " + card.getBalance());
        System.out.println("Transaction completed, please collect your card");
        atm.setCurrentATMState(new IdleState());

    }
}
