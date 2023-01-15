//package pl.macieksob.rentUser.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.UserDTO;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.UserRepository;
//import pl.macieksob.rentCar.service.UserService;
//
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    public void setup(){
//        userService.addWorker(new UserDTO(1L, "Maciek", "Sobolewski", "macieksob25@gmail.com", "15-878", "Bialystok", "514 546 610", "Boczna", "11", 76, "Sobolek2017?", "67328302379",
//                LocalDateTime.now(), LocalDate.of(2000, 12, 12), "fgdgdfg", "dfgdfgdf", true, new Card(), Set.of(new Role("ROLE_ADMIN"))));
//        userService.addWorker(new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//                LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, Set.of(new Role("ROLE_ADMIN"))));
//    }
//    @Test
//    public void addUser(){
//        UserDTO userDTO = userService.addUser(new UserDTO(3L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//                LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, Set.of(new Role("ROLE_ADMIN"))));
//
//        assertNotNull(userService.getUser(3L));
//    }
//
//    @Test
//    public void getUser(){
//        when(userService.getUser(1L)).thenReturn(new UserDTO(1L, "Maciek", "Sobolewski", "macieksob25@gmail.com", "15-878", "Bialystok", "514 546 610", "Boczna", "11", 76, "Sobolek2017?", "67328302379",
//                LocalDateTime.now(), LocalDate.of(2000, 12, 12), "fgdgdfg", "dfgdfgdf", true, new Card(), Set.of(new Role("ROLE_ADMIN"))));
//
//        UserDTO uerById = userService.getUser(1L);
//        assertEquals("macieksob25@gmail.com", uerById.getEmail());
//        assertEquals("Maciek", uerById.getName());
//        assertEquals("Sobolewski", uerById.getSurname());
//    }
//
//    @Test
//    public void editUser(){
//        UserDTO User = userService.getUser(2L);
//        User.setEmail("test@gmail.com");
//        userService.addUser(User);
//
//        assertThat(userService.getUser(2L).getEmail()).isEqualTo("test@gmail.com");
//    }
//
//    @Test
//    public void deleteUser(){
//        userService.deleteUserById(2L);
//        assertThat(userService.getUser(2L)).isNull();
//    }
//
//    @Test
//    public void getUsers(){
//        assertThat(userService.getAllUsers().size()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void testUser(){
//        UserDTO user = userService.getUser(1L);
//        assertThat(user.getEmail()).isEqualTo("macieksob25@gmail.com");
//    }
//
//}