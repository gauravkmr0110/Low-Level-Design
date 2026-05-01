package org.lld.practicequestions.splitwise.user;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final List<User> users;

    public UserController() {
        this.users = new ArrayList<>();
    }

    public User addUser(String userId, String name) {
        User user = new User(userId, name);
        users.add(user);
        return user;
    }

    public User getUserById(String userId) {
        return users.stream()
            .filter(user -> user.getUserId().equals(userId))
            .findFirst()
            .orElse(null);
    }

    public void removeUser(String userId) {
        users.removeIf(user -> user.getUserId().equals(userId));
    }

    public List<User> getAllUsers() {
        return users;
    }
}
