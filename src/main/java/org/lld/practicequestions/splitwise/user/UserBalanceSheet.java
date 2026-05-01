package org.lld.practicequestions.splitwise.user;

import java.util.HashMap;
import java.util.Map;

class UserBalanceSheet {

    Map<String, Balance> userVsBalance;
    double totalAmountOwed;
    double totalAmountToGetBack;
    double totalExpenses;

    UserBalanceSheet() {
        userVsBalance = new HashMap<>();
    }

    Balance getBalanceFor(User other) {
        return userVsBalance.computeIfAbsent(other.getUserId(), id -> new Balance());
    }

    void updateBalance(User other, double amountOwed, double amountToGetBack) {
        Balance balance = getBalanceFor(other);
        balance.amountOwed += amountOwed;
        balance.amountToGetBack += amountToGetBack;
        totalAmountOwed += amountOwed;
        totalAmountToGetBack += amountToGetBack;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BalanceSheet{");
        boolean first = true;
        for (Map.Entry<String, Balance> entry : userVsBalance.entrySet()) {
            if (!first) {
                builder.append(", ");
            }
            first = false;
            Balance balance = entry.getValue();
            builder.append(entry.getKey())
                   .append("=[owed=")
                   .append(balance.amountOwed)
                   .append(", getBack=")
                   .append(balance.amountToGetBack)
                   .append("]");
        }
        builder.append('}');
        return builder.toString();
    }
}