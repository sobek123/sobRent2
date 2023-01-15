//package pl.macieksob.rentCar.service;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.CarDTO;
//import pl.macieksob.rentCar.dto.RoleDTO;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.RoleRepository;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class RoleServiceTest {
//    @Mock
//    private RoleRepository roleRepository;
//
//    @InjectMocks
//    private RoleService roleService;
//    @BeforeEach
//    public void setup(){
//        roleService.addRole(new RoleDTO(1L, "ROLE_ADMIN"));
//        roleService.addRole(new RoleDTO(2L, "ROLE_WORKER"));
//        roleService.addRole(new RoleDTO(3L, "ROLE_USER"));
//        roleService.addRole(new RoleDTO(4L, "ROLE_ADMIN"));
//    }
//    @Test
//    public void addRole(){
//        RoleDTO RoleDTO = roleService.addRole(new RoleDTO(5L, "ROLE_ADMIN"));
//
//        assertNotNull(roleService.getRole(5L));
//    }
//
//    @Test
//    public void editRole(){
//        RoleDTO role = roleService.getRole(5L);
//        role.setName("ROLE_ADMIN");
//        roleService.addRole(role);
//
//        assertThat(roleService.getRole(5L).getName()).isEqualTo("ROLE_ADMIN");
//    }
//
//    @Test
//    public void deleteRole(){
//        roleService.deleteRoleById(5L);
//        assertThat(roleService.getRole(5L)).isNull();
//    }
//
//    @Test
//    public void getRole(){
//        when(roleService.getRole(1L))
//                .thenReturn(new RoleDTO(1L, "ROLE_ADMIN"));
//
//        RoleDTO roleById = roleService.getRole(1L);
//        assertThat(roleById.getName()).isEqualTo("ROLE_ADMIN");
//    }
//
//    @Test
//    public void getRoles(){
//        assertThat(roleService.getRoles().size()).isGreaterThan(0);
//    }
//}