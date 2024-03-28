package ma.emsi.jpa.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.jpa.entities.Role;
import ma.emsi.jpa.entities.User;
import ma.emsi.jpa.repositories.RoleRepository;
import ma.emsi.jpa.repositories.UserRepositoriy;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service        // pour gerer les couches metier
@Transactional  // pour qu'il soit transactionnel
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepositoriy userRepositoriy;
    private RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepositoriy.save(user);
    }

    @Override
    public Role addRole(Role role) {
        role.setRoleId(UUID.randomUUID().toString());
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepositoriy.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(roleName);
        if (user.getRoles() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepositoriy.findByUsername(username);
        if (user == null) {
            System.out.println("username does not exist");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("username or password invalid");
    }
}
