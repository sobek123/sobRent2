package pl.macieksob.rentCar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    @JsonProperty("start_date")
    @NotNull(message = "Pole nie może byc puste")
    private LocalDate startDate;

    @JsonProperty("end_date")
    @NotNull(message = "Pole nie może byc puste")
    private LocalDate endDate;

    @JsonProperty("start_date")
    @NotNull(message = "Pole nie może byc puste")
    private LocalDateTime launchDate;

    @NotEmpty(message = "Pole nie może byc puste")
    private Set<Car> cars;

    @NotEmpty(message = "Pole nie może byc puste")
    private User user;


}
