package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.dto.FullOrderDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.FullOrder;
import pl.macieksob.rentCar.model.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FullOrderRepository extends JpaRepository<FullOrder, Long> {
    List<FullOrder> findByUser(UserDTO user);

    @Query(nativeQuery = true, value = "select * from  FULL_ORDER_ORDERS FO, FULL_ORDER F, USERS U, CARS C  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND U.ID = :id AND DATE(SYSDATE()) EQUALS O.END_DATE")
    List<Long> getTodayOrders(@Param("id") Long id);


    @Query(nativeQuery = true,value = "select F.ID from  FULL_ORDER_ORDERS FO, FULL_ORDER F, USERS U, CARS C, ORDERS O " +
            "where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID  AND F.PRIZE LIKE  %?1%" +
            " or f.launch_Date LIKE  %?1%  or u.name LIKE  %?1%  or u.surname LIKE  %?1% or u.email LIKE  %?1% or u.date_Of_Birth LIKE  %?1%" +
            " or u.pesel LIKE  %?1% or u.city LIKE  %?1% or u.street LIKE  %?1% or u.post_Code LIKE  %?1% or u.number_Of_Street LIKE  %?1% or u.number_Of_Flat LIKE  %?1%" +
            " or c.model LIKE  %?1%  or c.brand LIKE  %?1% or c.km LIKE  %?1% or c.nm LIKE  %?1% or c.license_Plate LIKE  %?1%" +
            " or c.petrol LIKE  %?1% or c.engine LIKE  %?1% or c.transmission LIKE  %?1% or c.category LIKE  %?1% ")
    List<Long> getByKeyword(String keyword);
//
//    @Query(nativeQuery = true, value = "select * from  FULL_ORDER_ORDERS FO, FULL_ORDER F, USERS U, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID  AND U.ID = F.USER_ID AND U.ID = ?1 AND DATE(SYSDATE()) <= O.END_DATE")
//    List<Long> getActiveOrdersUser(Long id);
//
//    @Query(nativeQuery = true, value = "select * from  FULL_ORDER_ORDERS FO, FULL_ORDER F, USERS U, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID  AND U.ID = F.USER_ID AND U.ID = ?1 AND o.start_Date < DATE(SYSDATE()) AND o.end_Date > DATE(SYSDATE()) OR\n" +
//            "  o.start_Date > DATE(SYSDATE()) AND o.end_Date >DATE(SYSDATE()) OR o.end_Date < DATE(SYSDATE()) AND o.start_Date < DATE(SYSDATE())")
//    List<Long> getHistoricOrdersUser(Long id);

    @Query(nativeQuery = true, value = "select F.ID from  FULL_ORDER_ORDERS FO, FULL_ORDER F, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND (DATE(SYSDATE()) = O.END_DATE OR DATE(SYSDATE()) = O.START_DATE OR (DATE(SYSDATE()) > O.START_DATE AND DATE(SYSDATE()) < O.END_DATE) OR (DATE(SYSDATE()) < O.START_DATE AND DATE(SYSDATE()) < O.END_DATE))")
    List<Long> getActiveOrders();

//    @Query(nativeQuery = true, value = "select F.ID from  FULL_ORDER_ORDERS FO, FULL_ORDER F, USERS U, CARS C, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID AND U.ID = F.USER_ID AND o.start_Date < DATE(SYSDATE()) AND o.end_Date > DATE(SYSDATE()) OR\n" +
//            "  o.start_Date > DATE(SYSDATE()) AND o.end_Date > DATE(SYSDATE()) OR o.end_Date < DATE(SYSDATE()) AND o.start_Date < DATE(SYSDATE())")
//    List<Long> getHistoricOrders();

    @Query(nativeQuery = true, value = "select F.ID from  FULL_ORDER_ORDERS FO, FULL_ORDER F, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID  AND (O.END_DATE < DATE(SYSDATE()) AND O.START_DATE < DATE(SYSDATE()))")
    List<Long> getHistoricOrders();
    @Query(nativeQuery = true, value = "select F.ID from  FULL_ORDER_ORDERS FO, FULL_ORDER F, CARS C, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID  AND DATE(SYSDATE()) = O.START_DATE;")
    List<Long> getOrdersRentToday();

    @Query(nativeQuery = true, value = "select F.ID from  FULL_ORDER_ORDERS FO, FULL_ORDER F, CARS C, ORDERS O  where FO.ORDERS_ID = O.ID AND FO.FULL_ORDER_ID = F.ID AND O.CAR_ID = C.ID  AND DATE(SYSDATE()) = O.END_DATE;")
    List<Long> getOrdersBackToday();

    @Query(nativeQuery = true, value = "select sum(F.PRIZE) from FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) - :days < DATE(SYSDATE())")
    BigDecimal sumPrize(@Param("days") int days);

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) = ?1")
    BigDecimal gainFromDay(String day);

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-01-01' AND DATE(F.LAUNCH_DATE) <= '2023-01-31'")
    BigDecimal findAllByJanuary();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-02-01' AND DATE(F.LAUNCH_DATE) <= '2023-02-28'")
    BigDecimal findAllByFebruary();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-03-01' AND DATE(F.LAUNCH_DATE) <= '2023-03-31'")
    BigDecimal findAllByMarch();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-04-01' AND DATE(F.LAUNCH_DATE) <= '2023-04-30'")
    BigDecimal findAllByApril();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-05-01' AND DATE(F.LAUNCH_DATE) <= '2023-05-31'")
    BigDecimal findAllByMay();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-06-01' AND DATE(F.LAUNCH_DATE) <= '2023-06-30'")
    BigDecimal findAllByJune();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-07-01' AND DATE(F.LAUNCH_DATE) <= '2023-07-31'")
    BigDecimal findAllByJuly();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-08-01' AND DATE(F.LAUNCH_DATE) <= '2023-08-31'")
    BigDecimal findAllByAugust();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-09-01' AND DATE(F.LAUNCH_DATE) <= '2023-09-30'")
    BigDecimal findAllBySeptember();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-10-01' AND DATE(F.LAUNCH_DATE) <= '2023-10-31'")
    BigDecimal findAllByOctober();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-11-01' AND DATE(F.LAUNCH_DATE) <= '2023-11-30'")
    BigDecimal findAllByNovember();

    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) >= '2023-12-01' AND DATE(F.LAUNCH_DATE) <= '2023-12-31'")
    BigDecimal findAllByDecember();


    @Query(nativeQuery = true, value = "SELECT SUM(F.PRIZE) FROM FULL_ORDER F WHERE DATE(F.LAUNCH_DATE) = ?1")
    BigDecimal gainFromFullDay(String day);
}

