package ma.emsi.jpa.repositories;

import ma.emsi.jpa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // pour indiquer que c'est un Component de la couche DAO
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleName(String roleName);
}
