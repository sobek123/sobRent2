package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Category;
import pl.macieksob.rentCar.model.Petrol;
import pl.macieksob.rentCar.model.Transmission;
import pl.macieksob.rentCar.service.CarService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
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

    @PostMapping("/newCar")
    public CarDTO saveCar(@Valid @RequestBody CarDTO car){
        return carService.addCar(car);
    }


    @GetMapping("/all")
    @CrossOrigin("http://localhost:3000")

    public List<CarDTO> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarDTO getCar(@PathVariable Long id){
        return carService.getCarById(id);

    }

    @PutMapping("/editCar/{id}")
    public void editCar(@PathVariable Long id, @Valid @RequestBody CarDTO newCar){
        carService.editCar(id,newCar);

    }

    @DeleteMapping("/deleteCar/{id}")
    public void deleteCarById(@PathVariable Long id){
        carService.deleteCarById(id);

    }

    @DeleteMapping("/deleteCar")
    public void deleteCar(CarDTO user){
        carService.deleteCar(user);

    }

    @GetMapping("/brand")
    public List<CarDTO> getCarsByBrand(@RequestParam(value = "brand") String brand){
        return carService.getByBrand(brand);
    }

    @GetMapping("/engine")
    public List<CarDTO> getCarsByEngine(@RequestParam(value = "engine") Double engine){
        return carService.getByEngine(engine);
    }

    @GetMapping("/km")
    public List<CarDTO> getCarsByKm(@RequestParam(value = "km") Integer km){
        return carService.getByKm(km);
    }

    @GetMapping("/model")
    public List<CarDTO> getCarsByModel(@RequestParam(value = "model") String model){
        return carService.getByModel(model);
    }

    @GetMapping("/petrol")
    public List<CarDTO> getCarsByPetrol(@RequestParam(value = "petrol") Petrol petrol){
        return carService.getByPetrol(petrol);
    }

    @GetMapping("/year")
    public List<CarDTO> getCarsByYear(@RequestParam(value = "year") Integer year){
        return carService.getByYear(year);
    }

    @GetMapping("/prize")
    public List<CarDTO> getCarsByPrize(@RequestParam(value = "prize") BigDecimal prize){
        return carService.getByPrize(prize);
    }

    @GetMapping("/transmission")
    public List<CarDTO> getCarsByTransmission(@RequestParam(value = "transmission") Transmission transmission){
        return carService.getByTransmission(transmission);
    }

//    @GetMapping("/")
//    public List<CarDTO> getCarsByTransmission(@RequestParam(value = "keyword") String keyword){
//        return carService.getByKeyword();
//    }

    @GetMapping("/")
    public List<CarDTO> getCarsByTransmissionAndModelAndBrandAndYearAndEngineAndKmAndPetrol(@RequestParam(value = "transmission") Transmission transmission,@RequestParam(value = "model") String model,
    @RequestParam(value = "brand") String brand,@RequestParam(value = "year") Integer year,@RequestParam(value = "engine") Double engine,@RequestParam(value = "petrol") Petrol petrol,
    @RequestParam(value = "km") Integer km,@RequestParam(value = "prize") BigDecimal prize){
        return carService.getAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(brand,model,prize,km,petrol,year,engine,transmission);
    }

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


    @CrossOrigin("http://localhost:3000")
    @GetMapping("/models")
    public List<String> getModels(){
        System.out.println(carService.getAllCars().stream().map(CarDTO::getModel).collect(Collectors.toList()));
        return carService.getAllCars().stream().map(CarDTO::getModel).collect(Collectors.toList());

    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/brands")
    public List<String> getBrands(){
        System.out.println(carService.getAllCars().stream().map(CarDTO::getBrand).collect(Collectors.toList()));
        return carService.getAllCars().stream().map(CarDTO::getBrand).collect(Collectors.toList());
    }

    @GetMapping("/years")
    public List<Integer> getYears(){
        System.out.println(carService.getAllCars().stream().map(CarDTO::getYear).collect(Collectors.toList()));
        return carService.getAllCars().stream().map(CarDTO::getYear).collect(Collectors.toList());
    }



}
