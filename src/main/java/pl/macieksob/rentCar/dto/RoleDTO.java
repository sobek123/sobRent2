package pl.macieksob.rentCar.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.macieksob.rentCar.model.User;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Data
@NoArgsConstructor

public class RoleDTO {

    private Long id;

    @NotBlank
    private String name;


    @NotEmpty
    private Collection< User > users;
}
