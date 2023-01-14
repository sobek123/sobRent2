package pl.macieksob.rentCar.service;

import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.FullOrderDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.exception.FullOrderDuplicateException;
import pl.macieksob.rentCar.exception.FullOrderNotFoundException;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.model.FullOrder;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.repository.FullOrderRepository;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FullOrderService {

    @Autowired
    private FullOrderRepository fullOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardService cardService;

    @Autowired
    private CarService carService;

    @Autowired
    private MailService mailService;

    private FullOrderDTO mapToDTO(FullOrder order){
        FullOrderDTO map = modelMapper.map(order, FullOrderDTO.class);
        return map;
    }

    private FullOrder mapToEntity(FullOrderDTO orderDTO){
        FullOrder order = modelMapper.map(orderDTO, FullOrder.class);
        return order;
    }

    public String manOrWoman(UserDTO user){
        if(user.getName().endsWith("a")){
            return "Szanowna Pani ";
        }
        else {
            return "Szanowny Panie ";
        }
    }

    @Transactional
    public FullOrderDTO addFullOrder(FullOrderDTO order) throws MessagingException, UnsupportedEncodingException {

//        if(fullOrderRepository.existsById(order1.getId())){
//            throw new FullOrderDuplicateException("Order already exists!");
//        }
        order.setLaunchDate(LocalDateTime.now());
        Set<OrderDTO> orders1 = order.getOrders();
        for(OrderDTO orderDTO : orders1){
            CarDTO car = orderDTO.getCar();
            carService.editCarRentings(car.getId());
        }
        Card points = order.getUser().getCard();
        if( points != null){
            int fullPoints = 0;
            for(OrderDTO orderd : orders1){
                fullPoints += orderd.getCar().getPoints();
            }
            points.setPoints(points.getPoints() + fullPoints);
            cardService.addPoints(points);
        }

        String subject  = "Potwierdzenie rezerwacji";
        String from = "SobRent";
        StringBuilder message = new StringBuilder("<p>" + manOrWoman(order.getUser()) + order.getUser().getName() + " " + order.getUser().getSurname() + ",</p>");

        message.append("<p>Potwierdzamy rezerwację").append("</p>");
        message.append("<p><b>Szczegóły wypożyczenia </b></p>");

        for(OrderDTO orderDTO : orders1){
            CarDTO car = orderDTO.getCar();
            message.append("<p><b>").append(car.getBrand()).append(" ").append(car.getModel()).append("</b></p>");

            message.append("<p>Miejsce wypożyczenia: ").append(orderDTO.getRentPlace().showPlace()).append("<br>").append("Miejsce zwrotu: ").append(orderDTO.getBackPlace().showPlace()).append("<br>").append("Data wypożyczenia: ").append(orderDTO.getStartDate()).append("<br>").append("Data zwrotu: ").append(orderDTO.getEndDate()).append("<br>").append("Cena: ").append(orderDTO.getPrize()).append("zł").append("<br>").append("</p>");

        }



        message.append("<p>Pozdrawiamy, <br> zespół SobRent!</p>");

        FullOrder order1 = mapToEntity(order);
        mailService.sendMail(from,"macieksob25@gmail.com",order.getUser().getEmail(), message.toString(),subject,true);
        return mapToDTO(fullOrderRepository.save(order1));

//        return order;
    }
//    @Transactional
//    public FullOrderDTO editFullOrder(Long id, FullOrderDTO editFullOrder){
//        FullOrder order = fullOrderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
//        });
//
//
//        order.setStartDate(editFullOrder.getStartDate());
//        order.setEndDate(editFullOrder.getEndDate());
////        order.setCars(editOrder.getCars());
//
//        fullOrderRepository.save(order);
//
//        return mapToDTO(order);
//    }
@Transactional
    public void deleteFullOrderById(Long id) throws FullOrderNotFoundException{
//        FullOrder order = fullOrderRepository.findById(id).orElseThrow(() -> {throw new FullOrderNotFoundException("Order not exist!");
//        });
        fullOrderRepository.deleteById(id);
    }
    @Transactional
    public void deleteFullOrder(FullOrderDTO order){
        FullOrder order1 = mapToEntity(order);

        fullOrderRepository.delete(order1);
    }

    public Integer format(int month){
        if(month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }
        else if(month == 2){
            if(Year.now().getValue() % 4 == 0){
                return 29;
            }else
                return 28;
        }else
            return 31;
    }

    public List<BigDecimal> daysFromMonth(Integer month){
        List<BigDecimal> gains = new ArrayList<>();

        for (int i = 1; i <= format(month); i++) {
            BigDecimal bigDecimal = fullOrderRepository.gainFromDay(""+Year.now().getValue()+"-"+month+"-"+i);
            if(bigDecimal == null){
                gains.add(new BigDecimal(0));
            }else
                gains.add(bigDecimal);
        }
        return gains;
    }

    public List<BigDecimal> daysFromWeek(Integer week) throws ParseException {
        LocalDate now = LocalDate.now();
        LocalDate starDate = null;
        Calendar checkTime = Calendar.getInstance();
        List<BigDecimal> gains = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.WEEK_OF_YEAR);

        int dis = 1;

        LocalDate formattedDate = LocalDate.now();
        if (week > i) {
            dis = week - i;
            checkTime.setTime(new Date());
            checkTime.add(Calendar.DATE, dis);
            starDate = now.plusDays(dis * 7L);

        } else {
            dis = i - week;
            starDate = now.minusDays(dis * 7L);
        }
        List<String> dates = new ArrayList<>();
        formattedDate = getLocalDate(starDate, checkTime, calendar, formattedDate);
        for (int j = 0; j < 7; j++) {
            LocalDate localDate = formattedDate.plusDays(j);
            dates.add(localDate.toString());
            BigDecimal bigDecimal = fullOrderRepository.gainFromFullDay(localDate.toString());
            if(bigDecimal == null){
                gains.add(new BigDecimal(0));
            }else
                gains.add(bigDecimal);
        }
        return gains;
    }

    private LocalDate getLocalDate(LocalDate starDate, Calendar checkTime, Calendar calendar, LocalDate formattedDate) throws ParseException {
        checkTime.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(starDate.toString()));
        int i1 = calendar.get(Calendar.DAY_OF_WEEK);

        switch (i1) {
            case 2:
                formattedDate = starDate;
                break;
            case 3:
                formattedDate = starDate.minusDays(1);
                break;
            case 4:
                formattedDate = starDate.minusDays(2);
                break;
            case 5:
                formattedDate = starDate.minusDays(3);
                break;
            case 6:
                formattedDate = starDate.minusDays(4);
                break;
            case 7:
                formattedDate = starDate.minusDays(5);
                break;
            case 8:
                formattedDate = starDate.minusDays(6);
                break;
        }
        return formattedDate;
    }



    public BigDecimal sumPrize(Integer days){
        return fullOrderRepository.sumPrize(days);
    }

    public List<FullOrderDTO> getAllByKeyword(String keyword){
        List<Long> activeOrders = fullOrderRepository.getByKeyword(keyword).stream().distinct().collect(Collectors.toList());

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: activeOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> getAllFullOrders(){
        return fullOrderRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public FullOrderDTO getFullOrderById(Long id){
        FullOrder order = fullOrderRepository.findById(id).orElseThrow(() -> {
            throw new FullOrderNotFoundException("Order not exist!");
        });
        return mapToDTO(order);
    }

    public List<FullOrderDTO> findFullOrderByUser(UserDTO user){

        return fullOrderRepository.findByUser(user).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//    public List<FullOrderDTO> getByKeyword(String keyword) {
//        return fullOrderRepository.getByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

    public List<FullOrderDTO> sortByPrizeDesc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.desc("prize"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> sortByPrizeAsc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.asc("prize"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> sortByLaunchDateAsc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.asc("launchDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> sortByLaunchDateDesc(){
        return fullOrderRepository.findAll(Sort.by(Sort.Order.desc("launchDate"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<FullOrderDTO> getActiveOrders(){
        List<Long> activeOrders = fullOrderRepository.getActiveOrders().stream().distinct().collect(Collectors.toList());

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: activeOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> getHistoricOrders(){
        List<Long> historicOrders = fullOrderRepository.getHistoricOrders().stream().distinct().collect(Collectors.toList());

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: historicOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> getActiveOrdersUser(Long days){
        List<Long> activeOrders = fullOrderRepository.getActiveOrders().stream().distinct().collect(Collectors.toList());

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: activeOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders.stream().filter(el -> el.getUser().getId().equals(days)).collect(Collectors.toList());
    }

    public List<FullOrderDTO> getHistoricOrdersUser(Long days){
        List<Long> historicOrders = fullOrderRepository.getHistoricOrders().stream().distinct().collect(Collectors.toList());

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: historicOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders.stream().filter(el -> el.getUser().getId().equals(days)).collect(Collectors.toList());
    }



    public List<FullOrderDTO> getOrdersBackToday(){
        List<Long> historicOrders = fullOrderRepository.getOrdersBackToday();

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: historicOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<FullOrderDTO> getOrdersRentToday(){
        List<Long> historicOrders = fullOrderRepository.getOrdersRentToday();

        List<FullOrderDTO> orders = new ArrayList<>();

        for(Long l: historicOrders){
            orders.add(mapToDTO(fullOrderRepository.findById(l).orElseThrow()));
        }

        return orders;
    }

    public List<BigDecimal> monthlyParallel(){
        BigDecimal allByJanuary = fullOrderRepository.findAllByJanuary();
        BigDecimal allByFebruary = fullOrderRepository.findAllByFebruary();
        BigDecimal allByMarch = fullOrderRepository.findAllByMarch();
        BigDecimal allByApril = fullOrderRepository.findAllByApril();
        BigDecimal allByMay = fullOrderRepository.findAllByMay();
        BigDecimal allByJune = fullOrderRepository.findAllByJune();
        BigDecimal allByJuly = fullOrderRepository.findAllByJuly();
        BigDecimal allByAugust = fullOrderRepository.findAllByAugust();
        BigDecimal allBySeptember = fullOrderRepository.findAllBySeptember();
        BigDecimal allByOctober = fullOrderRepository.findAllByOctober();
        BigDecimal allByNovember = fullOrderRepository.findAllByNovember();
        BigDecimal allByDecember = fullOrderRepository.findAllByDecember();

        List<BigDecimal> monthlyParallel = new ArrayList<>();
        monthlyParallel.add(allByJanuary);
        monthlyParallel.add(allByFebruary);
        monthlyParallel.add(allByMarch);
        monthlyParallel.add(allByApril);
        monthlyParallel.add(allByMay);
        monthlyParallel.add(allByJune);
        monthlyParallel.add(allByJuly);
        monthlyParallel.add(allByAugust);
        monthlyParallel.add(allBySeptember);
        monthlyParallel.add(allByOctober);
        monthlyParallel.add(allByNovember);
        monthlyParallel.add(allByDecember);

        return monthlyParallel;
    }

    public void add(FullOrderDTO f) {
        fullOrderRepository.save(mapToEntity(f));
    }

    public void editFullOrder(FullOrderDTO order){
        FullOrder fullOrder = fullOrderRepository.findById(order.getId()).orElseThrow();
        FullOrder fullOrder1 = mapToEntity(order);
        fullOrder.setOrders(fullOrder1.getOrders());

        fullOrderRepository.save(fullOrder);

    }

    public List<FullOrder> getAll() {
        return fullOrderRepository.findAll();
    }

    public FullOrderDTO getFullOrder(long l) {
        return mapToDTO(fullOrderRepository.findById(l).orElseThrow());
    }
}
