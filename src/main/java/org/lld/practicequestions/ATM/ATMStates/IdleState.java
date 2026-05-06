package org.lld.practicequestions.ATM.ATMStates;

import org.lld.practicequestions.ATM.ATM;
import org.lld.practicequestions.ATM.Card;

public class IdleState extends ATMState{
    @Override
    public void insertCard(ATM atm, Card card){
        System.out.println("Card is inserted");
        atm.setCurrentATMState(new HasCardState());
    }
}
