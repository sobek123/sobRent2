package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository< User,Long > {

//    Optional<User> findByUsername(String username);

//    @Query(nativeQuery = true,value = "")
//    List<User> findAllByKeyword(String keyword, Pageable pageable);

    UserDTO findByVerificationCode(String verificationCode);

    UserDTO findByResetPasswordToken(String token);

    @Query("UPDATE User U SET U.enabled = TRUE WHERE U.id = ?1")
    void setEnable(Long id);

    UserDTO findByEmail(String email);
}
