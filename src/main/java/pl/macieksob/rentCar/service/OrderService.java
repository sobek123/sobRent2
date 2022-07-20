package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.exception.OrderDuplicateException;
import pl.macieksob.rentCar.exception.OrderNotFoundException;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    private OrderDTO mapToDTO(Order order){
        OrderDTO map = modelMapper.map(order, OrderDTO.class);
        return map;
    }

    private Order mapToEntity(OrderDTO orderDTO){
        Order order = modelMapper.map(orderDTO, Order.class);
        return order;
    }

    public Order addOrder(OrderDTO order){
        if(orderRepository.existsById(order.getId())){
            throw new OrderDuplicateException("Order already exists!");
        }

        Order order1 = mapToEntity(order);
        return orderRepository.save(order1);
    }

    public Order editOrder(Long id, OrderDTO editOrder){
        Order order = orderRepository.findById(id).orElseThrow(() -> {throw new OrderNotFoundException("Order not exist!");
        });


        order.setStartDate(editOrder.getStartDate());
        order.setEndDate(editOrder.getEndDate());
        order.setCars(editOrder.getCars());

        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> {throw new OrderNotFoundException("Order not exist!");
        });

        orderRepository.delete(order);
    }

    public void deleteOrder(OrderDTO order){
        Order order1 = mapToEntity(order);

        orderRepository.delete(order1);
    }

    public List<OrderDTO> getAllByKeyword(String keyword){
        return orderRepository.findAllByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders(){
        return orderRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            throw new OrderNotFoundException("Order not exist!");
        });
        return mapToDTO(order);
    }
}
