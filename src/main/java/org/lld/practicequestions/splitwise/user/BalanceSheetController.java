package org.lld.practicequestions.splitwise.user;

import org.lld.practicequestions.splitwise.expense.Expense;
import org.lld.practicequestions.splitwise.expense.split.Split;

public class BalanceSheetController {

    public void updateUserBalanceSheet(User paidBy, Expense expense) {
        for (Split split : expense.getSplits()) {
            User user = split.getUser();
            if (user.equals(paidBy)) {
                continue;
            }

            double amountOwed = split.getAmountOwed();
            paidBy.getBalanceSheet().updateBalance(user, 0, amountOwed);
            user.getBalanceSheet().updateBalance(paidBy, amountOwed, 0);
        }
    }
}
