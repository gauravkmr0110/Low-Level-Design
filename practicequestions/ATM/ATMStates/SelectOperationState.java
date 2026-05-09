package practicequestions.ATM.ATMStates;

import java.util.Scanner;

import practicequestions.ATM.ATM;
import practicequestions.ATM.Card;
import practicequestions.ATM.TransactionType;

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
