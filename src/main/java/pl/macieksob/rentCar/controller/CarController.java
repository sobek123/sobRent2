package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.repository.CarRepository;
import pl.macieksob.rentCar.service.CarService;

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
}
