package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.OrderDTO;
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

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id){
        orderService.getOrderById(id);

        return "";
    }

    @PutMapping("/{id}")
    public String editOrder(@PathVariable Long id, @RequestBody OrderDTO newOrder){
        orderService.editOrder(id,newOrder);

        return "";
    }


}
