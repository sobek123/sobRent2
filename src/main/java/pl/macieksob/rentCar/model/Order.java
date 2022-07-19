package pl.macieksob.rentCar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("start_date")
    @NotNull(message = "Pole nie może byc puste")
    @Column(nullable = false)
    private LocalDate startDate;

    @JsonProperty("end_date")
    @NotNull(message = "Pole nie może byc puste")
    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(
            name = "ORDER_CARS",
            joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "CAR_ID", referencedColumnName = "id")
    )
    @NotEmpty(message = "Pole nie może byc puste")
    private Set<Car> cars;

    @OneToOne
    @NotEmpty(message = "Pole nie może byc puste")
    private User user;
}
