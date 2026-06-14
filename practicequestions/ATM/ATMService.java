package practicequestions.ATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import practicequestions.ATM.ATMStates.IdleState;

public class ATMService {
    private static List<ATM>atmList;
    private static ATMService instance;

    private ATMService(){

    };

    public static ATMService getATMServiceObj(){
        if(instance==null){
            atmList = new ArrayList<>();
            instance = new ATMService();
        }
        return instance;
    }

    public void addATM(ATM atm){
        atmList.add(atm);
    }

    public void start(ATM atm, Card card){
        atm.getCurrentATMState().insertCard(atm,card);
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter pin: ");
        int pin = sc.nextInt();
        atm.getCurrentATMState().authenticatePin(atm,card,pin);
        if(atm.getCurrentATMState() instanceof IdleState){
            System.out.println("Card is returned");
            return;
        }
        System.out.println("Available Operations");
        System.out.println("1. Balance Check");
        System.out.println("2. Cash Withdraw");
        System.out.println("Please enter 1 for balance check 2 for cash withdrwal");
        int opr = sc.nextInt();

        switch (opr){

            case 1:
                atm.getCurrentATMState().selectOperation(atm, card, TransactionType.BALANCE_CHECK);
                atm.getCurrentATMState().displayBalance(atm, card);
                break;

            case 2:
                atm.getCurrentATMState().selectOperation(atm, card, TransactionType.WITHDRAWAL);
                System.out.println("Please enter amount to withdraw");
                int amount = sc.nextInt();
                atm.getCurrentATMState().withdrawCash(atm, card, amount);
                break;

            default:
                System.out.println("Invalid option");
                return;

        }



    }



}
