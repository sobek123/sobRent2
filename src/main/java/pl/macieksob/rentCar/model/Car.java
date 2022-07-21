package pl.macieksob.rentCar.model;

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
import java.util.Set;

@Entity
@Table(name = "CARS")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Pole nie może byc puste")
    private String model;
    
    @NotBlank(message = "Pole nie może byc puste")
    private String licensePlate;
    
    @NotEmpty(message = "Pole nie może byc puste")
    private BigDecimal prize;
    
    @NotNull(message = "Pole nie może byc puste")
    @Min(30)
    private Integer km;
    
    @NotNull(message = "Pole nie może byc puste")
    @Min(50)
    private Integer nm;

    @NotNull(message = "Pole nie może byc puste")
    @Min(5)
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
    
    @NotBlank(message = "Pole nie może byc puste")
    private String details;

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

    @NotNull(message = "Pole nie może byc puste")
    private Boolean taken;

    @Min(200)
    @NotNull(message = "Pole nie może byc puste")
    private Integer deposit;

    @ManyToMany(mappedBy = "cars")
    @NotEmpty(message = "Pole nie może byc puste")
    private Set<Order> orders;
}
