package ma.emsi.jpa.repositories;

import ma.emsi.jpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoriy extends JpaRepository<User, String> {
    User findByUsername(String username);
}
