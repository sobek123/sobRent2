//package pl.macieksob.rentCar.service;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.CarDTO;
//import pl.macieksob.rentCar.dto.FullOrderDTO;
//import pl.macieksob.rentCar.dto.OrderDTO;
//import pl.macieksob.rentCar.dto.UserDTO;
//import pl.macieksob.rentCar.model.*;
//import pl.macieksob.rentCar.repository.CardRepository;
//import pl.macieksob.rentCar.repository.FullOrderRepository;
//
//import javax.mail.MessagingException;
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class FullOrderServiceTest {
//
//    @Mock
//    private FullOrderRepository fullOrderRepository;
//
//    @InjectMocks
//    private FullOrderService fullOrderService;
//
//    @Autowired
//    private CarService carService;
//
//    @BeforeAll
//    public void setup() throws MessagingException, UnsupportedEncodingException {
//        fullOrderService.addFullOrder(new FullOrderDTO(2L, Set.of(new OrderDTO(3L, LocalDate.of(2022,10,11), LocalDate.of(2022,10,21), Place.FIRST, Place.SECOND,34554,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4)))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//                LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, new Card(),Set.of(new Role("ROLE_ADMIN"))),new BigDecimal(34554)));
//        fullOrderService.addFullOrder(new FullOrderDTO(1L,Set.of(new OrderDTO(1L, LocalDate.of(2022,12,7), LocalDate.of(2022,12,12),Place.FIRST, Place.SECOND,1044,carService.addCar(new CarDTO(40L, "Crafter", "BI6734T", 140, 205, 8.8, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.0, "./cars/VW_Crafter_IMG_0772.jpg", "Volkswagen", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.BENZYNA, 2022, 200, 4))),new OrderDTO(2L, LocalDate.of(2022,6,12), LocalDate.of(2022,5,28),Place.FIRST, Place.SECOND,1000,carService.addCar(carService.addCar(new CarDTO(36L, "RX450h", "BI6734T", 200, 305, 6.1, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.0, "./cars/LexusRX450hFSport(IV)Frontansicht,14.Februar_2016,Düsseldorf.jpg", "Lexus", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.HYBRID, 2018, 300, 4))))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//                LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true,new Card(),Set.of(new Role("ROLE_ADMIN"))),new BigDecimal(2044)));
//
//    }
//
//    @Test
//    public void addFullOrder() throws MessagingException, UnsupportedEncodingException {
//        FullOrderDTO fullOrderDTO = fullOrderService.addFullOrder(new FullOrderDTO(3L, Set.of(new OrderDTO(3L, LocalDate.of(2022,10,11), LocalDate.of(2022,10,21), Place.FIRST, Place.SECOND,34554,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4)))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//                LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, new Card(),Set.of(new Role("ROLE_ADMIN"))),new BigDecimal(34554)));
//
//        assertNotNull(fullOrderService.getFullOrder(3L));
//    }
//
//    @Test
//    public void editFullOrder() throws MessagingException, UnsupportedEncodingException {
//        FullOrderDTO fullOrder = fullOrderService.getFullOrder(1L);
//        fullOrder.setPrize(BigDecimal.valueOf(345.55));
//        fullOrderService.addFullOrder(fullOrder);
//
//        assertThat(fullOrderService.getFullOrder(1L).getPrize()).isEqualTo(BigDecimal.valueOf(345.55));
//    }
//
//    @Test
//    public void deleteFullOrder(){
//        fullOrderService.deleteFullOrderById(1L);
//        assertThat(fullOrderService.getFullOrder(1L)).isNull();
//    }
//
//    @Test
//    public void getFullOrders(){
//        assertThat(fullOrderService.getAll().size()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void getFullOrder(){
//        when(fullOrderService.getFullOrder(2L)).thenReturn(new FullOrderDTO(2L, Set.of(new OrderDTO(3L, LocalDate.of(2022,10,11), LocalDate.of(2022,10,21), Place.FIRST, Place.SECOND,34554,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Prize(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//                2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4)))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//                LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, new Card(),Set.of(new Role("ROLE_ADMIN"))),new BigDecimal(34554)));
//
//        FullOrderDTO fullOrderDTO = fullOrderService.getFullOrder(2L);
//        assertEquals(BigDecimal.valueOf(34554),fullOrderDTO.getPrize());
//    }
//}
