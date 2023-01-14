package pl.macieksob.rentCar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@Table(name="ROLES")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pole nie może byc puste")
    private String name;

    @JsonIgnoreProperties("roles")
    @ManyToMany(mappedBy = "roles",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @NotEmpty(message = "Pole nie może byc puste")
    private Collection<User> users;

    public Role(long l, String name) {
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }
}
