package org.lld.practicequestions.splitwise.expense.split;

import java.util.List;

public class EqualSplit implements SplitExpense {

    @Override
    public boolean validateSplit(List<Split> splits, double amount) {
        // Implementation for equal split validation
        return true;
    }

}