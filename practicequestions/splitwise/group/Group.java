package org.lld.practicequestions.splitwise.group;

import java.util.ArrayList;
import java.util.List;
import org.lld.practicequestions.splitwise.user.User;
import org.lld.practicequestions.splitwise.expense.Expense;
import org.lld.practicequestions.splitwise.expense.ExpenseController;

public class Group {

    String groupId;
    String groupName;
    List<User> members;
    List<Expense> expenses;
    ExpenseController groupExpenseController;

    public Group(String groupId, String groupName, List<User> members) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.members = members;
        this.expenses = new ArrayList<>();
        this.groupExpenseController = new ExpenseController();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public String getGroupId() {
        return groupId;
    }
}
