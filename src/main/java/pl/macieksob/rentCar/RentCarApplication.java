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
			carService.addCar(new CarDTO(1l, "M3", "BI1234T", 256, 456, 7.8, new Prize(1L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400,3));
			carService.addCar(new CarDTO(2L, "LFA", "BI6734T", 256, 456, 9.0, new Prize(2L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.5, "hfghfg", "Lexus", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2016, 900,4));
			carService.addCar(new CarDTO(3L, "GTR", "BI6734T", 256, 456, 11.0, new Prize(3L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.6, "hfghfg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2017, 800,2));
			carService.addCar(new CarDTO(4L, "E 200", "BI6734T", 256, 456, 9.0, new Prize(4L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,4));
			carService.addCar(new CarDTO(5L, "Sprinter", "BI6734T", 256, 456, 6.8, new Prize(5L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2016, 300,3));
			carService.addCar(new CarDTO(6L, "A6", "WE5436R", 256, 456, 8.7, new Prize(6L, BigDecimal.valueOf(109), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "hfghfg", "Audi", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2019, 350,3));
			carService.addCar(new CarDTO(7L, "Ducato", "BI6734T", 256, 456, 11.0, new Prize(7L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.4, "hfghfg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 250,5));
			carService.addCar(new CarDTO(8L, "R8", "BI6734T", 256, 456, 9.0, new Prize(8L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.2, "hfghfg", "Audi", Transmission.MANUALNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2015, 800,4));
			carService.addCar(new CarDTO(9L, "RS7", "BI6734T", 256, 456, 6.8, new Prize(9L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.6, "hfghfg", "Audi", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2017, 750,6));
			carService.addCar(new CarDTO(10L, "Huracan", "BI6734T", 256, 456, 9.0, new Prize(10L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.5, "hfghfg", "Lamborghini", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2016, 1000,5));
			carService.addCar(new CarDTO(11L, "Aygo", "BI6734T", 256, 456, 11.0, new Prize(11L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.6, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2018, 200,4));
			carService.addCar(new CarDTO(12L, "C-HR", "BI6734T", 256, 456, 9.0, new Prize(12L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.8, "hfghfg", "Toyota", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.ELEKTRYCZNY, 2018, 250,3));
			carService.addCar(new CarDTO(13L, "Juke", "BI6734T", 256, 456, 6.8, new Prize(13L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.2, "hfghfg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2022, 250,3));
			carService.addCar(new CarDTO(14L, "Q5", "BI6734T", 256, 456, 9.0, new Prize(14L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "hfghfg", "Audi", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2021, 350,4));
			carService.addCar(new CarDTO(15L, "Q8", "BI6734T", 256, 456, 11.0, new Prize(15L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					4.0, "hfghfg", "Audi", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2019, 400,5));
			carService.addCar(new CarDTO(16L, "Yaris", "BI6734T", 256, 456, 9.0, new Prize(16L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.4, "hfghfg", "Toyota", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200,6));
			carService.addCar(new CarDTO(17L, "LC500", "BI6734T", 256, 456, 6.8, new Prize(17L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "hfghfg", "Lexus", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.HYBRID, 2016, 500,7));
			carService.addCar(new CarDTO(18L, "720S", "BI6734T", 256, 456, 9.0, new Prize(18L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					5.5, "hfghfg", "McLaren", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2019, 600,3));
			carService.addCar(new CarDTO(19L, "Supra", "BI6734T", 256, 456, 11.0, new Prize(19L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.6, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2018, 350,4));
			carService.addCar(new CarDTO(20L, "Corsa", "BI6734T", 256, 456, 9.0, new Prize(20L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "hfghfg", "Opel", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200,5));
			carService.addCar(new CarDTO(21L, "A 45 AMG", "BI6734T", 256, 456, 6.8, new Prize(21L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.DIESEL, 2017, 300,6));
			carService.addCar(new CarDTO(22L, "Astra", "BI6734T", 256, 456, 11.0, new Prize(22L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.8, "hfghfg", "Opel", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.BENZYNA, 2017, 200,7));
			carService.addCar(new CarDTO(23L, "Megane", "BI6734T", 256, 456, 9.0, new Prize(23L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.8, "hfghfg", "Renault", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2018, 200,5));
			carService.addCar(new CarDTO(24L, "Qashqai", "BI6734T", 256, 456, 6.8, new Prize(24L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.4, "hfghfg", "Nissan", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.DIESEL, 2016, 250,4));
			carService.addCar(new CarDTO(25L, "LS500h", "BI6734T", 256, 456, 11.0, new Prize(25L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "hfghfg", "Lexus", Transmission.MANUALNA, "poduszki", Category.EKSKLUZYWNE, Petrol.HYBRID, 2022, 500,3));
			carService.addCar(new CarDTO(26L, "M8", "BI6734T", 256, 456, 9.0, new Prize(26L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.0, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2018, 500,4));
			carService.addCar(new CarDTO(27L, "SLS AMG", "BI6734T", 256, 456, 6.8, new Prize(27L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					6.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.BENZYNA, 2019, 700,5));
			carService.addCar(new CarDTO(28L, "NSX", "BI6734T", 256, 456, 11.0, new Prize(28L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.2, "hfghfg", "Honda", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SPORT, Petrol.ELEKTRYCZNY, 2021, 450,6));
			carService.addCar(new CarDTO(29L, "7", "BI6734T", 256, 456, 9.0, new Prize(29L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					4.5, "hfghfg", "BMW", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.DIESEL, 2019, 350,6));
			carService.addCar(new CarDTO(30L, "Arona", "BI6734T", 256, 456, 6.8, new Prize(30L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "hfghfg", "Seat", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2018, 250,5));
			carService.addCar(new CarDTO(31L, "Panda", "BI6734T", 256, 456, 11.0, new Prize(31L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.4, "hfghfg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2019, 200,4));
			carService.addCar(new CarDTO(32L, "500", "BI6734T", 256, 456, 9.0, new Prize(32L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					1.6, "hfghfg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200,3));
			carService.addCar(new CarDTO(33L, "C3", "BI6734T", 256, 456, 6.8, new Prize(33L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "hfghfg", "Citroen", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2017, 200,4));
			carService.addCar(new CarDTO(34L, "3", "BI6734T", 256, 456, 11.0, new Prize(34L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "hfghfg", "BMW", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2015, 300,5));
			carService.addCar(new CarDTO(35L, "X5", "BI6734T", 256, 456, 9.0, new Prize(35L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.5, "hfghfg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2016, 350,6));
			carService.addCar(new CarDTO(36L, "RX450h", "BI6734T", 256, 456, 6.8, new Prize(36L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "hfghfg", "Lexus", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.HYBRID, 2018, 300,4));
			carService.addCar(new CarDTO(37L, "A5", "BI6734T", 256, 456, 11.0, new Prize(37L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					3.0, "hfghfg", "Audi", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2019, 300,5));
			carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 256, 456, 9.0, new Prize(38L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.6, "hfghfg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200,4));
			carService.addCar(new CarDTO(39L, "Boxer", "BI6734T", 256, 456, 6.8, new Prize(39L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
					, 2.5, "hfghfg", "Peugeot", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2021, 200,3));
			carService.addCar(new CarDTO(40L, "Crafter", "BI6734T", 256, 456, 11.0, new Prize(40L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
					2.0, "hfghfg", "Volkswagen", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.BENZYNA, 2022, 200,4));
			carService.addCar(new CarDTO(41L, "RAV4", "BI6734T", 256, 456, 9.0, new Prize(41L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
					, 2.0, "hfghfg", "Toyota", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SUV, Petrol.ELEKTRYCZNY, 2022, 250,5));
			carService.addCar(new CarDTO(42L, "Corolla", "BI6734T", 256, 456, 6.8, new Prize(42L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
					, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250,4));
		};


	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
