package org.lld.practicequestions.splitwise.group;

import java.util.ArrayList;
import java.util.List;
import org.lld.practicequestions.splitwise.user.User;

public class GroupController {

    private final List<Group> groups;

    public GroupController() {
        this.groups = new ArrayList<>();
    }

    public Group createGroup(String groupId, String groupName, List<User> members) {
        Group group = new Group(groupId, groupName, members);
        groups.add(group);
        return group;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
