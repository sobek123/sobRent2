package pl.macieksob.rentCar.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.Category;
import pl.macieksob.rentCar.model.Transmission;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor

public class CarDTO {

    private Long id;

    
    @NotBlank
    private String model;

    
    @NotBlank
    private String licensePlate;

    
    @NotEmpty
    private java.util.List< BigDecimal > prize;

    
    @NotNull
    private Integer km;

    
    @NotNull
    private Integer nm;

    
    @NotBlank
    private Double combustion;

    
    @NotBlank
    private String image;

    
    @NotBlank
    private String brand;

    
    @NotBlank
    private Transmission transmission;

    
    @NotBlank
    private String details;



    @Embedded
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Category category;
}
