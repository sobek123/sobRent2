package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.exception.OrderDuplicateException;
import pl.macieksob.rentCar.exception.OrderNotFoundException;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.repository.OrderRepository;

import java.time.LocalDate;
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

    public OrderDTO addOrder(OrderDTO order){
        if(orderRepository.existsById(order.getId())){
            throw new OrderDuplicateException("Order already exists!");
        }

        Order order1 = mapToEntity(order);
        orderRepository.save(order1);

        return order;
    }

    public OrderDTO editOrder(Long id, OrderDTO editOrder){
        Order order = orderRepository.findById(id).orElseThrow(() -> {throw new OrderNotFoundException("Order not exist!");
        });


        order.setStartDate(editOrder.getStartDate());
        order.setEndDate(editOrder.getEndDate());
        order.setCars(editOrder.getCars());

        orderRepository.save(order);

        return mapToDTO(order);
    }

    public void deleteOrderById(Long id) throws OrderNotFoundException{
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
    public List<OrderDTO> getAllOrdersByEndDate(LocalDate endDate){
        return orderRepository.findAllByEndDate(endDate,PageRequest.of(0,10, Sort.by(Sort.Order.asc("endDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByEndDateDesc(LocalDate endDate){
        return orderRepository.findAllByEndDate(endDate,PageRequest.of(0,10,Sort.by(Sort.Order.asc("endDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByEndDateAsc(LocalDate endDate){
        return orderRepository.findAllByEndDate(endDate,PageRequest.of(0,10,Sort.by(Sort.Order.asc("endDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public List<OrderDTO> getAllOrdersByStartDate(LocalDate startDate){
        return orderRepository.findAllByStartDate(startDate,PageRequest.of(0,10, Sort.by(Sort.Order.asc("startDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByStartDateDesc(LocalDate startDate){
        return orderRepository.findAllByStartDate(startDate,PageRequest.of(0,10,Sort.by(Sort.Order.asc("startDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByStartDateAsc(LocalDate startDate){
        return orderRepository.findAllByStartDate(startDate,PageRequest.of(0,10,Sort.by(Sort.Order.asc("startDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByPlace(Place place){
        return orderRepository.findAllByPlace(place,PageRequest.of(0,10, Sort.by(Sort.Order.asc("endDate")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//    @GetMapping("/")
//    public List<OrderDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
//        return carService.getByKeyword();
//    }
}
