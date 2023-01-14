package pl.macieksob.rentCar.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository< Car, Long> {

    java.util.List<Car> findAllByModel(String model);

    java.util.List<Car> findAllByBrand(String brand);
    java.util.List<Car> findAllByKm(Integer km);
    List<Car> findAllByTaken(boolean b);

    List< Car> findAllByTransmission(Transmission transmission);

    List< Car> findAllByNmAndTaken(Integer nm,boolean taken);
    List< Car> findAllByCategory(Category category);


    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  C.RENTINGS = (SELECT MAX(RENTINGS) FROM CARS) AND DATE(SYSDATE()) - ?1 > O.START_DATE")
    List<Long> findMostRentedCar(Integer days);

    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  DATE(SYSDATE()) - ?1 > O.START_DATE")
    List<Long> findCarsByLastDays( Integer days);

    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  C.RENTINGS = (SELECT MAX(RENTINGS) FROM CARS) AND DATE(SYSDATE()) - ?1 > O.START_DATE")
    List<Long> findMostRentedCarBrand(Integer days);

    @Query(nativeQuery = true, value = "SELECT C.ID FROM ORDERS O, CARS C WHERE O.CAR_ID = C.ID AND  DATE(SYSDATE()) - ?1 > O.START_DATE")
    List<Long> findCarsByLastDaysBrand( Integer days);


//    List<Car> findAllByOpenedAndFaultIsNotNull(Boolean opened);

//    @Query(nativeQuery = true, value = "")
//    List<Long> findCarsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);


//    List< Car> findAllByByPrizeAndModel(BigDecimal prize, String model);
//    List< Car> findAllByByEngineAndModel(BigDecimal prize, String model);
//    List< Car> findAllByBrandAndModel(BigDecimal prize, String model);
//    List< Car> findAllByByKmAndModel(BigDecimal prize, String model);
//    List< Car> findAllByByTransmissionAndModel(BigDecimal prize, String model);
//    List< Car> findAllByByYearAndModel(BigDecimal prize, String model);
//    List< Car> findAllByByBrandAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByByEngineAndKm(BigDecimal prize, String model);
//    List< Car> findAllByByEngineAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByByYearAndAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByByPetrolAndEngine(BigDecimal prize, String model);
//    List< Car> findAllByByBrandAndKM(BigDecimal prize, String model);
//    List< Car> findAllByByBrandAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByByBrandAndYear(BigDecimal prize, String model);
//    List< Car> findAllByByBrandAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByByKmAndTransmission(BigDecimal prize, String model);
//    List< Car> findAllByByKmAndYear(BigDecimal prize, String model);
//    List< Car> findAllByByKmAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByByTransmissionAndYear(BigDecimal prize, String model);
//    List< Car> findAllByByTransmissionAndPetrol(BigDecimal prize, String model);
//    List< Car> findAllByByYearAndPetrol(BigDecimal prize, String model);

    List< Car> findAllByYear(Integer year);
    List< Car> findAllByPetrol(Petrol petrol);
    List< Car> findAllByEngine(Double engine);




    @Query(nativeQuery = true,value = "select * from CARS c where c.model LIKE  %?1%  or c.brand LIKE  %?1% or c.km LIKE  %?1% or c.nm LIKE  %?1% " +
            "or c.petrol LIKE  %?1% or c.engine LIKE  %?1% or c.transmission LIKE  %?1% or c.category LIKE  %?1% or c.license_Plate LIKE  %?1%")
    List<Car> findAllByKeyword(String keyword);


    Optional<Car> findByKm(int km);

    //2
    List<Car> findAllByBrandAndPetrol(String brand, Petrol petrol);
    List<Car> findAllByBrandAndNumberOfSeats(String brand, Integer numberOfSeats);
    List<Car> findAllByBrandAndTransmission(String brand, Transmission transmission);
    List<Car> findAllByBrandAndCategory(String brand, Category category);
    List<Car> findAllByBrandAndYear(String brand, Integer year);

    List<Car> findAllByPetrolAndNumberOfSeats(Petrol petrol, Integer numberOfSeats);
    List<Car> findAllByPetrolAndTransmission(Petrol petrol, Transmission transmission);
    List<Car> findAllByPetrolAndCategory(Petrol petrol, Category category);
    List<Car> findAllByPetrolAndYear(Petrol petrol, Integer year);

    List<Car> findAllByNumberOfSeatsAndTransmission(Integer numberOfSeats, Transmission transmission);
    List<Car> findAllByNumberOfSeatsAndCategory(Integer numberOfSeats, Category category);
    List<Car> findAllByNumberOfSeatsAndYear(Integer numberOfSeats,Integer year);

    List<Car> findAllByTransmissionAndCategory(Transmission transmission, Category category);
    List<Car> findAllByTransmissionAndYear(Transmission transmission, Integer year);

    List<Car> findAllByCategoryAndYear(Category category, Integer year);
    //6;
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYear(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Category category, Integer year);

    //5
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategory(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Category category);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYear(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Integer year);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYear(Integer numberOfSeats, String brand, Petrol petrol, Category category, Integer year);
    List<Car> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYear(Integer numberOfSeats, String brand, Transmission transmission, Category category, Integer year);
    List<Car> findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYear(Integer numberOfSeats,  Transmission transmission,Petrol petrol, Category category, Integer year);
    List<Car> findAllByTransmissionAndBrandAndPetrolAndCategoryAndYear(Transmission transmission,String brand, Petrol petrol,  Category category, Integer year);

    //3
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrol(Integer numberOfSeats, String brand, Petrol petrol);
    List<Car> findAllByNumberOfSeatsAndBrandAndYear(Integer numberOfSeats, String brand, Integer year);
    List<Car> findAllByNumberOfSeatsAndBrandAndTransmission(Integer numberOfSeats, String brand, Transmission transmission);
    List<Car> findAllByNumberOfSeatsAndBrandAndCategory(Integer numberOfSeats, String brand, Category category);

    List<Car> findAllByNumberOfSeatsAndPetrolAndYear(Integer numberOfSeats, Petrol petrol,  Integer year);
    List<Car> findAllByNumberOfSeatsAndPetrolAndCategory(Integer numberOfSeats, Petrol petrol,  Category category);
    List<Car> findAllByNumberOfSeatsAndPetrolAndTransmission(Integer numberOfSeats,  Petrol petrol, Transmission transmission);

    List<Car> findAllByNumberOfSeatsAndYearAndTransmission(Integer numberOfSeats, Integer year,Transmission transmission);
    List<Car> findAllByNumberOfSeatsAndYearAndCategory(Integer numberOfSeats,Integer year, Category category );

    List<Car> findAllByNumberOfSeatsAndTransmissionAndCategory(Integer numberOfSeats, Transmission transmission, Category category);


    List<Car> findAllByBrandAndPetrolAndYear(String brand, Petrol petrol, Integer year);
    List<Car> findAllByBrandAndPetrolAndTransmission(String brand, Petrol petrol, Transmission transmission);
    List<Car> findAllByBrandAndPetrolAndCategory(String brand, Petrol petrol, Category category);

    List<Car> findAllByBrandAndYearAndTransmission(String brand, Integer year,Transmission transmission);
    List<Car> findAllByBrandAndYearAndCategory(String brand,Integer year,Category category);

    List<Car> findAllByBrandAndTransmissionAndCategory(String brand, Transmission transmission, Category category);

    List<Car> findAllByPetrolAndYearAndTransmission( Petrol petrol, Integer year, Transmission transmission);
    List<Car> findAllByPetrolAndYearAndCategory(Petrol petrol, Integer year,Category category);

    List<Car> findAllByPetrolAndTransmissionAndCategory(Petrol petrol, Transmission transmission, Category category);

    List<Car> findAllByYearAndTransmissionAndCategory(Integer year,Transmission transmission, Category category);

    //4
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndYear(Integer numberOfSeats, String brand, Petrol petrol, Integer year);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmission(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndCategory(Integer numberOfSeats, String brand, Petrol petrol, Category category);
    List<Car> findAllByNumberOfSeatsAndPetrolAndYearAndTransmission(Integer numberOfSeats, Petrol petrol,Integer year,Transmission transmission);
    List<Car> findAllByNumberOfSeatsAndPetrolAndYearAndCategory(Integer numberOfSeats, Petrol petrol, Integer year,Category category);
    List<Car> findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategory(Integer numberOfSeats, Petrol petrol, Transmission transmission, Category category);
    List<Car> findAllByNumberOfSeatsAndYearAndTransmissionAndCategory(Integer numberOfSeats,Integer year, Transmission transmission, Category category);
    List<Car> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategory(Integer numberOfSeats, String brand, Transmission transmission, Category category);
    List<Car> findAllByNumberOfSeatsAndBrandAndYearAndCategory(Integer numberOfSeats, String brand, Integer year,Category category);
    List<Car> findAllByNumberOfSeatsAndBrandAndYearAndTransmission(Integer numberOfSeats, String brand,Integer year, Transmission transmission);

    List<Car> findAllByBrandAndPetrolAndYearAndTransmission(String brand, Petrol petrol, Integer year, Transmission transmission);
    List<Car> findAllByBrandAndPetrolAndYearAndCategory(String brand, Petrol petrol, Integer year, Category category);
    List<Car> findAllByBrandAndYearAndTransmissionAndCategory(String brand, Integer year,Transmission transmission, Category category);
    List<Car> findAllByBrandAndPetrolAndTransmissionAndCategory(String brand, Petrol petrol, Transmission transmission, Category category);


    List<Car> findAllByPetrolAndYearAndTransmissionAndCategory(Petrol petrol,Integer year, Transmission transmission, Category category );




    List<Car> findAllByBrandAndPetrolAndTaken(String brand, Petrol petrol, Boolean taken);
    List<Car> findAllByBrandAndNumberOfSeatsAndTaken(String brand, Integer numberOfSeats, Boolean taken);
    List<Car> findAllByBrandAndTransmissionAndTaken(String brand, Transmission transmission, Boolean taken);
    List<Car> findAllByBrandAndCategoryAndTaken(String brand, Category category, Boolean taken);
    List<Car> findAllByBrandAndYearAndTaken(String brand, Integer year, Boolean taken);

    List<Car> findAllByPetrolAndNumberOfSeatsAndTaken(Petrol petrol, Integer numberOfSeats, Boolean taken);
    List<Car> findAllByPetrolAndTransmissionAndTaken(Petrol petrol, Transmission transmission, Boolean taken);
    List<Car> findAllByPetrolAndCategoryAndTaken(Petrol petrol, Category category, Boolean taken);
    List<Car> findAllByPetrolAndYearAndTaken(Petrol petrol, Integer year, Boolean taken);

    List<Car> findAllByNumberOfSeatsAndTransmissionAndTaken(Integer numberOfSeats, Transmission transmission, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndCategoryAndTaken(Integer numberOfSeats, Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndYearAndTaken(Integer numberOfSeats,Integer year, Boolean taken);

    List<Car> findAllByTransmissionAndCategoryAndTaken(Transmission transmission, Category category, Boolean taken);
    List<Car> findAllByTransmissionAndYearAndTaken(Transmission transmission, Integer year, Boolean taken);

    List<Car> findAllByCategoryAndYearAndTaken(Category category, Integer year, Boolean taken);
    //6;
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndYearAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Category category, Integer year, Boolean taken);

    //5
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndCategoryAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndYearAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Integer year, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndYearAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Category category, Integer year, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndYearAndTaken(Integer numberOfSeats, String brand, Transmission transmission, Category category, Integer year, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndTransmissionAndPetrolAndCategoryAndYearAndTaken(Integer numberOfSeats,  Transmission transmission,Petrol petrol, Category category, Integer year, Boolean taken);
    List<Car> findAllByTransmissionAndBrandAndPetrolAndCategoryAndYearAndTaken(Transmission transmission,String brand, Petrol petrol,  Category category, Integer year, Boolean taken);

    //3
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndYearAndTaken(Integer numberOfSeats, String brand, Integer year, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndTransmissionAndTaken(Integer numberOfSeats, String brand, Transmission transmission, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndCategoryAndTaken(Integer numberOfSeats, String brand, Category category, Boolean taken);

    List<Car> findAllByNumberOfSeatsAndPetrolAndYearAndTaken(Integer numberOfSeats, Petrol petrol,  Integer year, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndPetrolAndCategoryAndTaken(Integer numberOfSeats, Petrol petrol,  Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndPetrolAndTransmissionAndTaken(Integer numberOfSeats,  Petrol petrol, Transmission transmission, Boolean taken);

    List<Car> findAllByNumberOfSeatsAndYearAndTransmissionAndTaken(Integer numberOfSeats, Integer year,Transmission transmission, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndYearAndCategoryAndTaken(Integer numberOfSeats,Integer year, Category category, Boolean taken );

    List<Car> findAllByNumberOfSeatsAndTransmissionAndCategoryAndTaken(Integer numberOfSeats, Transmission transmission, Category category, Boolean taken);


    List<Car> findAllByBrandAndPetrolAndYearAndTaken(String brand, Petrol petrol, Integer year, Boolean taken);
    List<Car> findAllByBrandAndPetrolAndTransmissionAndTaken(String brand, Petrol petrol, Transmission transmission, Boolean taken);
    List<Car> findAllByBrandAndPetrolAndCategoryAndTaken(String brand, Petrol petrol, Category category, Boolean taken);

    List<Car> findAllByBrandAndYearAndTransmissionAndTaken(String brand, Integer year,Transmission transmission, Boolean taken);
    List<Car> findAllByBrandAndYearAndCategoryAndTaken(String brand,Integer year,Category category, Boolean taken);

    List<Car> findAllByBrandAndTransmissionAndCategoryAndTaken(String brand, Transmission transmission, Category category, Boolean taken);

    List<Car> findAllByPetrolAndYearAndTransmissionAndTaken( Petrol petrol, Integer year, Transmission transmission, Boolean taken);
    List<Car> findAllByPetrolAndYearAndCategoryAndTaken(Petrol petrol, Integer year,Category category, Boolean taken);

    List<Car> findAllByPetrolAndTransmissionAndCategoryAndTaken(Petrol petrol, Transmission transmission, Category category, Boolean taken);

    List<Car> findAllByYearAndTransmissionAndCategoryAndTaken(Integer year,Transmission transmission, Category category, Boolean taken);

    //4
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndYearAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Integer year, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndTransmissionAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Transmission transmission, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndPetrolAndCategoryAndTaken(Integer numberOfSeats, String brand, Petrol petrol, Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndPetrolAndYearAndTransmissionAndTaken(Integer numberOfSeats, Petrol petrol,Integer year,Transmission transmission, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndPetrolAndYearAndCategoryAndTaken(Integer numberOfSeats, Petrol petrol, Integer year,Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndPetrolAndTransmissionAndCategoryAndTaken(Integer numberOfSeats, Petrol petrol, Transmission transmission, Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndYearAndTransmissionAndCategoryAndTaken(Integer numberOfSeats,Integer year, Transmission transmission, Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndTransmissionAndCategoryAndTaken(Integer numberOfSeats, String brand, Transmission transmission, Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndYearAndCategoryAndTaken(Integer numberOfSeats, String brand, Integer year,Category category, Boolean taken);
    List<Car> findAllByNumberOfSeatsAndBrandAndYearAndTransmissionAndTaken(Integer numberOfSeats, String brand,Integer year, Transmission transmission, Boolean taken);

    List<Car> findAllByBrandAndPetrolAndYearAndTransmissionAndTaken(String brand, Petrol petrol, Integer year, Transmission transmission, Boolean taken);
    List<Car> findAllByBrandAndPetrolAndYearAndCategoryAndTaken(String brand, Petrol petrol, Integer year, Category category, Boolean taken);
    List<Car> findAllByBrandAndYearAndTransmissionAndCategoryAndTaken(String brand, Integer year,Transmission transmission, Category category, Boolean taken);
    List<Car> findAllByBrandAndPetrolAndTransmissionAndCategoryAndTaken(String brand, Petrol petrol, Transmission transmission, Category category, Boolean taken);


    List<Car> findAllByPetrolAndYearAndTransmissionAndCategoryAndTaken(Petrol petrol,Integer year, Transmission transmission, Category category, Boolean taken );
}
