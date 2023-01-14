package pl.macieksob.rentCar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.RoleDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.repository.OrderRepository;
import pl.macieksob.rentCar.repository.RoleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Autowired
    private CarService carService;

    @BeforeEach
    public void setup(){
        orderService.addOrder(new OrderDTO(1L, LocalDate.of(2022,12,7), LocalDate.of(2022,12,12),Place.FIRST, Place.SECOND,1044,carService.addCar(new CarDTO(40L, "Crafter", "BI6734T", 140, 205, 8.8, new Price(40L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                2.0, "./cars/VW_Crafter_IMG_0772.jpg", "Volkswagen", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.BENZYNA, 2022, 200, 4))));
        orderService.addOrder(new OrderDTO(2L, LocalDate.of(2022,10,11), LocalDate.of(2022,10,21),Place.FIRST, Place.SECOND,34554,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Price(38L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4))));

    }
    @Test
    public void addOrder(){
        OrderDTO orderDTO = orderService.addOrder(new OrderDTO(2L, LocalDate.of(2022,10,11), LocalDate.of(2022,10,21),Place.FIRST, Place.SECOND,34554,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Price(2L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4))));

        assertNotNull(orderService.getOrderById(7L));
    }

    @Test
    public void deleteOrder(){
        orderService.deleteOrderById(1L);
        assertThat(orderService.getOrderById(1L)).isNull();
    }

    @Test
    public void editOrder(){
        OrderDTO order = orderService.getOrderById(1L);
        order.setPrize(1054);
        orderService.addOrder(order);

        assertThat(orderService.getOrderById(1L).getPrize()).isEqualTo(1054);
    }

    @Test
    public void getOrders(){
        assertThat(orderService.getAllOrders().size()).isGreaterThan(0);

    }

    @Test
    public void getOrder(){
        when(orderService.getOrderById(2L)).thenReturn(new OrderDTO(2L, LocalDate.of(2022,10,11), LocalDate.of(2022,10,21),Place.FIRST, Place.SECOND,34554,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Price(38L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
                2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4))));

        OrderDTO order = orderService.getOrderById(1L);
        assertThat(order.getPrize()).isEqualTo(34554);

    }
}