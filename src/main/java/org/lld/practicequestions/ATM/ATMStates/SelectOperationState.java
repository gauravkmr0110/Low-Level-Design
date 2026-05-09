package org.lld.practicequestions.ATM.ATMStates;

import java.util.Scanner;

import org.lld.practicequestions.ATM.ATM;
import org.lld.practicequestions.ATM.Card;
import org.lld.practicequestions.ATM.TransactionType;

public class SelectOperationState extends ATMState{

    @Override
    public void selectOperation(ATM atm, Card card, TransactionType txnType){
       switch (txnType){

           case WITHDRAWAL:
               atm.setCurrentATMState(new CashWithdrawlState());
               break;

           case BALANCE_CHECK:
               atm.setCurrentATMState(new CheckBalanceState());
               break;

           default:
               System.out.println("Invalid option");
       }
    }
}
