package org.lld.practicequestions.ATM;

import java.util.Collections;
import java.util.TreeMap;

import org.lld.practicequestions.ATM.ATMStates.ATMState;
import org.lld.practicequestions.ATM.ATMStates.IdleState;

public class ATM {

    ATMState currentATMState;
    TreeMap<Integer, Integer> notes;
    int totalATMBalance;

    public ATM(){
        this.currentATMState = new IdleState();
        this.notes = new TreeMap<>(Collections.reverseOrder());
        this.totalATMBalance = 0;
    }


    public void setCurrentATMState(ATMState newATMState) {
        this.currentATMState = newATMState;
    }
    public ATMState getCurrentATMState(){
        return currentATMState;
    }

    public void addCash(int denomination, int count){
        notes.compute(denomination, (k,v) -> v == null ? count : v + count);
        totalATMBalance += denomination*count;
    }

    public int getATMBalance(){
        return totalATMBalance;
    }

    public boolean deductBalance(int amount){

        TreeMap<Integer,Integer> notesDeducted = new TreeMap<>(Collections.reverseOrder());

        for(int denomination : notes.keySet()){
            int count = notes.get(denomination);
            int notesToDeduct = amount/denomination;
            notesToDeduct = Math.min(count,notesToDeduct);
            notesDeducted.put(denomination,notesToDeduct);
            amount = amount - denomination*notesToDeduct;
        }


        if(amount!=0){
            return false;
        }

        for(int denomination : notesDeducted.keySet()){
            int count = notesDeducted.get(denomination);
            notes.compute(denomination, (k,v) -> v == null ? count : v - count);
            totalATMBalance -= denomination*count;
        }

        return true;


    }
}
