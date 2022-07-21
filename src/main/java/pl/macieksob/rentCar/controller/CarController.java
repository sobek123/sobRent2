package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Petrol;
import pl.macieksob.rentCar.model.Transmission;
import pl.macieksob.rentCar.service.CarService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/sport")
    public List<Car> getSportCars(){
        return carService.getSportCar();
    }

    @GetMapping("/economy")
    public List<Car> getEconomyCars(){
        return carService.getEconomyCar();
    }

    @GetMapping("/comfort")
    public List<Car> getComfortCars(){
        return carService.getComfortCar();
    }

    @GetMapping("/retro")
    public List<Car> getRetroCars(){
        return carService.getRetroCar();
    }

    @GetMapping("/cargo")
    public List<Car> getCargoCars(){
        return carService.getCargoCar();
    }

    @GetMapping("/exclusive")
    public List<Car> getExclusiveCars(){
        return carService.getExclusiveCar();
    }

    @PostMapping("/newCar")
    public Car saveCar(@RequestBody CarDTO car){
        return carService.addCar(car);
    }


    @GetMapping
    public List<CarDTO> getAllOrders(){
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public String getCar(@PathVariable Long id){
        carService.getCarById(id);

        return "";
    }

    @PutMapping("/editCar/{id}")
    public String editCar(@PathVariable Long id, @RequestBody CarDTO newCar){
        carService.editCar(id,newCar);

        return "";
    }

    @DeleteMapping("/deleteCar/{id}")
    public String deleteUserById(@PathVariable Long id){
        carService.deleteCar(id);
        return "";
    }

    @DeleteMapping("/deleteCar")
    public String deleteUser(CarDTO user){
        carService.deleteCar(user);

        return "";
    }

    @GetMapping("/brand")
    public List<CarDTO> getCarsByBrand(String brand){
        return carService.getByBrand(brand);
    }

    @GetMapping("/engine")
    public List<CarDTO> getCarsByBrand(Double engine){
        return carService.getByEngine(engine);
    }

    @GetMapping("/km")
    public List<CarDTO> getCarsByKm(Integer km){
        return carService.getByKm(km);
    }

    @GetMapping("/model")
    public List<CarDTO> getCarsByModel(String model){
        return carService.getByModel(model);
    }

    @GetMapping("/petrol")
    public List<CarDTO> getCarsByPetrol(Petrol petrol){
        return carService.getByPetrol(petrol);
    }

    @GetMapping("/year")
    public List<CarDTO> getCarsByBrand(Integer year){
        return carService.getByYear(year);
    }

    @GetMapping("/prize")
    public List<CarDTO> getCarsByPrize(BigDecimal prize){
        return carService.getByPrize(prize);
    }

    @GetMapping("/transmission")
    public List<CarDTO> getCarsByTransmission(Transmission transmission){
        return carService.getByTransmission(transmission);
    }
}
