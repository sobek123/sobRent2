//package pl.macieksob.rentCar.service;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.AdditionalDTO;
//import pl.macieksob.rentCar.dto.CarDTO;
//import pl.macieksob.rentCar.dto.UserDTO;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.AdditionalRepository;
//import pl.macieksob.rentCar.repository.RoleRepository;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class AdditionalServiceTest {
//
//    @Mock
//    private AdditionalRepository additionalRepository;
//
//    @InjectMocks
//    private static AdditionalService additionalService;
//
//    @BeforeAll
//   public static void setup(){
//        additionalService.addAdditional(new AdditionalDTO(1L, "Tankowanie", BigDecimal.valueOf(566)));
//        additionalService.addAdditional(new AdditionalDTO(2L,"Wyjazd poza granice kraju",BigDecimal.valueOf(566)));
//        additionalService.addAdditional(new AdditionalDTO(3L,"Wypozyczenie fotelika dziecięcego",BigDecimal.valueOf(566)));
//        additionalService.addAdditional(new AdditionalDTO(4L,"Palenie w samochodzie",BigDecimal.valueOf(566)));
//
//    }
//
//    @Test
//    public void addAdditional(){
//        AdditionalDTO userDTO = additionalService.addAdditional(new AdditionalDTO(7L, "Maciej",BigDecimal.valueOf(566)));
//
//        assertNotNull(additionalService.getAdditional(7L));
//    }
//
//    @Test
//    public void editAdditional(){
//        AdditionalDTO additional = additionalService.getAdditional(7L);
//        additional.setPrize(BigDecimal.valueOf(345.55));
//        additionalService.addAdditional(additional);
//
//        assertThat(additionalService.getAdditional(7L).getPrize()).isEqualTo(BigDecimal.valueOf(345.55));
//    }
//
//    @Test
//    public void deleteAdditional(){
//        additionalService.deleteAdditionalById(7L);
//        assertThat(additionalService.getAdditional(7L)).isNull();
//    }
//
//    @Test
//    public void getAdditionals(){
//        assertThat(additionalService.getAll().size()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void getAdditional(){
//        when(additionalService.getAdditional(1L)).thenReturn(new AdditionalDTO(1L,"Tankowanie",BigDecimal.valueOf(566)));
//
//        AdditionalDTO additionalDTO = additionalService.getAdditional(1L);
//        assertEquals("Wypożyczenie fotelika dziecięcego",additionalDTO.getName());
//        assertEquals(BigDecimal.valueOf(367.6),additionalDTO.getPrize());
//    }
//}
