package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.FullOrderDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.exception.FullOrderDuplicateException;
import pl.macieksob.rentCar.exception.FullOrderNotFoundException;
import pl.macieksob.rentCar.model.FullOrder;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.repository.FullOrderRepository;
import pl.macieksob.rentCar.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private FullOrderService fullOrderService;

//    @Autowired
//    private FullOrderRepository fullOrderRepository;

    private OrderDTO mapToDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }

//    private FullOrder mapToEntityF(FullOrderDTO orderDTO){
//        FullOrder order = modelMapper.map(orderDTO, FullOrder.class);
//        return order;
//    }

    private Order mapToEntity(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }
    @Transactional
    public OrderDTO addOrder(OrderDTO order){
        if(orderRepository.existsById(order.getId())){
            throw new FullOrderDuplicateException("Order already exists!");
        }

        Order order1 = mapToEntity(order);
//        order1.getCar().setTaken(true);
//        order1.getCar().setRentings(order1.getCar().getRentings());
//        order1.setLaunchDate(LocalDateTime.now());
        orderRepository.save(order1);

        return order;
    }
    @Transactional
    public OrderDTO editOrder(Long id, OrderDTO editOrder){
        Order order = orderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
        });


        order.setStartDate(editOrder.getStartDate());
        order.setEndDate(editOrder.getEndDate());
//        order.setCars(editOrder.getCars());

        orderRepository.save(order);

        return mapToDTO(order);
    }

    public void deleteOrderById(Long id) throws FullOrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
        });

//        order = null;
//        orderRepository.save(order);
        List<FullOrderDTO> allFullOrders = fullOrderService.getAllFullOrders();
        OrderDTO first = mapToDTO(order);
        for(FullOrderDTO f : allFullOrders){
            for (OrderDTO or : f.getOrders()){
                if(or.getId().equals(id)){
                    Set<OrderDTO> orders = f.getOrders();
                    orders.remove(or);
                    f.setOrders(orders);
                    if(f.getOrders().size() == 0){
                        fullOrderService.deleteFullOrder(f);
                    }else{
//                first = null;q
                    fullOrderService.editFullOrder(f);}
                    orderRepository.delete(mapToEntity(or));


                }
            }
//            boolean contains = f.getOrders().contains(mapToDTO(order));
//            System.out.println(contains);
//            if(contains){
//                System.out.println("halo");
//                Set<OrderDTO> orders = f.getOrders();
//                System.out.println(orders);
//                orders.remove(mapToDTO(order));
//                f.setOrders(orders);
//                System.out.println(orders);
////                first = null;q
//               fullOrderService.editFullOrder(f);
////                fullOrderService.add(f);
//                break;
//            }
        }
    }
    @Transactional
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
            throw new FullOrderNotFoundException("Order not exist!");
        });
        return mapToDTO(order);
    }

    public List< Object> gainFromBrands(){
        return orderRepository.gainFromBrands();
    }

    public List<OrderDTO> gainFromDaysOrders(Integer days ){
        return orderRepository.gainFromDaysOrders(days);
    }

}
