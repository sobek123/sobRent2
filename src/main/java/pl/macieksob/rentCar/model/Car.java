package pl.macieksob.rentCar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CARS")
@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Pole nie może byc puste")
    private String model;
    
    @NotBlank(message = "Pole nie może byc puste")
    private String licensePlate;
    
//    @NotNull(message = "Pole nie może byc puste")
    @OneToOne(cascade = CascadeType.ALL)
    private Price price;
    
    @NotNull(message = "Pole nie może byc puste")
    @Min(30)
    private Integer km;
    
    @NotNull(message = "Pole nie może byc puste")
    @Min(50)
    private Integer nm;

    @NotNull(message = "Pole nie może byc puste")
    @Min(3)
    @Max(50)
    private Double combustion;

    @NotNull(message = "Pole nie może byc puste")
    @Min(1)
    @Max(10)
    private Double engine;

    @NotBlank(message = "Pole nie może byc puste")
    private String image;
    
    @NotBlank(message = "Pole nie może byc puste")
    private String brand;

    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Pole nie może byc puste")
    private Transmission transmission;
    
    private String fault = "";

    @NotNull(message = "Pole nie może byc puste")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @NotNull(message = "Pole nie może byc puste")
    @Enumerated(value = EnumType.STRING)
    private Petrol petrol;

    @NotNull(message = "Pole nie może byc puste")
    @Min(1960)
    @Max(2023)
    private Integer year;

//    @NotNull(message = "Pole nie może byc puste")
    private Boolean taken = false;

    @NotNull(message = "Pole nie może być puste")
    @Min(100)
    private Integer points;

//    @Min(200)
//    @NotNull(message = "Pole nie może byc puste")
//    private Integer deposit;
//
//    @ManyToMany(mappedBy = "cars")
////    @NotEmpty(message = "Pole nie może byc puste")
//    private Set<Order> orders;

//    @OneToOne(mappedBy = "car",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Order order;

    @NotBlank(message = "Pole nie może byc puste")
    private String details;

    @Min(2)
    @NotNull(message = "Pole nie może byc puste")
    private Integer numberOfSeats;

    @Min(0)
    private Integer rentings;



    public Car(Long id, String model, String licensePlate, Price price, Integer km, Integer nm, Double combustion, Double engine, String image, String brand, Transmission transmission, Category category, Petrol petrol, Integer year, Integer points, String details, Integer numberOfSeats) {
        this.id = id;
        this.model = model;
        this.licensePlate = licensePlate;
        this.price = price;
        this.km = km;
        this.nm = nm;
        this.combustion = combustion;
        this.engine = engine;
        this.image = image;
        this.brand = brand;
        this.transmission = transmission;
        this.category = category;
        this.petrol = petrol;
        this.year = year;
        this.points = points;
        this.details = details;
        this.numberOfSeats = numberOfSeats;
    }
    //    private Boolean opened;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return brand.equals(car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand);
    }
}
