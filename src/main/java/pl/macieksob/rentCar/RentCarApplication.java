package pl.macieksob.rentCar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.macieksob.rentCar.dto.*;
import pl.macieksob.rentCar.model.*;
import pl.macieksob.rentCar.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class RentCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(CarService carService, AdditionalService additionalService, UserService userService, OrderService orderService, FullOrderService fullOrderService, ContactService contactService) {

		return (args) -> {
			CarDTO carDTO37 = carService.addCar(new CarDTO(1L, "M3", "BI 1234T", 256, 456, 7.8, new Price(1L, BigDecimal.valueOf(139), BigDecimal.valueOf(189), BigDecimal.valueOf(299), BigDecimal.valueOf(329), BigDecimal.valueOf(499), BigDecimal.valueOf(569), BigDecimal.valueOf(5000)),
					3.5, "./cars/bmw-seria-5-g30-g31.jpg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2020, 400, 3));
			CarDTO carDTO38 = carService.addCar(new CarDTO(2L, "LFA", "BI 6734T", 660, 700, 9.0, new Price(2L, BigDecimal.valueOf(999), BigDecimal.valueOf(1299), BigDecimal.valueOf(1699), BigDecimal.valueOf(1899), BigDecimal.valueOf(2299), BigDecimal.valueOf(2599), BigDecimal.valueOf(20000)),
					5.5, "./cars/Lexus_LFA_Yellow_Las_Vegas.jpg", "Lexus", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2016, 900, 4));
			CarDTO carDTO36 = carService.addCar(new CarDTO(3L, "GTR", "WE 546RA", 600, 750, 11.0, new Price(3L, BigDecimal.valueOf(899), BigDecimal.valueOf(1199), BigDecimal.valueOf(1599), BigDecimal.valueOf(1799), BigDecimal.valueOf(2199), BigDecimal.valueOf(2499), BigDecimal.valueOf(20000)),
					3.6, "./images.jpg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2017, 800, 2));
			CarDTO poduszki1 = carService.addCar(new CarDTO(4L, "E 200", "WY 86977", 300, 400, 8.4, new Price(4L, BigDecimal.valueOf(599), BigDecimal.valueOf(899), BigDecimal.valueOf(1199), BigDecimal.valueOf(1299), BigDecimal.valueOf(1599), BigDecimal.valueOf(1899), BigDecimal.valueOf(15000)),
					5.6, "./cars/ovZktkpTURBXy9kZmM5NzJjNzY4NjAyZGQxMDg1YmViYTc4NDZmYmRkMC5qcGeRlQPNAQ_NASrNBsHNA84.jpg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300, 4));
			CarDTO carDTO35 = carService.addCar(new CarDTO(5L, "Sprinter", "BI 2231T", 150, 300, 7.0, new Price(5L, BigDecimal.valueOf(599), BigDecimal.valueOf(899), BigDecimal.valueOf(1199), BigDecimal.valueOf(1299), BigDecimal.valueOf(1599), BigDecimal.valueOf(1899), BigDecimal.valueOf(15000)),
					2.5, "./cars/5cd94c4c57502a426b2fbc0e.jpg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2016, 300, 3));
			CarDTO carDTO34 = carService.addCar(new CarDTO(6L, "A6", "WE 5436R", 189, 320, 8.7, new Price(6L, BigDecimal.valueOf(809), BigDecimal.valueOf(899), BigDecimal.valueOf(1199), BigDecimal.valueOf(1299), BigDecimal.valueOf(1599), BigDecimal.valueOf(1899), BigDecimal.valueOf(15000)),
					3.0, "./cars/782226cb52e3b8545a3.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2019, 350, 3));
			CarDTO carDTO33 = carService.addCar(new CarDTO(7L, "Ducato", "BI 7784T", 160, 300, 6.9, new Price(7L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(10000)),
					2.4, "./cars/1627985468_4ducato.jpg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 250, 5));
			CarDTO carDTO31 = carService.addCar(new CarDTO(8L, "R8", "BI 8905G", 700, 850, 9.0, new Price(8L, BigDecimal.valueOf(1199), BigDecimal.valueOf(1599), BigDecimal.valueOf(1899), BigDecimal.valueOf(2299), BigDecimal.valueOf(2599), BigDecimal.valueOf(2899), BigDecimal.valueOf(50000)),
					6.2, "./cars/895473.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2015, 800, 4));
			CarDTO carDTO32 = carService.addCar(new CarDTO(9L, "RS7", "BI 099KJ", 680, 800, 7.5, new Price(9L, BigDecimal.valueOf(999), BigDecimal.valueOf(1299), BigDecimal.valueOf(1599), BigDecimal.valueOf(1899), BigDecimal.valueOf(2099), BigDecimal.valueOf(2299), BigDecimal.valueOf(18000)),
					6.6, "./cars/audi-a3-cennik-2022-otwarcie.jpg", "Audi", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2017, 750, 6));
			CarDTO carDTO30 = carService.addCar(new CarDTO(10L, "Huracan", "WA 67R2T", 859, 1000, 9.5, new Price(10L, BigDecimal.valueOf(3099), BigDecimal.valueOf(3899), BigDecimal.valueOf(4299), BigDecimal.valueOf(4599), BigDecimal.valueOf(6099), BigDecimal.valueOf(8099), BigDecimal.valueOf(120000)),
					5.5, "./cars/1J7A0421-scaled.jpg", "Lamborghini", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2016, 1000, 5));
			CarDTO carDTO29 = carService.addCar(new CarDTO(11L, "Aygo", "BI 8794D", 100, 180, 5.5, new Price(11L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(5000)),
					1.6, "./cars/3db07d49a3be3a69d148387b70f7c6b0b3096dcc.jpg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2018, 200, 4));
			CarDTO carDTO27 = carService.addCar(new CarDTO(12L, "C-HR", "BI 75721", 140, 345, 4.5, new Price(12L, BigDecimal.valueOf(159), BigDecimal.valueOf(209), BigDecimal.valueOf(259), BigDecimal.valueOf(309), BigDecimal.valueOf(339), BigDecimal.valueOf(379), BigDecimal.valueOf(8000)),
					1.8, "./cars/5cd4124557502a9a0827a106.jpg", "Toyota", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.ELEKTRYCZNY, 2018, 250, 3));
			CarDTO carDTO28 = carService.addCar(new CarDTO(13L, "Juke", "WE 17141", 160, 245, 6.0, new Price(13L, BigDecimal.valueOf(159), BigDecimal.valueOf(209), BigDecimal.valueOf(259), BigDecimal.valueOf(309), BigDecimal.valueOf(339), BigDecimal.valueOf(379), BigDecimal.valueOf(8000)),
					2.2, "./cars/62e81411999ca85dbd400b1f309f32d1.jpg", "Nissan", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2022, 250, 3));
			CarDTO carDTO26 = carService.addCar(new CarDTO(14L, "Q5", "BI 57355", 310, 456, 9.0, new Price(14L, BigDecimal.valueOf(159), BigDecimal.valueOf(209), BigDecimal.valueOf(259), BigDecimal.valueOf(309), BigDecimal.valueOf(339), BigDecimal.valueOf(379), BigDecimal.valueOf(8000)),
					3.0, "./cars/audi-q5-cennik-2022-otwarcie.jpg", "Audi", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2021, 350, 4));
			CarDTO carDTO25 = carService.addCar(new CarDTO(15L, "Q8", "WU 06358", 357, 500, 11.0, new Price(15L, BigDecimal.valueOf(599), BigDecimal.valueOf(999), BigDecimal.valueOf(1399), BigDecimal.valueOf(1899), BigDecimal.valueOf(2299), BigDecimal.valueOf(2599), BigDecimal.valueOf(60000)),
					4.0, "./cars/audi-q8.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2019, 400, 5));
			CarDTO carDTO23 = carService.addCar(new CarDTO(16L, "Yaris", "BI 23343", 90, 167, 4.7, new Price(16L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(1000)),
					1.4, "./cars/1141456.jpg", "Toyota", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200, 6));
			CarDTO carDTO24 = carService.addCar(new CarDTO(17L, "LC500", "BI 12345", 450, 650, 6.8, new Price(17L, BigDecimal.valueOf(899), BigDecimal.valueOf(1399), BigDecimal.valueOf(1699), BigDecimal.valueOf(2099), BigDecimal.valueOf(2399), BigDecimal.valueOf(2699), BigDecimal.valueOf(100000)),
					3.0, "./cars/2949273-Ceny-Lexusa-LC-500-Coupe-rozpoczynaja-sie-od-594-tys-zl.jpg", "Lexus", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.HYBRID, 2016, 500, 7));
			CarDTO carDTO21 = carService.addCar(new CarDTO(18L, "720S", "WT 6YT4T", 720, 900, 15.0, new Price(18L, BigDecimal.valueOf(2099), BigDecimal.valueOf(2899), BigDecimal.valueOf(3899), BigDecimal.valueOf(4999), BigDecimal.valueOf(6999), BigDecimal.valueOf(7999), BigDecimal.valueOf(120000)),
					5.5, "./cars/882511.jpg", "McLaren", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2019, 600, 3));
			CarDTO carDTO22 = carService.addCar(new CarDTO(19L, "Supra", "BI 4534T", 350, 520, 11.2, new Price(19L, BigDecimal.valueOf(389), BigDecimal.valueOf(479), BigDecimal.valueOf(559), BigDecimal.valueOf(669), BigDecimal.valueOf(739), BigDecimal.valueOf(819), BigDecimal.valueOf(40000)),
					3.6, "./cars/pol_pl_Splitter-Przedni-V-2-Toyota-Supra-MK5-8876_2.jpg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2018, 350, 4));
			CarDTO carDTO20 = carService.addCar(new CarDTO(20L, "Corsa", "WX 5734A", 125, 235, 4.0, new Price(20L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(1000)),
					2.0, "./cars/opel-corsa-e-cennik-2022-otwarcie.jpg", "Opel", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200, 5));
			CarDTO poduszki = carService.addCar(new CarDTO(21L, "A 45 AMG", "WJ 99342", 280, 357, 6.3, new Price(21L, BigDecimal.valueOf(89), BigDecimal.valueOf(299), BigDecimal.valueOf(399), BigDecimal.valueOf(499), BigDecimal.valueOf(599), BigDecimal.valueOf(699), BigDecimal.valueOf(20000)),
					2.6, "./cars/651294a44c6de78307fdef7dfc0b506d.jpeg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.DIESEL, 2017, 300, 6));
			CarDTO carDTO19 = carService.addCar(new CarDTO(22L, "Astra", "BI 37331", 112, 199, 4.7, new Price(22L, BigDecimal.valueOf(109), BigDecimal.valueOf(129), BigDecimal.valueOf(299), BigDecimal.valueOf(319), BigDecimal.valueOf(359), BigDecimal.valueOf(469), BigDecimal.valueOf(3000)),
					1.8, "./cars/opel-astra.jpg", "Opel", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.BENZYNA, 2017, 200, 7));
			CarDTO carDTO18 = carService.addCar(new CarDTO(23L, "Megane", "BI 2724F", 101, 190, 5.0, new Price(23L, BigDecimal.valueOf(109), BigDecimal.valueOf(129), BigDecimal.valueOf(299), BigDecimal.valueOf(319), BigDecimal.valueOf(359), BigDecimal.valueOf(469), BigDecimal.valueOf(3000)),
					1.8, "./cars/renault-megane-cennik-2021-otwarcie.jpg", "Renault", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2018, 200, 5));
			CarDTO carDTO17 = carService.addCar(new CarDTO(24L, "Qashqai", "BI 1894A", 165, 202, 5.7, new Price(24L, BigDecimal.valueOf(199), BigDecimal.valueOf(299), BigDecimal.valueOf(499), BigDecimal.valueOf(599), BigDecimal.valueOf(799), BigDecimal.valueOf(1099), BigDecimal.valueOf(5000)),
					2.4, "./cars/modele-nissana-1-qashqai.jpg", "Nissan", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.DIESEL, 2016, 250, 4));
			CarDTO carDTO16 = carService.addCar(new CarDTO(25L, "LS500h", "BI 7930H", 380, 470, 14.7, new Price(25L, BigDecimal.valueOf(999), BigDecimal.valueOf(1199), BigDecimal.valueOf(1399), BigDecimal.valueOf(1599), BigDecimal.valueOf(1799), BigDecimal.valueOf(2099), BigDecimal.valueOf(30000)),
					3.0, "./cars/Lexus_LS_500h-35.jpg", "Lexus", Transmission.MANUALNA, "poduszki", Category.EKSKLUZYWNE, Petrol.HYBRID, 2022, 500, 3));
			CarDTO carDTO15 = carService.addCar(new CarDTO(26L, "M8", "WN 4434A", 670, 850, 18.0, new Price(26L, BigDecimal.valueOf(999), BigDecimal.valueOf(1199), BigDecimal.valueOf(1399), BigDecimal.valueOf(1599), BigDecimal.valueOf(1799), BigDecimal.valueOf(2099), BigDecimal.valueOf(20000)),
					6.0, "./cars/bmw-m8-gc-4-71fcbe29a87b88df7e02.webp", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2018, 500, 4));
			CarDTO carDTO14 = carService.addCar(new CarDTO(27L, "SLS AMG", "BI 60943", 745, 920, 20.0, new Price(27L, BigDecimal.valueOf(7000), BigDecimal.valueOf(7599), BigDecimal.valueOf(7999), BigDecimal.valueOf(8999), BigDecimal.valueOf(9999), BigDecimal.valueOf(10999), BigDecimal.valueOf(200000)),
					6.6, "./cars/940x520_najlepsze-i-niezawodne-tanie-samochody-sportowe-2022-z-cenami-i-zdjeciami-whrm.jpg", "Mercedes-Benz", Transmission.AUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.BENZYNA, 2019, 700, 5));
			CarDTO carDTO13 = carService.addCar(new CarDTO(28L, "NSX", "BI 5X34B", 690, 760, 9.9, new Price(28L, BigDecimal.valueOf(7000), BigDecimal.valueOf(7599), BigDecimal.valueOf(7999), BigDecimal.valueOf(8999), BigDecimal.valueOf(9999), BigDecimal.valueOf(10999), BigDecimal.valueOf(200000)),
					3.2, "./cars/f803947a5cce71f6e93e421a29021cb4.jpeg", "Honda", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SPORTOWE, Petrol.ELEKTRYCZNY, 2021, 450, 6));
			CarDTO carDTO12 = carService.addCar(new CarDTO(29L, "7", "BI 50345", 320, 456, 7.9, new Price(29L, BigDecimal.valueOf(999), BigDecimal.valueOf(1199), BigDecimal.valueOf(1399), BigDecimal.valueOf(1599), BigDecimal.valueOf(1799), BigDecimal.valueOf(2099), BigDecimal.valueOf(70000)),
					4.5, "./cars/bmw-7-test-41.jpg", "BMW", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.EKSKLUZYWNE, Petrol.DIESEL, 2019, 350, 6));
			CarDTO carDTO11 = carService.addCar(new CarDTO(30L, "Arona", "BI 901GF", 190, 230, 6.8, new Price(30L, BigDecimal.valueOf(199), BigDecimal.valueOf(299), BigDecimal.valueOf(499), BigDecimal.valueOf(599), BigDecimal.valueOf(799), BigDecimal.valueOf(1099), BigDecimal.valueOf(5000)),
					2.5, "./cars/maxresdefault.jpg", "Seat", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2018, 250, 5));
			CarDTO carDTO9 = carService.addCar(new CarDTO(31L, "Panda", "BI 4YH2K", 80, 156, 4.0, new Price(31L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(1000)),
					1.4, "./cars/fiat-panda-2020-5.jpg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2019, 200, 4));
			CarDTO carDTO10 = carService.addCar(new CarDTO(32L, "500", "WE 87891", 80, 145, 4.0, new Price(32L, BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(1000)),
					1.6, "./cars/dcbdf686d2681cd6e3989f9127a994f2.jpg", "Fiat", Transmission.AUTOMATYCZNA, "poduszki", Category.EKONOMICZNE, Petrol.ELEKTRYCZNY, 2018, 200, 3));
			CarDTO carDTO8 = carService.addCar(new CarDTO(33L, "C3", "BI 200A8", 120, 200, 4.9, new Price(33L, BigDecimal.valueOf(199), BigDecimal.valueOf(299), BigDecimal.valueOf(499), BigDecimal.valueOf(599), BigDecimal.valueOf(799), BigDecimal.valueOf(1099), BigDecimal.valueOf(5000)),
					2.0, "./cars/citroen-c3-cennik-2022-otwarcie.jpg", "Citroen", Transmission.MANUALNA, "poduszki", Category.EKONOMICZNE, Petrol.HYBRID, 2017, 200, 4));
			CarDTO carDTO7 = carService.addCar(new CarDTO(34L, "3", "BI 67167", 200, 280, 8.8, new Price(34L, BigDecimal.valueOf(809), BigDecimal.valueOf(899), BigDecimal.valueOf(1199), BigDecimal.valueOf(1299), BigDecimal.valueOf(1599), BigDecimal.valueOf(1899), BigDecimal.valueOf(15000)),
					2.5, "./cars/2023-bmw-3-series-14-ed5a2154052.webp", "BMW", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2015, 300, 5));
			CarDTO carDTO6 = carService.addCar(new CarDTO(35L, "X5", "BI 2214P", 234, 357, 9.8, new Price(35L, BigDecimal.valueOf(1099), BigDecimal.valueOf(1299), BigDecimal.valueOf(1499), BigDecimal.valueOf(1699), BigDecimal.valueOf(1899), BigDecimal.valueOf(2099), BigDecimal.valueOf(18000)),
					2.5, "./cars/5cdacb7c57502a333d006989.jpg", "BMW", Transmission.AUTOMATYCZNA, "poduszki", Category.SUV, Petrol.BENZYNA, 2016, 350, 6));
      CarDTO carDTO106 =carService.addCar(new CarDTO(36L, "RX450h", "WA 92341", 200, 305, 6.1, new Price(36L,BigDecimal.valueOf(1099), BigDecimal.valueOf(1299), BigDecimal.valueOf(1499), BigDecimal.valueOf(1699), BigDecimal.valueOf(1899), BigDecimal.valueOf(2099), BigDecimal.valueOf(18000)),
            2.0, "./cars/LexusRX450hFSport(IV)Frontansicht,14.Februar_2016,Düsseldorf.jpg", "Lexus", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.HYBRID, 2018, 300, 4));
			CarDTO carDTO4 = carService.addCar(new CarDTO(37L, "A5", "WT 29801", 256, 489, 8.1, new Price(37L, BigDecimal.valueOf(809), BigDecimal.valueOf(899), BigDecimal.valueOf(1199), BigDecimal.valueOf(1299), BigDecimal.valueOf(1599), BigDecimal.valueOf(1899), BigDecimal.valueOf(12000)),
					3.0, "./cars/audi_a5_sportback_photo_04.jpg", "Audi", Transmission.MANUALNA, "poduszki", Category.KOMFORTOWE, Petrol.BENZYNA, 2019, 300, 5));
			CarDTO carDTO5 = carService.addCar(new CarDTO(38L, "Transit", "WD 24090", 160, 215, 9.0, new Price(38L, BigDecimal.valueOf(899), BigDecimal.valueOf(999), BigDecimal.valueOf(1099), BigDecimal.valueOf(1199), BigDecimal.valueOf(1399), BigDecimal.valueOf(11599), BigDecimal.valueOf(10000)),
					2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4));
			CarDTO carDTO3 = carService.addCar(new CarDTO(39L, "Boxer", "BI 245TR", 160, 220, 8.9, new Price(39L, BigDecimal.valueOf(899), BigDecimal.valueOf(999), BigDecimal.valueOf(1099), BigDecimal.valueOf(1199), BigDecimal.valueOf(1399), BigDecimal.valueOf(11599), BigDecimal.valueOf(10000))
					, 2.5, "./cars/35-peugeot-boxer-euro6.jpg", "Peugeot", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2021, 200, 3));
			CarDTO carDTO2 = carService.addCar(new CarDTO(40L, "Crafter", "BI 1167Y", 140, 205, 8.8, new Price(40L, BigDecimal.valueOf(899), BigDecimal.valueOf(999), BigDecimal.valueOf(1099), BigDecimal.valueOf(1199), BigDecimal.valueOf(1399), BigDecimal.valueOf(11599), BigDecimal.valueOf(10000)),
					2.0, "./cars/VW_Crafter_IMG_0772.jpg", "Volkswagen", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.BENZYNA, 2022, 200, 4));
			CarDTO carDTO1 = carService.addCar(new CarDTO(41L, "RAV4", "BI 9080M", 255, 300, 6.6, new Price(41L, BigDecimal.valueOf(899), BigDecimal.valueOf(999), BigDecimal.valueOf(1099), BigDecimal.valueOf(1199), BigDecimal.valueOf(1399), BigDecimal.valueOf(11599), BigDecimal.valueOf(10000))
					, 2.0, "./cars/toyota-rav4-cennik-2022-otwarcie.jpg", "Toyota", Transmission.PÓŁAUTOMATYCZNA, "poduszki", Category.SUV, Petrol.ELEKTRYCZNY, 2022, 250, 5));
			CarDTO carDTO = carService.addCar(new CarDTO(42L, "Corolla", "WE 5421G", 280, 345, 7.7, new Price(42L, BigDecimal.valueOf(1099), BigDecimal.valueOf(1299), BigDecimal.valueOf(1499), BigDecimal.valueOf(1699), BigDecimal.valueOf(1899), BigDecimal.valueOf(2099), BigDecimal.valueOf(18000))
					, 2.2, "./cars/toyota-corolla-sedan-exterior-04-full_tcm-1015-1858165.jpg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250, 4));


			additionalService.addAdditional(new AdditionalDTO(1L, "Każdy kolejny dzień spóźnienia oddania samochodu", BigDecimal.valueOf(500)));
			additionalService.addAdditional(new AdditionalDTO(2L, "Wypożyczenie fotelika dziecięcego", BigDecimal.valueOf(100)));
			additionalService.addAdditional(new AdditionalDTO(3L, "Wydanie zgody na wyjazd poza granice Polski - samochody osobowe", BigDecimal.valueOf(400)));
			additionalService.addAdditional(new AdditionalDTO(4L, "Dodanie drugiego uprawnionego kierowcy", BigDecimal.valueOf(200)));
			additionalService.addAdditional(new AdditionalDTO(5L, "Zniesienie udziału własnego w szkodzie - pakiet podstawowy", BigDecimal.valueOf(500)));
			additionalService.addAdditional(new AdditionalDTO(6L, "Zniesienie udziału własnego w szkodzie - pakiet normalny", BigDecimal.valueOf(1000)));
			additionalService.addAdditional(new AdditionalDTO(7L, "Zniesienie udziału własnego w szkodzie - pakiet rozszerzony", BigDecimal.valueOf(1500)));
			additionalService.addAdditional(new AdditionalDTO(8L, "Odbiór lub zwrot pojazdu z adresu klienta", BigDecimal.valueOf(150)));
			additionalService.addAdditional(new AdditionalDTO(9L, "Mycie samochodu", BigDecimal.valueOf(100)));
			additionalService.addAdditional(new AdditionalDTO(10L, "Sprzątanie samochodu w środku", BigDecimal.valueOf(100)));
//			additionalService.addAdditional(new AdditionalDTO(11L, "Ładowanie auta elektrycznego", BigDecimal.valueOf(10)));
			userService.addWorker(new UserDTO(1L, "Jan", "Kowalski", "macieksob25@gmail.com", "15-878", "Bialystok", "514 546 610", "Boczna", "11", 76, "Kowalek1234?", "67328302379",
					LocalDateTime.now(), LocalDate.of(1984, 11, 12), "fgdgdfg", "dfgdfgdf", true, new Card(), Set.of(new Role("ROLE_ADMIN"))));
			UserDTO userDTO = userService.addUser(new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "45678765451",
					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, new Card(), Set.of(new Role("ROLE_USER"))));
			userService.addUser(new UserDTO(3L, "Daniel", "Nowak", "d.nowak@gmail.com", "15-255", "Bialystok", "334 676 222", "Botaniczna", "2", 1, "dNowak22!", "54856834213",
					LocalDateTime.now(), LocalDate.of(1995, 4, 2), "fgdgdfg", "dfgdfgdf", true, new Card(), Set.of(new Role("ROLE_WORKER"))));
//			orderService.addOrder(new OrderDTO(1L, LocalDate.of(2022,6,15),LocalDate.of(2022,7,8),32,Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Price(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//					, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250,4)) ,
//					new User(),BigDecimal.valueOf(354.6), Place.FIRST, Place.SECOND,30);
//		};	orderService.addOrder(new OrderDTO(1L, LocalDate.of(2022,6,15),LocalDate.of(2022,7,8),32,Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Price(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//				, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250,4)),new User(),BigDecimal.valueOf(3435.7), Place.FIRST, Place.SECOND, 30));
//			orderService.addOrder(new OrderDTO(1L, LocalDate.of(2022,6,15), LocalDate.of(2022,6,18),32,new UserDTO(1L, "Maciek", "Sobolewski", "macieksob25@gmail.com", "15-354", "Bialystok", "514 774 710", "Pogodna", "11", 32, "Sobolek2017?", "00321302379",
//					LocalDateTime.now(), LocalDate.of(200, 12, 12), "fgdgdfg", "dfgdfgdf", true, 0),Place.FIRST, Place.SECOND,30, Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Price(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//					, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250, 4))));
//			System.out.println(userService.findByPassword("Sobolek2017?"));
//			fullOrderService.addFullOrder(new FullOrderDTO(1L,Set.of(new OrderDTO(1L, LocalDate.of(2022,12,7), LocalDate.of(2022,12,12),Place.FIRST, Place.SECOND,1044,carService.addCar(new CarDTO(40L, "Crafter", "BI6734T", 140, 205, 8.8, new Price(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//					2.0, "./cars/VW_Crafter_IMG_0772.jpg", "Volkswagen", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.BENZYNA, 2022, 200, 4))),new OrderDTO(2L, LocalDate.of(2022,6,12), LocalDate.of(2022,5,28),Place.FIRST, Place.SECOND,1000,carService.addCar(carService.addCar(new CarDTO(36L, "RX450h", "BI6734T", 200, 305, 6.1, new Price(BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//					2.0, "./cars/LexusRX450hFSport(IV)Frontansicht,14.Februar_2016,Düsseldorf.jpg", "Lexus", Transmission.MANUALNA, "poduszki", Category.SUV, Petrol.HYBRID, 2018, 300, 4))))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true,new Card(),Set.of(new Role("ROLE_ADMIN"))),new BigDecimal(2044)));
//			fullOrderService.addFullOrder(new FullOrderDTO(2L,Set.of(new OrderDTO(1L, LocalDate.of(2022,6,15), LocalDate.of(2022,6,18),32,new UserDTO(1L, "Maciek", "Sobolewski", "macieksob25@gmail.com", "15-354", "Bialystok", "514 774 710", "Pogodna", "11", 32, "Sobolek2017?", "00321302379",
//					LocalDateTime.now(), LocalDate.of(200, 12, 12), "fgdgdfg", "dfgdfgdf", true, Set.of(new Role("ROLE_WORKER"))),Place.FIRST, Place.SECOND,30,Set.of(new CarDTO(42L, "Corolla", "BI6734T", 280, 345, 7.7, new Price(42L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300))
//					, 2.2, "hfghfg", "Toyota", Transmission.AUTOMATYCZNA, "poduszki", Category.KOMFORTOWE, Petrol.DIESEL, 2020, 250, 4)))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, Set.of(new Role("ROLE_WORKER")))));
contactService.addContact(new ContactDTO("Mac","Sob","macieksob@gmail.com","654666444","Witam"));
			contactService.addContact(new ContactDTO("Edward","Wolski","ed.wolski@gmail.com","533221112","Poproszę o"));
			contactService.addContact(new ContactDTO("Kamila","Bielska","kambil12@gmail.com","523124445","Dzień dobry"));
//			OrderDTO e1 = new OrderDTO(1L, LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 8), Place.FIRST, Place.SECOND, 54432, carDTO4);
//			OrderDTO e2 = new OrderDTO(2L, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 12), Place.FIRST, Place.SECOND, 44323, carDTO3);
//			OrderDTO e3 = new OrderDTO(3L, LocalDate.of(2023, 1, 2), LocalDate.of(2023, 1, 4), Place.FIRST, Place.SECOND, 78767, carDTO2);
//			OrderDTO e4 = new OrderDTO(4L, LocalDate.of(2023, 2, 5), LocalDate.of(2023, 2, 8), Place.FIRST, Place.SECOND, 23456, carDTO25);
//			OrderDTO e5 = new OrderDTO(5L, LocalDate.of(2023, 6, 5), LocalDate.of(2023, 6, 8), Place.FIRST, Place.SECOND, 45677, carDTO21);
//			OrderDTO e6 = new OrderDTO(6L, LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 8), Place.FIRST, Place.SECOND, 66899, carDTO14);
//			OrderDTO e7 = new OrderDTO(7L, LocalDate.of(2023, 4, 5), LocalDate.of(2023, 4, 8), Place.FIRST, Place.SECOND, 76783, carDTO16);
//			OrderDTO e8 = new OrderDTO(8L, LocalDate.of(2023, 5, 5), LocalDate.of(2023, 5, 8), Place.FIRST, Place.SECOND, 43454, carDTO19);
//			OrderDTO e9 = new OrderDTO(9L, LocalDate.of(2023, 5, 5), LocalDate.of(2023, 5, 8), Place.FIRST, Place.SECOND, 12434, carDTO21);
//			OrderDTO e10 = new OrderDTO(10L, LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 8), Place.FIRST, Place.SECOND, 45666, carDTO7);
			fullOrderService.addFullOrder(new FullOrderDTO(1L,Set.of(new OrderDTO(1L, LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 8), Place.FIRST, Place.SECOND, 54432, carDTO4)),userDTO,new BigDecimal(45677)));
			fullOrderService.addFullOrder(new FullOrderDTO(2L,Set.of(new OrderDTO(2L, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 12), Place.FIRST, Place.SECOND, 44323, carDTO3)),userDTO,new BigDecimal(44323)));
			fullOrderService.addFullOrder(new FullOrderDTO(3L,Set.of(new OrderDTO(3L, LocalDate.of(2023, 1, 2), LocalDate.of(2023, 1, 4), Place.FIRST, Place.SECOND, 78767, carDTO2)),userDTO,new BigDecimal(78767)));
			fullOrderService.addFullOrder(new FullOrderDTO(4L,Set.of(new OrderDTO(4L, LocalDate.of(2023, 2, 5), LocalDate.of(2023, 2, 8), Place.FIRST, Place.SECOND, 23456, carDTO25)),userDTO,new BigDecimal(23456)));
			fullOrderService.addFullOrder(new FullOrderDTO(5L,Set.of(new OrderDTO(5L, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 8), Place.FIRST, Place.SECOND, 45677, carDTO21)),userDTO,new BigDecimal(45677)));
			fullOrderService.addFullOrder(new FullOrderDTO(6L,Set.of(new OrderDTO(6L, LocalDate.of(2023, 4, 4), LocalDate.of(2023, 4, 18), Place.FIRST, Place.SECOND, 55455, carDTO14)),userDTO,new BigDecimal(55455)));
			fullOrderService.addFullOrder(new FullOrderDTO(7L,Set.of(new OrderDTO(7L, LocalDate.of(2023, 1, 16), LocalDate.of(2023, 1, 18), Place.FIRST, Place.SECOND, 43245, carDTO13)),userDTO,new BigDecimal(43245)));
			fullOrderService.addFullOrder(new FullOrderDTO(8L,Set.of(new OrderDTO(8L, LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 18), Place.FIRST, Place.SECOND, 4367, carDTO16)),userDTO,new BigDecimal(4367)));
			fullOrderService.addFullOrder(new FullOrderDTO(9L,Set.of(new OrderDTO(9L, LocalDate.of(2023, 6, 8), LocalDate.of(2023, 6, 28), Place.FIRST, Place.SECOND, 8686, carDTO12)),userDTO,new BigDecimal(8686)));
			fullOrderService.addFullOrder(new FullOrderDTO(10L,Set.of(new OrderDTO(10L, LocalDate.of(2023, 8, 9), LocalDate.of(2023, 8, 11), Place.FIRST, Place.SECOND, 67866, carDTO11)),userDTO,new BigDecimal(67866)));
			fullOrderService.addFullOrder(new FullOrderDTO(11L,Set.of(new OrderDTO(11L, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 16), Place.FIRST, Place.SECOND, 34565, carDTO22)),userDTO,new BigDecimal(34565)));
			fullOrderService.addFullOrder(new FullOrderDTO(12L,Set.of(new OrderDTO(12L, LocalDate.of(2023, 2, 11), LocalDate.of(2023, 2, 19), Place.FIRST, Place.SECOND, 3432, carDTO27)),userDTO,new BigDecimal(3432)));
			fullOrderService.addFullOrder(new FullOrderDTO(13L,Set.of(new OrderDTO(13L, LocalDate.of(2023, 4, 14), LocalDate.of(2023, 4, 21), Place.FIRST, Place.SECOND, 22344, carDTO28)),userDTO,new BigDecimal(22344)));
			fullOrderService.addFullOrder(new FullOrderDTO(14L,Set.of(new OrderDTO(14L, LocalDate.of(2023, 5, 26), LocalDate.of(2023, 5, 29), Place.FIRST, Place.SECOND, 5467, carDTO1)),userDTO,new BigDecimal(5467)));
			fullOrderService.addFullOrder(new FullOrderDTO(15L,Set.of(new OrderDTO(15L, LocalDate.of(2023, 7, 28), LocalDate.of(2023, 7, 30), Place.FIRST, Place.SECOND, 89890, carDTO30)),userDTO,new BigDecimal(89890)));
			fullOrderService.addFullOrder(new FullOrderDTO(16L,Set.of(new OrderDTO(16L, LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 22), Place.FIRST, Place.SECOND, 42454, carDTO31)),userDTO,new BigDecimal(42454)));
			fullOrderService.addFullOrder(new FullOrderDTO(17L,Set.of(new OrderDTO(17L, LocalDate.of(2023, 10, 19), LocalDate.of(2023, 10, 21), Place.FIRST, Place.SECOND, 13423, carDTO33)),userDTO,new BigDecimal(13423)));
			fullOrderService.addFullOrder(new FullOrderDTO(18L,Set.of(new OrderDTO(18L, LocalDate.of(2023, 1, 17), LocalDate.of(2023, 1, 19), Place.FIRST, Place.SECOND, 43545, carDTO34)),userDTO,new BigDecimal(43545)));
			fullOrderService.addFullOrder(new FullOrderDTO(19L,Set.of(new OrderDTO(19L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 5), Place.FIRST, Place.SECOND, 23423, carDTO35)),userDTO,new BigDecimal(23423)));
			fullOrderService.addFullOrder(new FullOrderDTO(20L,Set.of(new OrderDTO(20L, LocalDate.of(2023, 1, 3), LocalDate.of(2023, 1, 6), Place.FIRST, Place.SECOND, 12344, carDTO36)),userDTO,new BigDecimal(12344)));
			fullOrderService.addFullOrder(new FullOrderDTO(21L,Set.of(new OrderDTO(21L, LocalDate.of(2023, 1, 4), LocalDate.of(2023, 1, 7), Place.FIRST, Place.SECOND, 66899, carDTO37)),userDTO,new BigDecimal(66899)));
			fullOrderService.addFullOrder(new FullOrderDTO(22L,Set.of(new OrderDTO(22L, LocalDate.of(2023, 2, 5), LocalDate.of(2023, 2, 8), Place.FIRST, Place.SECOND, 34666, carDTO38)),userDTO,new BigDecimal(34666)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(23L,Set.of(new OrderDTO(23L, LocalDate.of(2023, 2, 22), LocalDate.of(2023, 2, 24), Place.FIRST, Place.SECOND, 25677, carDTO23)),userDTO,new BigDecimal(25677)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(24L,Set.of(new OrderDTO(24L, LocalDate.of(2023, 3, 21), LocalDate.of(2023, 3, 25), Place.FIRST, Place.SECOND, 76881, carDTO29)),userDTO,new BigDecimal(76881)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(25L,Set.of(new OrderDTO(25L, LocalDate.of(2023, 3, 11), LocalDate.of(2023, 3, 18), Place.FIRST, Place.SECOND, 39886, carDTO26)),userDTO,new BigDecimal(39886)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(26L,Set.of(new OrderDTO(26L, LocalDate.of(2023, 3, 13), LocalDate.of(2023, 3, 18), Place.FIRST, Place.SECOND, 23467, carDTO20)),userDTO,new BigDecimal(23467)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(27L,Set.of(new OrderDTO(27L, LocalDate.of(2023, 11, 12), LocalDate.of(2023, 11, 16), Place.FIRST, Place.SECOND, 91654, carDTO9)),userDTO,new BigDecimal(91654)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(28L,Set.of(new OrderDTO(28L, LocalDate.of(2023, 5, 15), LocalDate.of(2023, 5, 19), Place.FIRST, Place.SECOND, 87962, carDTO15)),userDTO,new BigDecimal(87962)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(29L,Set.of(new OrderDTO(29L, LocalDate.of(2023, 5, 17), LocalDate.of(2023, 5, 20), Place.FIRST, Place.SECOND, 34231, carDTO19)),userDTO,new BigDecimal(34231)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(30L,Set.of(new OrderDTO(30L, LocalDate.of(2023, 7, 18), LocalDate.of(2023, 7, 21), Place.FIRST, Place.SECOND, 65322, carDTO7)),userDTO,new BigDecimal(65322)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(31L,Set.of(new OrderDTO(31L, LocalDate.of(2023, 7, 19), LocalDate.of(2023, 7, 22), Place.FIRST, Place.SECOND, 35655, carDTO8)),userDTO,new BigDecimal(35655)));
// 			fullOrderService.addFullOrder(new FullOrderDTO(32L,Set.of(new OrderDTO(32L, LocalDate.of(2023, 7, 28), LocalDate.of(2023, 7, 30), Place.FIRST, Place.SECOND, 76453, carDTO17)),userDTO,new BigDecimal(76453)));
//			fullOrderService.addFullOrder(new FullOrderDTO(2L,Set.of(e2,e3),userDTO1,new BigDecimal(123090)));
//			fullOrderService.addFullOrder(new FullOrderDTO(3L,Set.of(e4),userDTO1,new BigDecimal(23456)));
//			fullOrderService.addFullOrder(new FullOrderDTO(4L,Set.of(e5),userDTO1,new BigDecimal(45677)));
////			fullOrderService.addFullOrder(new FullOrderDTO(5L,Set.of(e6),userDTO,new BigDecimal(66899)));
//			fullOrderService.addFullOrder(new FullOrderDTO(5L,Set.of(e7),userDTO1,new BigDecimal(76783)));
//			fullOrderService.addFullOrder(new FullOrderDTO(6L,Set.of(e8),userDTO1,new BigDecimal(43454)));
//			fullOrderService.addFullOrder(new FullOrderDTO(7L,Set.of(e9),userDTO,new BigDecimal(12434)));
//			fullOrderService.addFullOrder(new FullOrderDTO(8L,Set.of(e10),userDTO1,new BigDecimal(45666)));
//			fullOrderService.addFullOrder(new FullOrderDTO(2L,Set.of(new OrderDTO(2L, LocalDate.of(2022,12,21), LocalDate.of(2022,12,31),Place.FIRST, Place.SECOND,34554,carService.addCar(new CarDTO(38L, "Transit", "BI6734T", 160, 215, 9.0, new Price(38L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),
//					2.6, "./cars/2020_ford_transit-passenger-van_passenger-van_350-hd-xlt-high-roof_fq_oem_1_1280.jpg", "Ford", Transmission.AUTOMATYCZNA, "poduszki", Category.DOSTAWCZE, Petrol.DIESEL, 2020, 200, 4)))),new UserDTO(2L, "Diana", "Kolska", "dianakol@gmail.com", "15-546", "Bialystok", "647 676 888", "Waryńskiego", "4", 22, "Dianakol1234!", "00989889984",
//					LocalDateTime.now(), LocalDate.of(1998, 3, 11), "fgdgdfg", "dfgdfgdf", true, new Card(),Set.of(new Role("ROLE_ADMIN"))),new BigDecimal(34554)));
			
		};
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
