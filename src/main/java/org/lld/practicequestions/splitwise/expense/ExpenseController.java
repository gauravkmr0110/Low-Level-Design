package org.lld.practicequestions.splitwise.expense;

import java.util.ArrayList;
import java.util.List;
import org.lld.practicequestions.splitwise.user.BalanceSheetController;
import org.lld.practicequestions.splitwise.user.User;
import org.lld.practicequestions.splitwise.expense.split.Split;
import org.lld.practicequestions.splitwise.expense.split.SplitExpense;

public class ExpenseController {

    private final List<Expense> expenses;
    private final BalanceSheetController balanceSheetController;

    public ExpenseController() {
        this(new BalanceSheetController());
    }

    public ExpenseController(BalanceSheetController balanceSheetController) {
        this.balanceSheetController = balanceSheetController;
        this.expenses = new ArrayList<>();
    }

    public Expense createExpense(String id, String desc, double amount, User paidBy, ExpenseSplitType splitType, List<Split> splitDetails) {
        Expense expense = new Expense(id, desc, amount, paidBy, splitType, splitDetails);
        SplitExpense splitExpense = ExpenseFactory.getSplitType(splitType);

        if (!splitExpense.validateSplit(splitDetails, amount)) {
            throw new IllegalArgumentException("Invalid split details for the given amount");
        }

        expenses.add(expense);
        balanceSheetController.updateUserBalanceSheet(paidBy, expense);
        return expense;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
