package org.lld.practicequestions.splitwise.expense.split;

import java.util.List;

public interface SplitExpense {

    public boolean validateSplit(List<Split> splits, double amount);
    
}
