package org.lld.practicequestions.ATM;

import org.lld.practicequestions.ATM.ATMStates.ATMState;
import org.lld.practicequestions.ATM.ATMStates.IdleState;

public class ATM {
    private static ATM atmObj = new ATM();

    ATMState currentATMState;
    int noOfTwoThousandNotes;
    int noOfFiveHundredNotes;
    int noOfHundredNotes;

    private ATM(){}

    public ATM getATMObjet(){
        atmObj.setCurrentATMState(new IdleState());
        return atmObj;

    }

    public void setCurrentATMState(ATMState newATMState) {
        this.currentATMState = newATMState;
    }
    public ATMState getCurrentATMState(){
        return currentATMState;
    }

    public void addCash(int notes500, int notes2000, int notes100){
        noOfHundredNotes+=notes500;
        noOfTwoThousandNotes+=notes2000;
        noOfFiveHundredNotes+=notes100;
    }
}
