package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.exception.CarNotFoundException;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.repository.CarRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ModelMapper modelMapper;


    private CarDTO mapToDTO(Car car){
        return modelMapper.map(car, CarDTO.class);
    }

    private Car mapToEntity(CarDTO carDTO){
        return modelMapper.map(carDTO, Car.class);
    }

    @Transactional
    public CarDTO addCar(CarDTO car){
//        if(carRepository.existsById(car.getId())){
//            throw new CarDuplicateException("Car already exists!");
//        }
        car.setTaken(false);
        car.setRentings(0);
        car.setFault("");
//        priceRepository.save(car.getPrize());
//        if(!car.getImage().startsWith("./cars/")){
//            car.setImage("./cars"+car.getImage());
//        }
        Car car1 = mapToEntity(car);

        carRepository.save(car1);

        return car;
    }

//    @Transactional
//    public CarDTO addCarForm(MultipartFile file,CarDTO car){
////        if(carRepository.existsById(car.getId())){
////            throw new CarDuplicateException("Car already exists!");
////        }
//        car.setTaken(false);
//        car.setRentings(0);
//        car.setFault("");
////        priceRepository.save(car.getPrize());
//        if(!car.getImage().startsWith("./cars/")){
//            car.setImage("./cars"+car.getImage());
//        }
//        Car car1 = mapToEntity(car);
//
//        carRepository.save(car1);
//
//        return car;
//    }

    @Transactional
    public void addCarFullOrder(CarDTO car){
        carRepository.save(mapToEntity(car));
    }

    @Transactional
    public void deleteCar(Long id) throws CarNotFoundException{
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        carRepository.delete(car);
    }

    public CarDTO getCarById(Long id) throws CarNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not exist!");
        });
        return mapToDTO(car);
    }
    @Transactional
    public CarDTO editCar(Long id, CarDTO editCar) throws CarNotFoundException{
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        car.setPoints(editCar.getPoints());
        car.setPrice(editCar.getPrice());
        if(!Objects.equals(car.getFault(), "")){
            car.setFault(car.getFault() + ", "+ editCar.getFault());
        }
        car.setTaken(editCar.getTaken());
        carRepository.save(car);

        return mapToDTO(car);
    }

    @Transactional
    public CarDTO editCarRentings(Long id) throws CarNotFoundException{
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        car.setRentings(car.getRentings() + 1);
        car.setTaken(true);
        carRepository.save(car);

        return mapToDTO(car);
    }
    @Transactional
    public void deleteCar(CarDTO car){
        Car car1 = mapToEntity(car);
        carRepository.delete(car1);
    }
    @Transactional
    public void deleteCarById(Long id){
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });
        carRepository.deleteById(id);
    }

    public java.util.List<CarDTO> findAllByModel(String model){
        return carRepository.findAllByModel(model).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public java.util.List<CarDTO> getAllCars(){
        return carRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
//        List<OrderDTO> collectO = orderService.getAllO                                rders();
//        List<CarDTO> res = new ArrayList<>();
//
//        for(Car c: collect){
//            System.out.println(collectO);
//            System.out.println(c);
//
//            List<OrderDTO> collect1 = collectO.stream().filter(el -> el.getCar().getId().equals(c.getId())).collect(Collectors.toList());
//            CarDTO carDTO = mapToDTO(c);
//
//
//
//            carDTO.setOrders(collect1);
//            res.add(carDTO);
//
//        }

//        return res;
    }
    public java.util.List<CarDTO> findAllByBrand(String brand){
        return carRepository.findAllByBrand(brand).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> findAllByKm(Integer km){
        return carRepository.findAllByKm(km).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public java.util.List<CarDTO> findAllByTransmission(Transmission transmission){
        return carRepository.findAllByTransmission(transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<String> getModelsByBrands(String brand, Category category){
        if(category == null){
            List<CarDTO> collect = carRepository.findAllByBrand(brand).stream().map(this::mapToDTO).collect(Collectors.toList());
            return collect.stream().map(CarDTO::getModel).distinct().collect(Collectors.toList());
        }else {
            List<CarDTO> collect = carRepository.findAllByBrandAndCategory(brand, category).stream().map(this::mapToDTO).collect(Collectors.toList());

            return collect.stream().map(CarDTO::getModel).distinct().collect(Collectors.toList());
        }
    }

    public List<Integer> getYearByBrands(String brand){
        List<CarDTO> collect = carRepository.findAllByBrand(brand).stream().map(this::mapToDTO).collect(Collectors.toList());

        return collect.stream().map(CarDTO::getYear).distinct().collect(Collectors.toList());
    }

    public List<Integer> getNumberOfSeatsByBrands(String brand){
        List<CarDTO> collect = carRepository.findAllByBrand(brand).stream().map(this::mapToDTO).collect(Collectors.toList());
        return collect.stream().map(CarDTO::getNumberOfSeats).distinct().collect(Collectors.toList());
    }

    public java.util.List<CarDTO> findAllByPetrol(Petrol petrol){
        return carRepository.findAllByPetrol(petrol).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> findAllByEngine(Double engine){
        return carRepository.findAllByEngine(engine).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> findAllByYear(Integer year){
        return carRepository.findAllByYear(year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//    public java.util.List<CarDTO> findAllByPrize(BigDecimal prize){
//        return carRepository.findAllByPrize(prize).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

    //SORTING
    public java.util.List<CarDTO> sortByPrizeAscending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.asc("prize"))).stream().filter(elem -> elem.getCategory().equals(category)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByPrizeDescending(Category category){
        return carRepository.findAll(Sort.by(Sort.Order.desc("prize"))).stream().filter(elem -> elem.getCategory().equals(category)).map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNumberOfSeatsAscending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("numberOfSeats"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNumberOfSeatsDescending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("numberOfSeats"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByYearAscending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("year"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByYearDescending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("year"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByKmAscending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("km"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByKmDescending(){
        return carRepository.findAll(Sort.by(Sort.Order.desc("km"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNmAscending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("nm"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNmDescending(){
        return carRepository.findAll(Sort.by(Sort.Order.desc("nm"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByCombustionAscending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("combustion"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByCombustionDescending(){
        return carRepository.findAll(Sort.by(Sort.Order.desc("combustion"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByEngineAscending(){
        return carRepository.findAll(Sort.by(Sort.Order.asc("engine"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByEngineDescending(){
        return carRepository.findAll(Sort.by(Sort.Order.desc("engine"))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getCarsByStartDateAndEndDate(String startDate, String endDate){
//        List<Car> mostRentedCar = carRepository.findAll();

        List<CarDTO> mostRentedCar = carRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());

        List<CarDTO> cars = new ArrayList<>();

        List<OrderDTO> allOrders = orderService.getAllOrders();

        for(OrderDTO order: allOrders){
            if((order.getStartDate().compareTo(LocalDate.parse(startDate)) > 0 && order.getStartDate().compareTo(LocalDate.parse(endDate)) > 0) || (order.getEndDate().compareTo(LocalDate.parse(endDate)) < 0 && order.getEndDate().compareTo(LocalDate.parse(startDate)) < 0)){
                cars.add(order.getCar());
            }
        }

//        for(int i = 0;i< mostRentedCar.size(); i++){
//            if(!cars.contains(mostRentedCar.get(i))){
//                cars.add(mostRentedCar.get(i));
//            }
//        }

//        for(Car c: mostRentedCar){
//            Order order = c.getOrder();
//            if(order == null || (order.getStartDate().compareTo(LocalDate.parse(startDate)) > 0 && order.getStartDate().compareTo(LocalDate.parse(endDate)) > 0) || (order.getEndDate().compareTo(LocalDate.parse(endDate)) < 0 && order.getEndDate().compareTo(LocalDate.parse(startDate)) < 0)){
//                cars.add(mapToDTO(c));}
////           } }else {
////                if ((order.getStartDate().compareTo(LocalDate.parse(startDate)) > 0 && order.getStartDate().compareTo(LocalDate.parse(endDate)) > 0) || (order.getEndDate().compareTo(LocalDate.parse(endDate)) < 0 && order.getEndDate().compareTo(LocalDate.parse(startDate)) < 0)) {
////                    cars.add(mapToDTO(c));
////                }
////            }
//        }

//        List<CarDTO> carDTOStream = cars.stream().filter(elem -> elem.getTaken().equals(false)).collect(Collectors.toList());

        return cars;
    }


    public java.util.List<CarDTO> findAllByKeyword(String keyword){
        return carRepository.findAllByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //FILTERING
    public List<CarDTO> getSportCar(){
        return carRepository.findAllByCategory(Category.SPORTOWE).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getExclusiveCar(){
        return carRepository.findAllByCategory(Category.EKSKLUZYWNE).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getComfortCar(){
        return carRepository.findAllByCategory(Category.KOMFORTOWE).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getCargoCar(){
        return carRepository.findAllByCategory(Category.DOSTAWCZE).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getSUVCar(){
        return carRepository.findAllByCategory(Category.SUV).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getEconomyCar(){
        return carRepository.findAllByCategory(Category.EKONOMICZNE).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//

//    public void rentCar(Long id, OrderDTO order){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String credentials = (String) auth.getCredentials();
//        UserDTO byPassword = userService.findByPassword(credentials);
//        order.setUser(byPassword);
//        order.setLaunchDate(LocalDateTime.now());
//        orderService.addOrder(order);
//        CarDTO carById = getCarById(id);
//        carById.setTaken(true);
//        carById.setRentings(carById.getRentings() + 1);
//        Car car = mapToEntity(carById);
//        byPassword.setPoints(byPassword.getPoints() + car.getPoints());
//
//        String subject  = "Potwierdzenie rezerwacji";
//        String from = "RentCar";
//        String message = "<p>" + manOrWoman(user) + user.getName() + " " + user.getSurname() + ",</p>";
//        message += "<p>Potwierdzamy rezerwację samochodu </p>" + car.getBrand() + car.getModel();
//        message += "<p>Numer rezerwacji:  </p>" + order.getId();
//
//
//
//        message += "<p>Pozdrawiamy, <br> zespół RentCar!</p>";
//
//
//        mailService.sendMail(from,"macieksob25@gmail.com",user.getEmail(), message,subject,true);
//        carRepository.save(car);
//    }

//    public List<CarDTO> getCarsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate){
//        List<Long> mostRentedCar = carRepository.findCarsByStartDateAndEndDate(startDate, endDate);
//
//        List<CarDTO> cars = new ArrayList<>();
//
//        for(Long l: mostRentedCar){
//            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
//        }
//
//        return cars;
//    }


    public void addFault(String fault, Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> {throw new CarNotFoundException("Car not found");});
        car.setFault(fault);
        carRepository.save(car);
    }

    public List<CarDTO> mostRentedCarInDaysBrand(String days){
        List<Long> mostRentedCar = carRepository.findMostRentedCarBrand(Integer.valueOf(days));

        List<CarDTO> cars = new ArrayList<>();

        for(Long l: mostRentedCar){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
        }

        return cars;
    }

    public List<CarDTO> findAllByLastDaysBrand(String days){
        List<Long> carsByLastDays = carRepository.findCarsByLastDaysBrand(Integer.valueOf(days));
        List<CarDTO> cars = new ArrayList<>();

        for(Long l: carsByLastDays){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
        }

        return cars;
    }

    public List<CarDTO> mostRentedCarInDays(String days){
        List<Long> mostRentedCar = carRepository.findMostRentedCar(Integer.valueOf(days));

        List<CarDTO> cars = new ArrayList<>();

        for(Long l: mostRentedCar){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
        }

        return cars;
    }

    public List<CarDTO> findAllByLastDays(String days){
        List<Long> carsByLastDays = carRepository.findCarsByLastDays(Integer.valueOf(days));
        List<CarDTO> cars = new ArrayList<>();

        for(Long l: carsByLastDays){
            cars.add(mapToDTO(carRepository.findById(l).orElseThrow()));
        }

        return cars;
    }


    public List<CarDTO> findAllByBrandAndPetrolAndTaken(@RequestParam String brand,@RequestParam Petrol petrol){
        return carRepository.findAllByBrandAndPetrolAndTaken(brand, petrol,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndNumberOfSeatsAndTaken(@RequestParam String brand,@RequestParam Integer numberOfSeats){
        return carRepository.findAllByBrandAndNumberOfSeatsAndTaken(brand, numberOfSeats,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndTransmissionAndTaken(@RequestParam String brand,@RequestParam Transmission transmission){
        return carRepository.findAllByBrandAndTransmissionAndTaken(brand, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndCategoryAndTaken(@RequestParam String brand,@RequestParam Category category){
        return carRepository.findAllByBrandAndCategoryAndTaken(brand, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYearAndTaken(@RequestParam String brand,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYearAndTaken(brand, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndNumberOfSeatsAndTaken(@RequestParam Petrol petrol,@RequestParam Integer numberOfSeats){
        return carRepository.findAllByPetrolAndNumberOfSeatsAndTaken(petrol, numberOfSeats,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndTransmissionAndTaken(@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByPetrolAndTransmissionAndTaken(petrol, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndCategoryAndTaken(@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.findAllByPetrolAndCategoryAndTaken(petrol, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYearAndTaken(@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYearAndTaken(petrol, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndTransmissionAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndTransmissionAndTaken(numberOfSeats, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndCategoryAndTaken(numberOfSeats, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYearAndTaken(numberOfSeats, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByTransmissionAndCategoryAndTaken(@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByTransmissionAndCategoryAndTaken(transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<CarDTO> findAllByTransmissionAndYearAndTaken(@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByTransmissionAndYearAndTaken(transmission, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByCategoryAndYearAndTaken(@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByCategoryAndYearAndTaken(category, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //6;
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYearAndTaken(numberOfSeats, brand, petrol, transmission, category, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //5
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndTaken(numberOfSeats, brand, petrol, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYearAndTaken(numberOfSeats, brand, petrol, transmission, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYearAndTaken(numberOfSeats, brand, petrol, category, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYearAndTaken(numberOfSeats, brand, transmission, category, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYearAndTaken(numberOfSeats, transmission, petrol, category, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByTransmissionAndBrandAndPetrolAndCategoryAndYearAndTaken(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByTransmissionAndBrandAndPetrolAndCategoryAndYearAndTaken(transmission, brand, petrol,  category, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //3
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTaken(numberOfSeats, brand, petrol,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndYearAndTaken(numberOfSeats, brand, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmissionAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndBrandAndTransmissionAndTaken(numberOfSeats, brand, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndCategoryAndTaken(numberOfSeats, brand, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Integer year){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndYearAndTaken(numberOfSeats, petrol, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Category category){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndCategoryAndTaken(numberOfSeats, petrol, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndTransmissionAndTaken(@RequestParam Integer numberOfSeats,@RequestParam  Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndTransmissionAndTaken(numberOfSeats, petrol, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYearAndTransmissionAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYearAndTransmissionAndTaken(numberOfSeats, year, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYearAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYearAndCategoryAndTaken(numberOfSeats,year,category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndTransmissionAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndTransmissionAndCategoryAndTaken(numberOfSeats, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public List<CarDTO> findAllByBrandAndPetrolAndYearAndTaken(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.findAllByBrandAndPetrolAndYearAndTaken(brand, petrol, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndTransmissionAndTaken(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByBrandAndPetrolAndTransmissionAndTaken(brand, petrol, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndCategoryAndTaken(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.findAllByBrandAndPetrolAndCategoryAndTaken(brand, petrol, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYearAndTransmissionAndTaken(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYearAndTransmissionAndTaken(brand,year,transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYearAndCategoryAndTaken(@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYearAndCategoryAndTaken(brand, year, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndTransmissionAndCategoryAndTaken(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByBrandAndTransmissionAndCategoryAndTaken(brand, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYearAndTransmissionAndTaken(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYearAndTransmissionAndTaken(petrol,  year, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYearAndCategoryAndTaken(@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYearAndCategoryAndTaken(petrol, year,category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndTransmissionAndCategoryAndTaken(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByPetrolAndTransmissionAndCategoryAndTaken(petrol, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByYearAndTransmissionAndCategoryAndTaken(@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByYearAndTransmissionAndCategoryAndTaken( year,transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //4
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndYearAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndYearAndTaken(numberOfSeats, brand, petrol, year,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndTaken(numberOfSeats, brand, petrol, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndTaken(numberOfSeats, brand, petrol, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYearAndTransmissionAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndYearAndTransmissionAndTaken(numberOfSeats, petrol,year, transmission,false ).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYearAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndYearAndCategoryAndTaken(numberOfSeats, petrol, year, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategoryAndTaken(numberOfSeats, petrol, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYearAndTransmissionAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYearAndTransmissionAndCategoryAndTaken(numberOfSeats, year, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndTaken(numberOfSeats, brand, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYearAndCategoryAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndYearAndCategoryAndTaken(numberOfSeats, brand,  year,category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYearAndTransmissionAndTaken(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndYearAndTransmissionAndTaken(numberOfSeats, brand,  year,transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndYearAndTransmissionAndTaken(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByBrandAndPetrolAndYearAndTransmissionAndTaken(brand, petrol, year, transmission,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndYearAndCategoryAndTaken(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByBrandAndPetrolAndYearAndCategoryAndTaken(brand, petrol, year, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYearAndTransmissionAndCategoryAndTaken(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYearAndTransmissionAndCategoryAndTaken(brand, year, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndTransmissionAndCategoryAndTaken(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByBrandAndPetrolAndTransmissionAndCategoryAndTaken(brand, petrol, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYearAndTransmissionAndCategoryAndTaken(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYearAndTransmissionAndCategoryAndTaken(petrol, year, transmission, category,false).stream().map(this::mapToDTO).collect(Collectors.toList());
    }






    public List<CarDTO> findAllByBrandAndPetrol(@RequestParam String brand,@RequestParam Petrol petrol){
        return carRepository.findAllByBrandAndPetrol(brand, petrol).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndNumberOfSeats(@RequestParam String brand,@RequestParam Integer numberOfSeats){
        return carRepository.findAllByBrandAndNumberOfSeats(brand, numberOfSeats).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndTransmission(@RequestParam String brand,@RequestParam Transmission transmission){
        return carRepository.findAllByBrandAndTransmission(brand, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndCategory(@RequestParam String brand,@RequestParam Category category){
        return carRepository.findAllByBrandAndCategory(brand, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYear(@RequestParam String brand,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYear(brand, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndNumberOfSeats(@RequestParam Petrol petrol,@RequestParam Integer numberOfSeats){
        return carRepository.findAllByPetrolAndNumberOfSeats(petrol, numberOfSeats).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndTransmission(@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByPetrolAndTransmission(petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndCategory(@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.findAllByPetrolAndCategory(petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYear(@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYear(petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndTransmission(numberOfSeats, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndCategory(numberOfSeats, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYear(@RequestParam Integer numberOfSeats,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYear(numberOfSeats, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByTransmissionAndCategory(@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByTransmissionAndCategory(transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<CarDTO> findAllByTransmissionAndYear(@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByTransmissionAndYear(transmission, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByCategoryAndYear(@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByCategoryAndYear(category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    //6;
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(numberOfSeats, brand, petrol, transmission, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //5
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(numberOfSeats, brand, petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(numberOfSeats, brand, petrol, transmission, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(numberOfSeats, brand, petrol, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(numberOfSeats, brand, transmission, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(numberOfSeats, transmission, petrol, category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByTransmissionAndBrandAndPetrolAndCategoryAndYear(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByTransmissionAndBrandAndPetrolAndCategoryAndYear(transmission, brand, petrol,  category, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //3
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrol(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrol(numberOfSeats, brand, petrol).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndYear(numberOfSeats, brand, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndBrandAndTransmission(numberOfSeats, brand, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndCategory(numberOfSeats, brand, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYear(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Integer year){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndYear(numberOfSeats, petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam  Category category){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndCategory(numberOfSeats, petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam  Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndTransmission(numberOfSeats, petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYearAndTransmission(numberOfSeats, year, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYearAndCategory(numberOfSeats,year,category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndTransmissionAndCategory(numberOfSeats, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public List<CarDTO> findAllByBrandAndPetrolAndYear(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.findAllByBrandAndPetrolAndYear(brand, petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndTransmission(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByBrandAndPetrolAndTransmission(brand, petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.findAllByBrandAndPetrolAndCategory(brand, petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYearAndTransmission(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYearAndTransmission(brand,year,transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYearAndCategory(@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYearAndCategory(brand, year, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByBrandAndTransmissionAndCategory(brand, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYearAndTransmission(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYearAndTransmission(petrol,  year, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYearAndCategory(@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYearAndCategory(petrol, year,category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndTransmissionAndCategory(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByPetrolAndTransmissionAndCategory(petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByYearAndTransmissionAndCategory(@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByYearAndTransmissionAndCategory( year,transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //4
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndYear(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndYear(numberOfSeats, brand, petrol, year).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmission(numberOfSeats, brand, petrol, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndPetrolAndCategory(numberOfSeats, brand, petrol, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndYearAndTransmission(numberOfSeats, petrol,year, transmission ).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndYearAndCategory(numberOfSeats, petrol, year, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategory(numberOfSeats, petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndYearAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndYearAndTransmissionAndCategory(numberOfSeats, year, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByNumberOfSeatsAndBrandAndTransmissionAndCategory(numberOfSeats, brand, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYearAndCategory(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndYearAndCategory(numberOfSeats, brand,  year,category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYearAndTransmission(@RequestParam Integer numberOfSeats,@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByNumberOfSeatsAndBrandAndYearAndTransmission(numberOfSeats, brand,  year,transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndYearAndTransmission(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Integer year){
        return carRepository.findAllByBrandAndPetrolAndYearAndTransmission(brand, petrol, year, transmission).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndYearAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByBrandAndPetrolAndYearAndCategory(brand, petrol, year, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndYearAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByBrandAndYearAndTransmissionAndCategory(brand, year, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByBrandAndPetrolAndTransmissionAndCategory(@RequestParam String brand,@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category){
        return carRepository.findAllByBrandAndPetrolAndTransmissionAndCategory(brand, petrol, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> findAllByPetrolAndYearAndTransmissionAndCategory(@RequestParam Petrol petrol,@RequestParam Transmission transmission,@RequestParam Category category,@RequestParam Integer year){
        return carRepository.findAllByPetrolAndYearAndTransmissionAndCategory(petrol, year, transmission, category).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<Integer> getAllYears() {
        List<Car> all = carRepository.findAll();
        List<Integer> res = new ArrayList<>();
        for(Car y : all){
            if(!res.contains(y.getYear())){
                res.add(y.getYear());
            }
        }
        return res;
    }
}
