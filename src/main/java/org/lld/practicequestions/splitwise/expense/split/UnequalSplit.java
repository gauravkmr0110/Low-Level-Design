package org.lld.practicequestions.splitwise.expense.split;

import java.util.List;

public class UnequalSplit implements SplitExpense {

    @Override
    public boolean validateSplit(List<Split> splits, double amount) {
        // Implementation for unequal split validation
        return true;
    }

}
