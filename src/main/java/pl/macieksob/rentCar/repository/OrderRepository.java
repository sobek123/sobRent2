package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository< Order,Long > {

    @Query(nativeQuery = true,value = "select * from ORDERS O inner join CATEGORY C on C.ID = i.CATEGORY_ID where i.TITLE iLIKE  %:keyword%  or i.CONTENT iLIKE  %:keyword% or c.name iLIKE  %:keyword% or i.date iLIKE  %:keyword%")
    List<Order> findAllByKeyword(String keyword);

//    SELECT C.ID FROM CAR C. ORDER O WHERE O.CAR_ID= C.ID AND O.START_DATE


//    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  DATE(SYSDATE()) - ?1 < O.END_DATE")
//    List<BigDecimal> gainFromDays(@Param("days") Integer days);

    @Query(nativeQuery = true, value = "SELECT SUM(O.PRIZE), BRAND FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID GROUP BY C.BRAND")
    List<Object> gainFromBrands();

    @Query(nativeQuery = true, value = "SELECT  BRAND, SUM(PRIZE) FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID GROUP BY C.BRAND")
    List<String> gainFromBrands2();

    @Query(nativeQuery = true, value = "SELECT SUM(O.PRIZE) FROM ORDERS O WHERE O.START_DATE >= DATE(year(curdate())+'-'+?1+'-01') AND O.END_DATE <= Date(year(curdate())+'-'+?1+'-31')")
    List<BigDecimal> gainFromMonth31(Integer month);

    @Query(nativeQuery = true, value = "SELECT SUM(O.PRIZE) FROM ORDERS O WHERE O.START_DATE >= DATE(year(curdate())+'-'+?1+'-01') AND O.END_DATE <= Date(year(curdate())+'-'+?1+'-30')")
    List<BigDecimal> gainFromMonth30(Integer month);

    @Query(nativeQuery = true, value = "SELECT SUM(O.PRIZE) FROM ORDERS O WHERE O.START_DATE >= DATE(year(curdate())+'-'+?1+'-01') AND O.END_DATE <= Date(year(curdate())+'-'+?1+'-28')")
    List<BigDecimal> gainFromMonthFebruary(Integer month);

    @Query(nativeQuery = true, value = "SELECT * FROM  FULL_ORDER_ORDERS FO, FULL_ORDER F, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND DATE(F.LAUNCH_DATE) - ?1 < DATE(SYSDATE())")
    List<OrderDTO> gainFromDaysOrders( Integer days);
}
