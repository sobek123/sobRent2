package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository< Role,Long > {
    Optional<Role> findByName(String role_user);
}
