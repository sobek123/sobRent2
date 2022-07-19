package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.macieksob.rentCar.service.MailService;
import pl.macieksob.rentCar.service.CarService;

@RestController
public class Controller {

    @Autowired
    private MailService mailService;

    @Autowired
    private CarService carService;


    @GetMapping("/hello")
    public String hel(){
        return "hello";
    }
}
