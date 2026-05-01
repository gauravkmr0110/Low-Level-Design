package org.lld.practicequestions.splitwise.expense;

import java.util.List;
import org.lld.practicequestions.splitwise.user.User;
import org.lld.practicequestions.splitwise.expense.split.Split;

public class Expense {
    private final String id;
    private final String description;
    private final double amount;
    private final User paidBy;
    private final ExpenseSplitType splitType;
    private final List<Split> splits;

    Expense(String id, String description, double amount, User paidBy, ExpenseSplitType splitType, List<Split> splits) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitType = splitType;
        this.splits = splits;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public ExpenseSplitType getSplitType() {
        return splitType;
    }

    public List<Split> getSplits() {
        return splits;
    }
}