package pl.macieksob.rentCar.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.service.CarService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("https://sobrent.herokuapp.com")
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/sport")
    public List<CarDTO> getSportCars(){
        return carService.getSportCar();
    }

    @GetMapping("/economy")
    public List<CarDTO> getEconomyCars(){
        return carService.getEconomyCar();
    }

    @GetMapping("/comfort")
    public List<CarDTO> getComfortCars(){
        return carService.getComfortCar();
    }

    @GetMapping("/SUV")
    public List<CarDTO> getSUVCars(){
        return carService.getSUVCar();
    }

    @GetMapping("/cargo")
    public List<CarDTO> getCargoCars(){
        return carService.getCargoCar();
    }

    @GetMapping("/exclusive")
    public List<CarDTO> getExclusiveCars(){
        return carService.getExclusiveCar();
    }

    @RequestMapping(value = "/newCar",method=RequestMethod.POST)
    @CrossOrigin("https://sobrent.herokuapp.com")
    public CarDTO saveCar(@Valid @RequestBody CarDTO car){
        return carService.addCar(car);
    }


    @GetMapping("/all")
    @CrossOrigin("https://sobrent.herokuapp.com")
    public List<CarDTO> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable Long id){
        return carService.getCarById(id);

    }

    @RequestMapping(value = "/editCar/{id}",method=RequestMethod.PUT)
    public void editCar(@PathVariable("id") Long id, @Valid @RequestBody CarDTO newCar){
        carService.editCar(id,newCar);

    }

    @RequestMapping(value = "/deleteCar/{id}",method=RequestMethod.DELETE)
    @CrossOrigin("https://sobrent.herokuapp.com")
    public void deleteCarById(@PathVariable("id") Long id){
        carService.deleteCarById(id);

    }

    @RequestMapping(value = "/deleteCar",method=RequestMethod.DELETE)
    @CrossOrigin("https://sobrent.herokuapp.com")
    public void deleteCar(CarDTO user){
        carService.deleteCar(user);

    }

    @GetMapping("/brand")
    public List<CarDTO> getCarsByBrand(@RequestParam(value = "brand") String brand){
        return carService.findAllByBrand(brand);
    }

    @GetMapping("/engine")
    public List<CarDTO> getCarsByEngine(@RequestParam(value = "engine") Double engine){
        return carService.findAllByEngine(engine);
    }

    @GetMapping("/km")
    public List<CarDTO> getCarsByKm(@RequestParam(value = "km") Integer km){
        return carService.findAllByKm(km);
    }

    @GetMapping("/model")
    public List<CarDTO> getCarsByModel(@RequestParam(value = "model") String model){
        return carService.findAllByModel(model);
    }

    @GetMapping("/petrol")
    public List<CarDTO> getCarsByPetrol(@RequestParam(value = "petrol") Petrol petrol){
        return carService.findAllByPetrol(petrol);
    }

    @GetMapping("/year")
    public List<CarDTO> getCarsByYear(@RequestParam(value = "year") Integer year){
        return carService.findAllByYear(year);
    }

//    @GetMapping("/prize")
//    public List<CarDTO> getCarsByPrize(@RequestParam(value = "prize") BigDecimal prize){
//        return carService.findAllByPrize(prize);
//    }

    @GetMapping("/transmission")
    public List<CarDTO> getCarsByTransmission(@RequestParam(value = "transmission") Transmission transmission){
        return carService.findAllByTransmission(transmission);
    }

    @GetMapping("/keyword")
    public List<CarDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
        return carService.findAllByKeyword(keyword);
    }

//    @GetMapping("/")
//    public List<CarDTO> getCarsByTransmissionAndModelAndBrandAndYearAndEngineAndKmAndPetrol(@RequestParam(value = "transmission") Transmission transmission,@RequestParam(value = "model") String model,
//    @RequestParam(value = "brand") String brand,@RequestParam(value = "year") Integer year,@RequestParam(value = "engine") Double engine,@RequestParam(value = "petrol") Petrol petrol,
//    @RequestParam(value = "km") Integer km,@RequestParam(value = "prize") BigDecimal prize){
//        return carService.getAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(brand,model,prize,km,petrol,year,engine,transmission);
//    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        Category[] values = Category.values();

        return Arrays.asList(values);
    }

    @GetMapping("/transmissions")
    public List<Transmission> getTransmissions(){
        Transmission[] values = Transmission.values();
        return Arrays.asList(values);
    }


    @GetMapping("/petrols")
    public List<Petrol> getPetrols(){
        Petrol[] values = Petrol.values();
        return Arrays.asList(values);
    }

