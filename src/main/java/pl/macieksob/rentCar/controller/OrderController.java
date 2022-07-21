package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/newOrder")
    public String addOrder(OrderDTO orderDTO){
        orderService.addOrder(orderDTO);

        return "";
    }
    @DeleteMapping("/deleteOrder/{id}")
    public String deleteUserById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return "";
    }

    @DeleteMapping("/deleteOrder")
    public String deleteUser(OrderDTO order){
        orderService.deleteOrder(order);

        return "";
    }
    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id){
        orderService.getOrderById(id);

        return "";
    }

    @PutMapping("/editOrder/{id}")
    public String editOrder(@PathVariable Long id, @RequestBody OrderDTO newOrder){
        orderService.editOrder(id,newOrder);

        return "";
    }

    @GetMapping("/place")
    public List<OrderDTO> getAllOrdersByPlace(){
        return orderService.getAllOrdersByPlace();
    }

    @GetMapping("/startDate")
    public List<OrderDTO> getAllOrdersByStartDate(){
        return orderService.getAllOrdersByStartDate();
    }

    @GetMapping("/endDate")
    public List<OrderDTO> getAllOrdersByEndDate(){
        return orderService.getAllOrdersByEndDate();
    }
}
