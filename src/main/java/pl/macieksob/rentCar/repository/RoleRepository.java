package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.Role;

@Repository
public interface RoleRepository extends JpaRepository< Role,Long > {
}