//    @RequestMapping(value = "/setOpened", method = RequestMethod.PUT)
//    public CarDTO setOpened(@Vali@RequestBody CarDTO carDTO){
//        return carService.editCar(carDTO.getId(), carDTO);
//    }


    @CrossOrigin("https://sobrent.herokuapp.com")
    @GetMapping("/models")
    public List<String> getModels(){
        return carService.getAllCars().stream().map(CarDTO::getModel).collect(Collectors.toList());

    }

    @CrossOrigin("https://sobrent.herokuapp.com")
    @GetMapping("/brands")
    public List<String> getBrands(){
        return carService.getAllCars().stream().distinct().map(CarDTO::getBrand).collect(Collectors.toList());
    }

    @GetMapping("/years")
    public List<Integer> getYears(){
        return carService.getAllYears();
    }

    @GetMapping("/modelByBrand")
    public List<String> getModelByBrand(@RequestParam(value = "brand") String brand, @RequestParam(value ="category", required=false) Category category){
        return carService.getModelsByBrands(brand,category);
    }

    @GetMapping("/yearByBrand")
    public List<Integer> getYearByBrand(@RequestParam(value = "brand") String brand){
        return carService.getYearByBrands(brand);
    }

    @GetMapping("/numberOfSeatsByBrand")
    public List<Integer> getNumberOfSeatsByBrand(@RequestParam(value = "brand") String brand){
        return carService.getNumberOfSeatsByBrands(brand);
    }
    @GetMapping("/sortByKmAsc")
    public List<CarDTO> sortCarsByKmAsc(){
        return carService.sortByKmAscending();
    }
    @GetMapping("/sortByKmDesc")
    public List<CarDTO> sortCarsByKmDesc(){
        return carService.sortByKmDescending();
    }

    @GetMapping("/sortByNmAsc")
    public List<CarDTO> sortCarsByNmAsc(){
        return carService.sortByNmAscending();
    }

    @GetMapping("/sortByNmDesc")
    public List<CarDTO> sortCarsByNmDesc(){
        return carService.sortByNmDescending();
    }

    @GetMapping("/sortByNumberOfSeatsAsc")
    public List<CarDTO> sortCarsByNumberOfSeatsAsc(){
        return carService.sortByNumberOfSeatsAscending();
    }

    @GetMapping("/sortByNumberOfSeatsDesc")
    public List<CarDTO> sortCarsByNumberOfSeatsDesc(){
        return carService.sortByNumberOfSeatsDescending();
    }

    @GetMapping("/sortByEngineAsc")
    public List<CarDTO> sortCarsByEngineAsc(){
        return carService.sortByEngineAscending();
    }

    @GetMapping("/sortByEngineDesc")
    public List<CarDTO> sortCarsByEngineDesc(){
        return carService.sortByEngineDescending();
    }

    @GetMapping("/sortByCombustionAsc")
    public List<CarDTO> sortCarsByCombustionAsc(){
        return carService.sortByCombustionAscending();
    }

    @GetMapping("/sortByCombustionDesc")
    public List<CarDTO> sortCarsByCombustionDesc(){
        return carService.sortByCombustionDescending();
    }

    @GetMapping("/sortByYearAsc")
    public List<CarDTO> sortCarsByYearAsc(){
        return carService.sortByYearAscending();
    }

    @GetMapping("/sortByYearDesc")
    public List<CarDTO> sortCarsByYearDesc(@RequestParam(required = false, value = "category") Category category){
        return carService.sortByYearDescending();
    }

    @GetMapping("/getByStartDateAndEndDate")
    public List<CarDTO> findAllByStartDateAndEndDate(@RequestParam("startDate")String startDate, @RequestParam("endDate")String endDate){
        System.out.println(startDate + " "+endDate);
        return carService.getCarsByStartDateAndEndDate(startDate,endDate);
    }

    @RequestMapping(value = "/fault",method=RequestMethod.PUT)
    public void addFault(@RequestParam("fault") String fault,@RequestParam("id") Long id){
        carService.addFault(fault, id);
    }



    @GetMapping("/getByBrandAndPetrol")
    public List<CarDTO> findAllByBrandAndPetrol(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol){
        return carService.findAllByBrandAndPetrol(brand, petrol);
    }

    @GetMapping("/getByBrandAndNumberOfSeats")
    public List<CarDTO> findAllByBrandAndNumberOfSeats(@RequestParam("brand") String brand,@RequestParam("numberOfSeats") String numberOfSeats){
        return carService.findAllByBrandAndNumberOfSeats(brand, Integer.valueOf(numberOfSeats));
    }

    @GetMapping("/getByBrandAndTransmission")
    public List<CarDTO> findAllByBrandAndTransmission(@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByBrandAndTransmission(brand, transmission);
    }

    @GetMapping("/getByBrandAndCategory")
    public List<CarDTO> findAllByBrandAndCategory(@RequestParam("brand") String brand,@RequestParam("category") Category category){
        return carService.findAllByBrandAndCategory(brand, category);
    }

    @GetMapping("/getByBrandAndYear")
    public List<CarDTO> findAllByBrandAndYear(@RequestParam("brand") String brand,@RequestParam("year") Integer year){
        return carService.findAllByBrandAndYear(brand, year);
    }

    @GetMapping("/getByPetrolAndNumberOfSeats")
    public List<CarDTO> findAllByPetrolAndNumberOfSeats(@RequestParam("petrol") Petrol petrol,@RequestParam("numberOfSeats") String numberOfSeats){
        return carService.findAllByPetrolAndNumberOfSeats(petrol, Integer.valueOf(numberOfSeats));
    }

    @GetMapping("/getByPetrolAndTransmission")
    public List<CarDTO> findAllByPetrolAndTransmission(@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByPetrolAndTransmission(petrol, transmission);
    }

    @GetMapping("/getByPetrolAndCategory")
    public List<CarDTO> findAllByPetrolAndCategory(@RequestParam("petrol") Petrol petrol,@RequestParam("category") Category category){
        return carService.findAllByPetrolAndCategory(petrol, category);
    }

    @GetMapping("/getByPetrolAndYear")
    public List<CarDTO> findAllByPetrolAndYear(@RequestParam("petrol") Petrol petrol,@RequestParam("year") Integer year){
        return carService.findAllByPetrolAndYear(petrol, year);
    }

    @GetMapping("/getByNumberOfSeatsAndTransmission")
    public List<CarDTO> findAllByNumberOfSeatsAndTransmission(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByNumberOfSeatsAndTransmission(Integer.valueOf(numberOfSeats), transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("category") Category category){
        return carService.findAllByNumberOfSeatsAndCategory(Integer.valueOf(numberOfSeats), category);
    }

    @GetMapping("/getByNumberOfSeatsAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndYear(Integer.valueOf(numberOfSeats), year);
    }

    @GetMapping("/getByTransmissionAndCategory")
    public List<CarDTO> findAllByTransmissionAndCategory(@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByTransmissionAndCategory(transmission, category);
    }

    @GetMapping("/getByTransmissionAndYear")
    public List<CarDTO> findAllByTransmissionAndYear(@RequestParam("transmission") Transmission transmission,@RequestParam("year") Integer year){
        return carService.findAllByTransmissionAndYear(transmission, year);
    }

    @GetMapping("/getByCategoryAndYear")
    public List<CarDTO> findAllByCategoryAndYear(@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByCategoryAndYear(category, year);
    }
//6;

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(Integer.valueOf(numberOfSeats), brand, petrol, transmission, category, year);
    }

    //5
    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(Integer.valueOf(numberOfSeats), brand, petrol, transmission, category);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(Integer.valueOf(numberOfSeats), brand, petrol, transmission, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(Integer.valueOf(numberOfSeats), brand, petrol, category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(Integer.valueOf(numberOfSeats), brand, transmission, category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(Integer.valueOf(numberOfSeats), petrol,transmission,  category, year);
    }

    @GetMapping("/getByTransmissionAndBrandAndPetrolAndCategoryAndYear")
    public List<CarDTO> findAllByTransmissionAndBrandAndPetrolAndCategoryAndYear(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByTransmissionAndBrandAndPetrolAndCategoryAndYear( brand, petrol, transmission, category, year);
    }

    //3
    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrol")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrol(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrol(Integer.valueOf(numberOfSeats), brand, petrol);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndYear(Integer.valueOf(numberOfSeats), brand, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndTransmission")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmission(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByNumberOfSeatsAndBrandAndTransmission(Integer.valueOf(numberOfSeats), brand, transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("category") Category category){
        return carService.findAllByNumberOfSeatsAndBrandAndCategory(Integer.valueOf(numberOfSeats), brand, category);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndYear")
    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("petrol") Petrol petrol,@RequestParam("year")  Integer year){
        return carService.findAllByNumberOfSeatsAndPetrolAndYear(Integer.valueOf(numberOfSeats), petrol, year);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("petrol") Petrol petrol,@RequestParam("category")  Category category){
        return carService.findAllByNumberOfSeatsAndPetrolAndCategory(Integer.valueOf(numberOfSeats), petrol, category);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndTransmission")
    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndTransmission(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("petrol")  Petrol petrol,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByNumberOfSeatsAndPetrolAndTransmission(Integer.valueOf(numberOfSeats), petrol, transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndYearAndTransmission")
    public List<CarDTO> findAllByNumberOfSeatsAndYearAndTransmission(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam Integer year,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByNumberOfSeatsAndYearAndTransmission(Integer.valueOf(numberOfSeats), transmission, year);
    }

    @GetMapping("/getByNumberOfSeatsAndYearAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndYearAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndYearAndCategory(Integer.valueOf(numberOfSeats),category,year);
    }

    @GetMapping("/getByNumberOfSeatsAndTransmissionAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndTransmissionAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByNumberOfSeatsAndTransmissionAndCategory(Integer.valueOf(numberOfSeats), transmission, category);
    }

    @GetMapping("/getByBrandAndPetrolAndYear")
    public List<CarDTO> findAllByBrandAndPetrolAndYear(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("year") Integer year){
        return carService.findAllByBrandAndPetrolAndYear(brand, petrol, year);
    }

    @GetMapping("/getByBrandAndPetrolAndTransmission")
    public List<CarDTO> findAllByBrandAndPetrolAndTransmission(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByBrandAndPetrolAndTransmission(brand, petrol, transmission);
    }

    @GetMapping("/getByBrandAndPetrolAndCategory")
    public List<CarDTO> findAllByBrandAndPetrolAndCategory(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("category") Category category){
        return carService.findAllByBrandAndPetrolAndCategory(brand, petrol, category);
    }

    @GetMapping("/getByBrandAndYearAndTransmission")
    public List<CarDTO> findAllByBrandAndYearAndTransmission(@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission,@RequestParam("year") Integer year){
        return carService.findAllByBrandAndYearAndTransmission(brand,transmission,year);
    }

    @GetMapping("/getByBrandAndYearAndCategory")
    public List<CarDTO> findAllByBrandAndYearAndCategory(@RequestParam("brand") String brand,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByBrandAndYearAndCategory(brand, category, year);
    }

    @GetMapping("/getByBrandAndTransmissionAndCategory")
    public List<CarDTO> findAllByBrandAndTransmissionAndCategory(@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByBrandAndTransmissionAndCategory(brand, transmission, category);
    }

    @GetMapping("/getByPetrolAndYearAndTransmission")
    public List<CarDTO> findAllByPetrolAndYearAndTransmission(@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("year") Integer year){
        return carService.findAllByPetrolAndYearAndTransmission(petrol, transmission, year );
    }

    @GetMapping("/getByPetrolAndYearAndCategory")
    public List<CarDTO> findAllByPetrolAndYearAndCategory(@RequestParam("petrol") Petrol petrol,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByPetrolAndYearAndCategory(petrol,category, year);
    }

    @GetMapping("/getByPetrolAndTransmissionAndCategory")
    public List<CarDTO> findAllByPetrolAndTransmissionAndCategory(@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByPetrolAndTransmissionAndCategory(petrol, transmission, category);
    }

    @GetMapping("/getByYearAndTransmissionAndCategory")
    public List<CarDTO> findAllByYearAndTransmissionAndCategory(@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByYearAndTransmissionAndCategory( transmission, category,year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndYear")
//4
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndYear(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrolAndYear(Integer.valueOf(numberOfSeats), brand, petrol, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndTransmission")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmission(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrolAndTransmission(Integer.valueOf(numberOfSeats), brand, petrol, transmission);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndPetrolAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndPetrolAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("category") Category category){
        return carService.findAllByNumberOfSeatsAndBrandAndPetrolAndCategory(Integer.valueOf(numberOfSeats), brand, petrol, category);
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndYearAndTransmission")
    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYearAndTransmission(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndPetrolAndYearAndTransmission(Integer.valueOf(numberOfSeats), petrol,transmission,year );
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndYearAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndYearAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("petrol") Petrol petrol,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndPetrolAndYearAndCategory(Integer.valueOf(numberOfSeats), petrol,category, year );
    }

    @GetMapping("/getByNumberOfSeatsAndPetrolAndTransmissionAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategory(Integer.valueOf(numberOfSeats), petrol, transmission, category);
    }

    @GetMapping("/getByNumberOfSeatsAndYearAndTransmissionAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndYearAndTransmissionAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndYearAndTransmissionAndCategory(Integer.valueOf(numberOfSeats), transmission, category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndTransmissionAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByNumberOfSeatsAndBrandAndTransmissionAndCategory(Integer.valueOf(numberOfSeats), brand,transmission,category );
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndYearAndCategory")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYearAndCategory(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndYearAndCategory(Integer.valueOf(numberOfSeats), brand,category, year);
    }

    @GetMapping("/getByNumberOfSeatsAndBrandAndYearAndTransmission")
    public List<CarDTO> findAllByNumberOfSeatsAndBrandAndYearAndTransmission(@RequestParam("numberOfSeats") String numberOfSeats,@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission,@RequestParam("year") Integer year){
        return carService.findAllByNumberOfSeatsAndBrandAndYearAndTransmission(Integer.valueOf(numberOfSeats), brand, transmission, year);
    }

    @GetMapping("/getByBrandAndPetrolAndYearAndTransmission")
    public List<CarDTO> findAllByBrandAndPetrolAndYearAndTransmission(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("year") Integer year){
        return carService.findAllByBrandAndPetrolAndYearAndTransmission(brand, petrol, transmission, year);
    }

    @GetMapping("/getByBrandAndPetrolAndYearAndCategory")
    public List<CarDTO> findAllByBrandAndPetrolAndYearAndCategory(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByBrandAndPetrolAndYearAndCategory(brand, petrol, category, year);
    }

    @GetMapping("/getByBrandAndYearAndTransmissionAndCategory")
    public List<CarDTO> findAllByBrandAndYearAndTransmissionAndCategory(@RequestParam("brand") String brand,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByBrandAndYearAndTransmissionAndCategory(brand,  transmission, category, year);
    }

    @GetMapping("/getByBrandAndPetrolAndTransmissionAndCategory")
    public List<CarDTO> findAllByBrandAndPetrolAndTransmissionAndCategory(@RequestParam("brand") String brand,@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category){
        return carService.findAllByBrandAndPetrolAndTransmissionAndCategory(brand, petrol, transmission, category);
    }

    @GetMapping("/getByPetrolAndYearAndTransmissionAndCategory")
    public List<CarDTO> findAllByPetrolAndYearAndTransmissionAndCategory(@RequestParam("petrol") Petrol petrol,@RequestParam("transmission") Transmission transmission,@RequestParam("category") Category category,@RequestParam("year") Integer year){
        return carService.findAllByPetrolAndYearAndTransmissionAndCategory(petrol, transmission, category,  year);
    }

    @GetMapping("/mostRentedCar")
    public List<CarDTO> mostRentedCar(@RequestParam("days") String days){
        return carService.mostRentedCarInDays(days);
    }

    @GetMapping("/getByLastDays")
    public List<CarDTO> findAllByLastDays(@RequestParam("days") String days){
        return carService.findAllByLastDays(days);
    }

    @GetMapping("/mostRentedCarBrand")
    public List<CarDTO> mostRentedCarBrand(@RequestParam("days") String days){
        return carService.mostRentedCarInDaysBrand(days);
    }

    @GetMapping("/getByLastDaysBrand")
    public List<CarDTO> findAllByLastDaysBrand(@RequestParam("days") String days){
        return carService.findAllByLastDaysBrand(days);
    }

//    @GetMapping("/checkCar")
//    public boolean checkCar(){
//        return false;
//    }

}
