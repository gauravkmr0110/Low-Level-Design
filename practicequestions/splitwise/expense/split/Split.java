package org.lld.practicequestions.splitwise.expense.split;

import org.lld.practicequestions.splitwise.user.User;

public class Split {

    User user;
    double amountOwed;

    public Split(User user, double amountOwed) {
        this.user = user;
        this.amountOwed = amountOwed;
    }

    public User getUser() {
        return user;
    }
    
    public double getAmountOwed() {
        return amountOwed;
    }
    
}
