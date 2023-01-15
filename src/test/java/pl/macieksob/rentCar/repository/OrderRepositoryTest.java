//package pl.macieksob.rentCar.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import pl.macieksob.rentCar.model.*;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class OrderRepositoryTest {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Test
//    public void saveCarTest(){
//        Order order = new Order(1L, LocalDate.of(2023,1,5), LocalDate.of(2023,2,2),Place.FIRST,Place.SECOND, new Car(4L, "E 200", "BI6734T",  new Price(1L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),300, 400, 8.4,
//                5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA,  Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,"poduszki",4),null,null, 1255);
//
//        orderRepository.save(order);
//
//        assertThat(order.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void deleteCarTest(){
//        Order order = orderRepository.findById(1L).get();
//
//        orderRepository.delete(order);
//
//        Order order2 = null;
//
//        Optional<Order> opOrder =  orderRepository.findById(1L);
//
//        if(opOrder.isPresent()){
//            order2 = opOrder.get();
//        }
//
//        assertThat(order2).isNull();
//    }
//
//    @Test
//    public void getCarTest(){
//        Order order = orderRepository.findById(1L).get();
//
//        assertThat(order.getId()).isEqualTo(1L);
//    }
//
//    @Test
//    public void getCarListTest(){
//        List<Order> all = orderRepository.findAll();
//
//        assertThat(all.size()).isGreaterThan(0);
//    }
//
//    @Test
//    public void updateCarTest(){
//        Order order = orderRepository.findById(1L).get();
//
//        order.setPrize(1234);
//
//        Order save = orderRepository.save(order);
//
//        assertThat(save.getPrize()).isEqualTo(1234);
//    }
//}