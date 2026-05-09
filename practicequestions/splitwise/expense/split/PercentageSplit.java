package org.lld.practicequestions.splitwise.expense.split;

import java.util.List;

public class PercentageSplit implements SplitExpense {

    @Override
    public boolean validateSplit(List<Split> splits, double amount) {
        // Implementation for percentage split validation
        return true;
    }

}