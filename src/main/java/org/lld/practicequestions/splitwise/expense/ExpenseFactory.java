package org.lld.practicequestions.splitwise.expense;

import org.lld.practicequestions.splitwise.expense.split.SplitExpense;
import org.lld.practicequestions.splitwise.expense.split.EqualSplit;
import org.lld.practicequestions.splitwise.expense.split.UnequalSplit;
import org.lld.practicequestions.splitwise.expense.split.PercentageSplit;

public class ExpenseFactory {
    
    public static SplitExpense getSplitType(ExpenseSplitType expenseSplitType) {
        switch (expenseSplitType) {
            case EQUAL:
                return new EqualSplit();
            case UNEQUAL:
                return new UnequalSplit();
            case PERCENTAGE:
                return new PercentageSplit();
            default:
                throw new IllegalArgumentException("Invalid split type");
        }
    }
}
