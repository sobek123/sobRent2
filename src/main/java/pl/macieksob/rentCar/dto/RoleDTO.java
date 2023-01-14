package pl.macieksob.rentCar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.macieksob.rentCar.model.User;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleDTO {

    private Long id;

    @NotBlank(message = "Pole nie może być puste")
    private String name;

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonIgnore
    private Collection< User > users;
}
