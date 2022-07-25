package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Petrol;
import pl.macieksob.rentCar.model.Transmission;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository< Car, Long> {

    java.util.List<Car> findAllByModel(String model, Pageable pageable);
    java.util.List<Car> findAllByBrand(String brand,Pageable pageable);
    java.util.List<Car> findAllByKm(Integer km,Pageable pageable);

    List< Car> findAllByTransmission(Transmission transmission,Pageable pageable);

    List< Car> findAllByNm(Integer nm,Pageable pageable);
    List< Car> findAllByCategory(String category,Pageable pageable);

    @Query(nativeQuery = true, value = "gdgf")
    List< Car> findAllByStartDateAndEndDateAndPLace(String category,Pageable pageable);

    List< Car> findAllByPrize(BigDecimal prize,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,String engine,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndEngine(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndEngineAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKmAndEngineAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Pageable pageable);
//    List< Car> findAllByEngineAndModelAndBrandAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Pageable pageable);
//    List< Car> findAllByPrizeAndEngineAndBrandAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYear(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km, Transmission transmission,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndKm(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrand(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndModel(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByEngineAndModel(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllBrandAndModel(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByKmAndModel(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByTransmissionAndModel(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByYearAndModel(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByBrandAndEngine(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByEngineAndKm(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByEngineAndTransmission(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByYearAndAndEngine(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPetrolAndEngine(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByBrandAndKM(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByBrandAndTransmission(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByBrandAndYear(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByBrandAndPetrol(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByKmAndTransmission(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByKmAndYear(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByKmAndPetrol(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByTransmissionAndYear(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByTransmissionAndPetrol(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByYearAndPetrol(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngine(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndKm(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndTransmission(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndYear(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndPetrol(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndEngineAndBrand(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndEngineAndKm(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndEngineAndTransmission(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndEngineAndYear(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndEngineAndPetrol(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndKmAndBrand(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndTransmissionAndBrand(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndYearAndBrand(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndPetrolAndBrand(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndKmAndTransmission(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndKmAndYear(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndKmAndPetrol(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndTransmissionAndYear(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndTransmissionAndPetrol(BigDecimal prize, String model, String brand,Pageable pageable);
//    List< Car> findAllByPrizeAndYearAndPetrol(BigDecimal prize, String model, String brand,Pageable pageable);
    List< Car> findAllByYear(Integer year,Pageable pageable);
    List< Car> findAllByPetrol(Petrol petrol,Pageable pageable);
    List< Car> findAllByEngine(Double engine,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndKm(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndYear(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndPetrol(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndYear(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndPetrol(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByYearAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPetrolAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndPetrolAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndYearAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndYearAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndPetrolAndYearAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndPetrolAndPetrolAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndYearAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndPetrolAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//
//
//    List< Car> findAllByPrizeAndYear(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPrizeAndPetrol(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPrizeAndEngine(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPrizeAndKm(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPrizeAndBrand(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPrizeAndTransmission(BigDecimal prize, String model,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndTransmission(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndYear(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndPetrol(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);
//    List< Car> findAllByPrizeAndModelAndBrandAndEngine(BigDecimal prize, String model, String brand, Integer km,Pageable pageable);



    @Query(nativeQuery = true,value = "select * from CARS c where c.model iLIKE  %:keyword%  or c.brand iLIKE  %:keyword% or c.km iLIKE  %:keyword% or c.nm iLIKE  %:keyword% " +
            "or c.petrol iLIKE  %:keyword% or c.engine iLIKE  %:keyword% or c.transmission iLIKE  %:keyword% or c.category iLIKE  %:keyword%")
    List<Car> findAllByKeyword(String keyword,Pageable pageable);

}
