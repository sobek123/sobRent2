package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.exception.CarDuplicateException;
import pl.macieksob.rentCar.exception.CarNotFoundException;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Petrol;
import pl.macieksob.rentCar.model.Transmission;
import pl.macieksob.rentCar.repository.CarRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

    private CarDTO mapToDTO(Car car){
        CarDTO map = modelMapper.map(car, CarDTO.class);
        return map;
    }

    private Car mapToEntity(CarDTO carDTO){
        Car car = modelMapper.map(carDTO, Car.class);
        return car;
    }

    @Transactional
    public CarDTO addCar(CarDTO car){
        if(carRepository.existsById(car.getId())){
            throw new CarDuplicateException("Car already exists!");
        }

        Car car1 = mapToEntity(car);
        carRepository.save(car1);

        return car;
    }

    public void deleteCar(Long id) throws CarNotFoundException{
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        carRepository.delete(car);
    }

    public CarDTO getCarById(Long id) throws CarNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not exist!");
        });
        return mapToDTO(car);
    }

    public CarDTO editCar(Long id, CarDTO editCar) throws CarNotFoundException{
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new CarNotFoundException("Car not found!");
        });

        car.setTransmission(editCar.getTransmission());
        car.setCategory(editCar.getCategory());
        car.setDetails(editCar.getDetails());
        car.setImage(editCar.getImage());

        carRepository.save(car);

        return mapToDTO(car);
    }

    public void deleteCar(CarDTO car){
        Car car1 = mapToEntity(car);
        carRepository.delete(car1);
    }

    public void deleteCarById(Long id){
        carRepository.deleteById(id);
    }

    public java.util.List<CarDTO> getByModel(String model){
        return carRepository.findAllByModel(model, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public java.util.List<CarDTO> getAllCars(){
        return carRepository.findAll(PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    public java.util.List<CarDTO> getByBrand(String brand){
        return carRepository.findAllByBrand(brand, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByKm(Integer km){
        return carRepository.findAllByKm(km, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByTransmission(Transmission transmission){
        return carRepository.findAllByTransmission(transmission, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public java.util.List<CarDTO> getByPetrol(Petrol petrol){
        return carRepository.findAllByPetrol(petrol, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByEngine(Double engine){
        return carRepository.findAllByEngine(engine, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByYear(Integer year){
        return carRepository.findAllByYear(year, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> getByPrize(BigDecimal prize){
        return carRepository.findAllByPrize(prize, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //SORTING
    public java.util.List<CarDTO> sortByPrizeAscending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("prize")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByPrizeDescending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.desc("prize")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByBrandAscending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("brand")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByBrandDescending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("brand")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByYearAscending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("year")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByYearDescending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("year")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByKmAscending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("km")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByKmDescending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.desc("km")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNmAscending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("nm")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByNmDescending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.desc("nm")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByCombustionAscending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("combustion")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByCombustionDescending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.desc("combustion")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByEngineAscending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.asc("engine")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public java.util.List<CarDTO> sortByEngineDescending(){
        return carRepository.findAll(PageRequest.of(0,10,Sort.by(Sort.Order.desc("engine")))).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

//    public java.util.List<CarDTO> getByKeyword(String keyword){
//        return carRepository.findAllByKeyword(keyword, PageRequest.of(0,10)).stream().map(this::mapToDTO).collect(Collectors.toList());
//    }

    //FILTERING
    public List<CarDTO> getSportCar(){
        return carRepository.findAllByCategory("SPORT",PageRequest.of(0,3)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getExclusiveCar(){
        return carRepository.findAllByCategory("EXCLUSIVE",PageRequest.of(0,3)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getComfortCar(){
        return carRepository.findAllByCategory("COMFORT",PageRequest.of(0,3)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getCargoCar(){
        return carRepository.findAllByCategory("CARGO",PageRequest.of(0,3)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getSUVCar(){
        return carRepository.findAllByCategory("SUV",PageRequest.of(0,3)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getEconomyCar(){
        return carRepository.findAllByCategory("ECONOMY",PageRequest.of(0,3)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<CarDTO> getAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(String brand, String model, BigDecimal prize, Integer km, Petrol petrol, Integer year, Double engine, Transmission tr){

        return carRepository.findAllByPrizeAndModelAndBrandAndKmAndTransmissionAndYearAndPetrolAndEngine(prize,model,brand,km,tr,year,petrol,engine,PageRequest.of(0,5)).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


}
