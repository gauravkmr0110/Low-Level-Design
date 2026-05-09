package practicequestions.ATM.ATMStates;

import practicequestions.ATM.ATM;
import practicequestions.ATM.Card;

public class HasCardState extends ATMState {
    @Override
    public void authenticatePin(ATM atm, Card card, int pin){
        if(pin!=card.getPin()){
            System.out.println("Incorrect pin entered");
            atm.setCurrentATMState(new IdleState());
            return;
        }

        System.out.println("Pin validated, please select operation to perform");
        atm.setCurrentATMState(new SelectOperationState());

    }
}
