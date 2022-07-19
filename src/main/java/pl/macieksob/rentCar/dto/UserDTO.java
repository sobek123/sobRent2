package pl.macieksob.rentCar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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

    
    @NotNull
    private Integer numberOfFlat;

    
    @NotBlank
    private String password;

    @NotBlank
    private String verificationCode;

    private Boolean enabled;


    private String resetPasswordToken;


}
