package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/newOrder")
    public String addOrder(@RequestBody OrderDTO orderDTO){
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

    @GetMapping("/")
    public List<OrderDTO> getAllOrdersByPlace(@RequestParam(value = "place")Place place){
        return orderService.getAllOrdersByPlace(place);
    }

    @GetMapping("/")
    public List<OrderDTO> getAllOrdersByStartDate(@RequestParam(value = "startDate")LocalDate startDate){
        return orderService.getAllOrdersByStartDate(startDate);
    }

    @GetMapping("/")
    public List<OrderDTO> getAllOrdersByEndDate(@RequestParam(value = "endDate")LocalDate endDate){
        return orderService.getAllOrdersByEndDate(endDate);
    }
}
