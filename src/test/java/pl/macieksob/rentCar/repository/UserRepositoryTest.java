//package pl.macieksob.rentCar.repository;
//
//import org.apache.tomcat.jni.Local;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.UserRepository;
//
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void saveUserTest(){
//        User user = new User(1L, "Damian", "Bielski", "damainekbiel@gmail.com", "14-667", "Warszawa", "556777434", "Lawendowa", "5", 4,
//                "WitamWszystkich123!", "99453424567", LocalDateTime.now(), LocalDate.of(1992,11,11),"gert54654gdfgf","gertge5654yrtyhwerght",
//                true, Set.of(new Role("ROLE_ADMIN")),null,null);
//
//        userRepository.save(user);
//
//        assertThat(user.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void deleteUserTest(){
//        User user = userRepository.findById(1L).get();
//
//        userRepository.delete(user);
//
//        User user2 = null;
//
//        Optional<User> opUser =  userRepository.findByEmail("macieksob25@gmail.com");
//
//        if(opUser.isPresent()){
//            user2 = opUser.get();
//        }
//
//        assertThat(user2).isNull();
//    }
//
//    @Test
//    public void getUserTest(){
//        User user = userRepository.findById(1L).get();
//
//        assertThat(user.getId()).isEqualTo(1L);
//    }
//
//    @Test
//    public void getUserListTest(){
//        List<User> all = userRepository.findAll();
//
//        assertThat(all.size()).isGreaterThan(0);
//    }
//
//    @Test
//    public void updateUserTest(){
//        User user = userRepository.findById(1L).get();
//
//        user.setEmail("macieksoe@wp.pl");
//
//        User save = userRepository.save(user);
//
//        assertThat(save.getEmail()).isEqualTo("macieksoe@wp.pl");
//    }
//}