package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.FullOrderDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Place;
import pl.macieksob.rentCar.service.FullOrderService;
import pl.macieksob.rentCar.service.OrderService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fullOrder")
public class FullOrderController {

    @Autowired
    private FullOrderService fullOrderService;

    @GetMapping("/sortByPrizeDesc")
    public List<FullOrderDTO> sortByPrizeDesc(){
        return fullOrderService.sortByPrizeDesc();
    }

    @GetMapping("/sortByPrizeAsc")
    public List<FullOrderDTO> sortByPrizeAsc(){
        return fullOrderService.sortByPrizeAsc();
    }

    @GetMapping("/sortByLaunchDateAsc")
    public List<FullOrderDTO> sortByLaunchDateAsc(){
        return fullOrderService.sortByLaunchDateAsc();
    }

    @GetMapping("/sortByLaunchDateDesc")
    public List<FullOrderDTO> sortByLaunchDateDesc(){
        return fullOrderService.sortByLaunchDateDesc();
    }

    @RequestMapping(value = "/newFullOrder",method= RequestMethod.POST)
    public void addFullOrder(@Valid @RequestBody FullOrderDTO fullOrderDTO) throws MessagingException, UnsupportedEncodingException {


        Set<OrderDTO> orders = fullOrderDTO.getOrders();
        for(OrderDTO ord: orders){
//            if(ord.getRentPlace() == Place.FIRST || ord.getBackPlace() == Place.FIRST){
//                ord.setRentPlace(Place.FIRST);
//                ord.setBackPlace(Place.FIRST);
//            }else if(ord.getRentPlace() == Place.SECOND || ord.getBackPlace() == Place.SECOND){
//                ord.setRentPlace(Place.SECOND);
//                ord.setRentPlace(Place.SECOND);
//            }else if(ord.getRentPlace() == Place.THIRD || ord.getBackPlace() == Place.THIRD){
//                ord.setRentPlace(Place.THIRD);
//                ord.setRentPlace(Place.THIRD);
//            }
//            ord.setStartDate(LocalDate.of(2022,10,10));
//            ord.setEndDate(LocalDate.of(2022,10,13));
        }
        fullOrderService.addFullOrder(fullOrderDTO);

    }
    @RequestMapping(value = "/deleteFullOrder/{id}",method=RequestMethod.DELETE)
    public void deleteFullOrderById(@PathVariable("id") Long id){
        fullOrderService.deleteFullOrderById(id);

    }

    @RequestMapping(value = "/deleteFullOrder",method=RequestMethod.DELETE)
    public void deleteFullOrder(FullOrderDTO fullOrder){
        fullOrderService.deleteFullOrder(fullOrder);
    }
    @GetMapping
    public List<FullOrderDTO> getAllFullOrders(){
        return fullOrderService.getAllFullOrders();
    }

    @GetMapping("/{id}")
    public FullOrderDTO getFullOrder(@PathVariable Long id){
        return fullOrderService.getFullOrderById(id);

    }

    @RequestMapping(value = "/daysFromMonth", method = RequestMethod.GET)
    public List<BigDecimal> daysFromMonth(@RequestParam("month") String month){
        return fullOrderService.daysFromMonth(Integer.valueOf(month));
    }

//    @RequestMapping(value = "/editFullOrder/{id}",method=RequestMethod.PUT)
//    public FullOrderDTO editFullOrder(@PathVariable Long id,@Valid @RequestBody FullOrderDTO newFullOrder){
//        return fullOrderService.editFullOrder(id,newFullOrder);
//
//    }

    @GetMapping("/getActiveOrders")
    public List<FullOrderDTO> activeOrders(){
        return fullOrderService.getActiveOrders();

    }

    @GetMapping("/getHistoricOrders")
    public List<FullOrderDTO> historicOrders(){
        return fullOrderService.getHistoricOrders();

    }

    @GetMapping("/getActiveOrdersUser")
    public List<FullOrderDTO> activeOrders(@RequestParam("id") Long id){
        return fullOrderService.getActiveOrdersUser(id);

    }

    @GetMapping("/getHistoricOrdersUser")
    public List<FullOrderDTO> historicOrders(@RequestParam("id") Long id){
        return fullOrderService.getHistoricOrdersUser(id);

    }

    @GetMapping("/rentToday")
    public List<FullOrderDTO> rentToday( ){
        return fullOrderService.getOrdersRentToday();

    }

    @GetMapping("/backToday")
    public List<FullOrderDTO> backToday(){
        return fullOrderService.getOrdersBackToday();

    }

    @GetMapping("/keyword")
    public List<FullOrderDTO> getByKeyword(@RequestParam("keyword") String keyword){
        return fullOrderService.getAllByKeyword(keyword);
    }

    @GetMapping("/sumPrize")
    public BigDecimal gainFromDays(@RequestParam("days") String days){
        return fullOrderService.sumPrize(Integer.valueOf(days));
    }

    @GetMapping("/daysFromWeek")
    public List<BigDecimal> daysFromWeek(@RequestParam("week") String week) throws ParseException {
        return fullOrderService.daysFromWeek(Integer.valueOf(week));
    }

    @GetMapping("/monthlyParallel")
    public List<BigDecimal> monthlyParallel(){
        return fullOrderService.monthlyParallel();
    }


//    @GetMapping("/gainFromDays")
////    public java.util.List<BigDecimal> sortByPrizeAscending(@RequestParam("days") String days){
////        return fullOrderService.sumPrize(days);
////    }
}
