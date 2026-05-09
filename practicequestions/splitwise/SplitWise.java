package org.lld.practicequestions.splitwise;

import java.util.Arrays;
import java.util.List;
import org.lld.practicequestions.splitwise.user.User;
import org.lld.practicequestions.splitwise.group.Group;
import org.lld.practicequestions.splitwise.expense.split.Split;
import org.lld.practicequestions.splitwise.expense.ExpenseSplitType;
import org.lld.practicequestions.splitwise.user.BalanceSheetController;
import org.lld.practicequestions.splitwise.user.UserController;
import org.lld.practicequestions.splitwise.expense.ExpenseController;
import org.lld.practicequestions.splitwise.group.GroupController;


public class SplitWise {
    private final GroupController groupController;
    private final ExpenseController expenseController;
    private final BalanceSheetController balanceSheetController;
    private final UserController userController;

    public SplitWise() {
        this.balanceSheetController = new BalanceSheetController();
        this.expenseController = new ExpenseController(balanceSheetController);
        this.userController = new UserController();
        this.groupController = new GroupController();
    }

    public void runDemo() {
        User alice = userController.addUser("u1", "Alice");
        User bob = userController.addUser("u2", "Bob");
        User charlie = userController.addUser("u3", "Charlie");

        Group trip = groupController.createGroup("g1", "Trip", Arrays.asList(alice, bob, charlie));

        List<Split> splits = Arrays.asList(
            new Split(alice, 30),
            new Split(bob, 30),
            new Split(charlie, 30)
        );

        expenseController.createExpense("e1", "Dinner", 90, alice, ExpenseSplitType.EQUAL, splits);
        trip.addExpense(expenseController.getExpenses().get(0));

        printBalances();
    }

    private void printBalances() {
        for (User user : userController.getAllUsers()) {
            System.out.println(user.getName() + " balances: " + user.getBalanceSheet());
        }
    }
}
