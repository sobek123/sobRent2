package pl.macieksob.rentCar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;


@Data
@Getter
@NoArgsConstructor

public class UserDTO {
    private Long id;

    @NotBlank(message = "Pole nie może byc puste!")
    private String name;


    @NotBlank(message = "Pole nie może byc puste!")
    private String surname;

    @Email
    @NotBlank(message = "Pole nie może byc puste!")
    private String email;

    @NotBlank(message = "Pole nie może byc puste!")
    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String postCode;

    @NotBlank(message = "Pole nie może byc puste!")
    @Pattern(regexp = "[a-z]+([ -][A-Z][a-z]+)?")
    private String city;

    @NotBlank(message = "Pole nie może byc puste!")
    @Pattern(regexp = "\\d{3} \\d{3} \\d{3}")
    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

    @NotBlank(message = "Pole nie może byc puste!")
    private String street;

    @NotBlank(message = "Pole nie może byc puste!")
    @Pattern(regexp = "\\\\d+[A-Z]?\\\\\\\\\\\\d+[A-Z]?")
    private String numberOfStreet;

    @Min(1)
    @NotNull(message = "Pole nie może byc puste")
    private Integer numberOfFlat;

    @NotBlank(message = "Pole nie może byc puste!")
    @Size(min=6,message = "Pole musi mieć co najmniej 6 znaków!")
    private String password;

    @NotBlank(message = "Pole nie może byc puste!")
    @Size(min=11,max = 11,message = "Pole ma nieprawidłową ilość znaków")
    private String pesel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Pole nie może byc puste")
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotBlank(message = "Pole nie może być puste!")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Pole nie może byc puste!")
    private String resetPasswordToken;

    @NotBlank(message = "Pole nie może byc puste!")
    private String verificationCode;

    //    @NotBlank(message = "Pole nie może byc puste!")
    private Boolean enabled;

    @NotEmpty(message = "Pole nie może byc puste")
    private Collection<Role> roles;

    @NotNull(message = "Pole nie może być puste!")
    private Integer points;
}
