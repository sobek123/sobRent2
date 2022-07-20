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

    
    @NotBlank
    private String name;

    
    @NotBlank
    private String surname;

    @Email
    @NotBlank
    private String email;

    
    @NotBlank
    private String postCode;

    
    @NotBlank
    private String city;

    
    @NotBlank
    private String phoneNumber;

    
    @NotBlank
    private String street;

    
    @NotBlank
    private String numberOfStreet;

    @NotBlank(message = "Pole nie może byc puste!")
    @Column(nullable = false)
    @Size(min=11,max = 11,message = "Pole ma nieprawidłową ilość znaków")
    private String pesel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    @NotNull(message = "Pole nie może byc puste")
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    @NotBlank(message = "Pole nie może być puste!")
    private LocalDate dateOfBirth;
    
    @NotNull
    private Integer numberOfFlat;

    
    @NotBlank
    private String password;

    @NotBlank
    private String verificationCode;

    private Boolean enabled;


    private String resetPasswordToken;


    @NotEmpty(message = "Pole nie może byc puste")
    private Collection<Role> roles;

}
