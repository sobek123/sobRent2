package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository< Order,Long > {

    @Query(nativeQuery = true,value = "select * from ORDERS O inner join CATEGORY C on C.ID = i.CATEGORY_ID where i.TITLE iLIKE  %:keyword%  or i.CONTENT iLIKE  %:keyword% or c.name iLIKE  %:keyword% or i.date iLIKE  %:keyword%")
    List<Order> findAllByKeyword(String keyword);
}
