package pl.macieksob.rentCar.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.Category;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.model.Petrol;
import pl.macieksob.rentCar.model.Transmission;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor

public class CarDTO {


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
