package org.lld.practicequestions.ATM;

public class Main {

    public static void main(String[] args){
        ATMService atmService = ATMService.getATMServiceObj();
        ATM atm1 = new ATM();
        atm1.addCash(100, 20);
        atm1.addCash(200, 30);
        atm1.addCash(500, 40);
        atm1.addCash(2000, 50);

        ATM atm2 = new ATM();
        atm2.addCash(100, 20);
        

        BankAccount account1 = new BankAccount("Gaurav","123456",400000);
        BankAccount account2 = new BankAccount("Rahul","346828234",1200055);

        Card card1 = new Card("123456789",1224,146,account1);
        Card card2 = new Card("456781234",1678,326,account2);

        atmService.addATM(atm1);
        atmService.addATM(atm2);

        atmService.start(atm1,card1);

    }
}


/*

Main
  ↓
ATMService.start()
  ↓
ATM.currentState.insertCard()
  ↓
IdleState → HasCardState
  ↓
authenticatePin()
  ↓
HasCardState → SelectOperationState
  ↓
selectOperation()
  ↓
 ┌───────────────────────┐
 │                       │
 ↓                       ↓

CheckBalanceState     CashWithdrawlState
       ↓                     ↓
 displayBalance()      withdrawCash()
       ↓                     ↓
    IdleState            IdleState
*/
