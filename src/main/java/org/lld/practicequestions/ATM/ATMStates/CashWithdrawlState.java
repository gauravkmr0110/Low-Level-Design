package org.lld.practicequestions.ATM.ATMStates;

import org.lld.practicequestions.ATM.ATM;
import org.lld.practicequestions.ATM.Card;

public class CashWithdrawlState extends ATMState{

    @Override
    public void withdrawCash(ATM atm, Card card, int amount) {
        if(atm.getATMBalance() < amount){
            System.out.println("Insufficient fund in ATM, please collect your card");
            atm.setCurrentATMState(new IdleState());
            return;
        }

        else if(amount > card.getBalance()){
            System.out.println("Insufficient fund in bank account, please collect your card");
            atm.setCurrentATMState(new IdleState());
            return;
        }

        boolean success = atm.deductBalance(amount);

        if(success==false){
            System.out.println("Please enter amount in multiple of 500");
            return;
        }
        card.deductAccountBalance(amount);

        System.out.println("Transaction completed, please collect cash");



    }
}


/*

*/
