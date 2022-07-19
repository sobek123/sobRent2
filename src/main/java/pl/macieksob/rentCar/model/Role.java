package pl.macieksob.rentCar.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name="ROLES")
@Data
@NoArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pole nie może byc puste")
    private String name;

    @ManyToMany(mappedBy = "roles",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @NotEmpty(message = "Pole nie może byc puste")
    private Collection<User> users;

    public Role(String name) {
        this.name = name;
    }
}
