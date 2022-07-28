package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.service.OrderService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/newOrder")
    public OrderDTO addOrder(@Valid @RequestBody OrderDTO orderDTO){
        return orderService.addOrder(orderDTO);

    }
    @DeleteMapping("/deleteOrder/{id}")
    public void deleteUserById(@PathVariable Long id){
        orderService.deleteOrderById(id);

    }

    @DeleteMapping("/deleteOrder")
    public void deleteUser(OrderDTO order){
        orderService.deleteOrder(order);
    }
    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id){
        return orderService.getOrderById(id);

    }

    @PutMapping("/editOrder/{id}")
    public OrderDTO editOrder(@PathVariable Long id,@Valid @RequestBody OrderDTO newOrder){
        return orderService.editOrder(id,newOrder);

    }

    @GetMapping("/place")
    public List<OrderDTO> getAllOrdersByPlace(@RequestParam(value = "place")Place place){
        return orderService.getAllOrdersByPlace(place);
    }

    @GetMapping("/startDate")
    public List<OrderDTO> getAllOrdersByStartDate(@RequestParam(value = "startDate")LocalDate startDate){
        return orderService.getAllOrdersByStartDate(startDate);
    }

    @GetMapping("/endDate")
    public List<OrderDTO> getAllOrdersByEndDate(@RequestParam(value = "endDate")LocalDate endDate){
        return orderService.getAllOrdersByEndDate(endDate);
    }
}
