package pl.macieksob.rentCar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.macieksob.rentCar.model.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCarTest(){
        Car car = new Car(4L, "E 200", "BI6734T",  new Price(1L,BigDecimal.valueOf(89), BigDecimal.valueOf(129), BigDecimal.valueOf(199), BigDecimal.valueOf(219), BigDecimal.valueOf(259), BigDecimal.valueOf(269), BigDecimal.valueOf(300)),300, 400, 8.4,
                5.6, "hfghfg", "Mercedes-Benz", Transmission.AUTOMATYCZNA,  Category.EKSKLUZYWNE, Petrol.ELEKTRYCZNY, 2018, 300,"poduszki",4);

        carRepository.save(car);

        assertThat(car.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteCarTest(){
        Car car = carRepository.findById(1L).get();

        carRepository.delete(car);

        Car car2 = null;

        Optional<Car> opCar =  carRepository.findByKm(677);

        if(opCar.isPresent()){
            car2 = opCar.get();
        }

        assertThat(car2).isNull();
    }

    @Test
    public void getCarTest(){
        Car car = carRepository.findById(4L).get();

        assertThat(car.getId()).isEqualTo(4L);
    }

    @Test
    public void getCarListTest(){
        List<Car> all = carRepository.findAll();

        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    public void updateCarTest() {
        Car car = carRepository.findById(1L).get();

        car.setKm(677);

        Car save = carRepository.save(car);

        assertThat(save.getKm()).isEqualTo(677);
    }
}
