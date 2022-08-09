package pl.macieksob.rentCar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.model.Category;
import pl.macieksob.rentCar.model.Petrol;
import pl.macieksob.rentCar.model.Prize;
import pl.macieksob.rentCar.model.Transmission;
import pl.macieksob.rentCar.service.CarService;

import java.math.BigDecimal;

@SpringBootApplication
public class RentCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(CarService carService) {

		//System.out.println("Ładuję podstawowe dane.");
		//logger.info("Ładuję podstawowe dane.");


		return (args) -> {
			carService.addCar(new CarDTO(1l, "M3","BI1234T",256,456,7.8,new Prize(1L, BigDecimal.valueOf(89),BigDecimal.valueOf(129),BigDecimal.valueOf(199),BigDecimal.valueOf(219),BigDecimal.valueOf(259),BigDecimal.valueOf(269),BigDecimal.valueOf(300)),3.5,"hfghfg","BMW"
			, Transmission.AUTOMATYCZNA,"poduszki", Category.KOMFORTOWE, Petrol.BENZYNA,2020,300));

		};
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
