package org.lld.practicequestions.splitwise.user;

public class User {
    private final String userId;
    private final String name;
    private final UserBalanceSheet balanceSheet;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.balanceSheet = new UserBalanceSheet();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public UserBalanceSheet getBalanceSheet() {
        return balanceSheet;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return userId.equals(other.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
