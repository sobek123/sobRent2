package pl.macieksob.rentCar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal firstPeriod;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal secondPeriod;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal thirdPeriod;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal fourthPeriod;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal fifthPeriod;

    @NotNull(message = "Pole nie może byc puste")
    private BigDecimal sixthPeriod;

    @NotNull(message = "Pole nie może byc puste")
    @Min(200)
    private BigDecimal deposit;

}
