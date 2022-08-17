package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Category;
import pl.macieksob.rentCar.model.Petrol;
import pl.macieksob.rentCar.model.Transmission;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository< Car, Long> {

    java.util.List<Car> findAllByModel(String model);
    java.util.List<Car> findAllByBrand(String brand);
    java.util.List<Car> findAllByKm(Integer km);

    List< Car> findAllByTransmission(Transmission transmission);

    List< Car> findAllByNmAndTaken(Integer nm,boolean taken);
    List< Car> findAllByCategory(Category category);

    @Query(nativeQuery = true, value = "gdgf")
    List< Car> findAllByStartDateAndEndDateAndPLace(String category);

    List< Car> findAllByPrize(BigDecimal prize);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol,Double engine);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndEngine(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndEngineAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndEngineAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByEngineAndModelAndBrandAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndEngineAndBrandAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndEngineAndEngineAndTransmissionAndYearAndPetrol(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year,Petrol petrol);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYear(BigDecimal prize, String model, String brand, Integer km, Transmission transmission, Integer year);
    List< Car> findAllByPrizeAndModelAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km, Transmission transmission);
    List< Car> findAllByPrizeAndModelAndBrandAndKm(BigDecimal prize, String model, String brand, Integer km);
    List< Car> findAllByPrizeAndModelAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModel(BigDecimal prize, String model);
//    List< Car> findAllByEngineAndModel(BigDecimal prize, String model);
//    List< Car> findAllBrandAndModel(BigDecimal prize, String model);
//    List< Car> findAllByKmAndModel(BigDecimal prize, String model);
//    List< Car> findAllByTransmissionAndModel(BigDecimal prize, String model);
//    List< Car> findAllByYearAndModel(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByEngineAndKm(BigDecimal prize, String model);
//    List< Car> findAllByEngineAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByYearAndAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByPetrolAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndKM(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndYear(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByKmAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByKmAndYear(BigDecimal prize, String model);
//    List< Car> findAllByKmAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByTransmissionAndYear(BigDecimal prize, String model);
//    List< Car> findAllByTransmissionAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByYearAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndModelAndEngine(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndKm(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndTransmission(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndModelAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndKm(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndTransmission(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndEngineAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndTransmissionAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndYearAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndPetrolAndBrand(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndTransmission(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndKmAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndTransmissionAndYear(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndTransmissionAndPetrol(BigDecimal prize, String model, String brand);
//    List< Car> findAllByPrizeAndYearAndPetrol(BigDecimal prize, String model, String brand);
    List< Car> findAllByYear(Integer year);
    List< Car> findAllByPetrol(Petrol petrol);
    List< Car> findAllByEngine(Double engine);
//    List< Car> findAllByPrizeAndModelAndEngineAndKm(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndYear(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndPetrol(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndYear(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndEngineAndBrandAndKmAndPetrol(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByYearAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPetrolAndModelAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndPetrolAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndYearAndEngineAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndYearAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndYearAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndPetrolAndKmAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndYearAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndPetrolAndBrandAndPetrolAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//
//
//    List< Car> findAllByPrizeAndYear(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndKm(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndBrand(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByPrizeAndModelAndBrandAndTransmission(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndBrandAndYear(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndBrandAndPetrol(BigDecimal prize, String model, String brand, Integer km);
//    List< Car> findAllByPrizeAndModelAndBrandAndEngine(BigDecimal prize, String model, String brand, Integer km);



    @Query(nativeQuery = true,value = "select * from CARS c where c.model iLIKE  %:keyword%  or c.brand iLIKE  %:keyword% or c.km iLIKE  %:keyword% or c.nm iLIKE  %:keyword% " +
            "or c.petrol iLIKE  %:keyword% or c.engine iLIKE  %:keyword% or c.transmission iLIKE  %:keyword% or c.category iLIKE  %:keyword%")
    List<Car> findAllByKeyword(String keyword);



}
