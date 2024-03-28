package ma.emsi.jpa.service;

import ma.emsi.jpa.entities.Role;
import ma.emsi.jpa.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    Role addRole(Role role);
    User findUserByUserName(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    User authenticate(String username, String password);

}
