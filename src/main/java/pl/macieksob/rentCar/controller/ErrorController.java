package pl.macieksob.rentCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.macieksob.rentCar.service.MailService;
import pl.macieksob.rentCar.service.CarService;

@RestController
@CrossOrigin("https://sobrent.herokuapp.com")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping("/error")
    public String hel(){
        return "hello";
    }
}
