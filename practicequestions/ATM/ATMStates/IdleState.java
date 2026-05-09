package practicequestions.ATM.ATMStates;

import practicequestions.ATM.ATM;
import practicequestions.ATM.Card;

public class IdleState extends ATMState{
    @Override
    public void insertCard(ATM atm, Card card){
        System.out.println("Card is inserted");
        atm.setCurrentATMState(new HasCardState());
    }
}
