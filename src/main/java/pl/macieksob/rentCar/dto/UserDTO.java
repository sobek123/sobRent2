package pl.macieksob.rentCar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.model.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;

    private String name;


    private String surname;

    private String email;

    private String postCode;

//    @Pattern(regexp = "[a-z]+([ -][A-Z][a-z]+)?")
    private String city;

//    @Pattern(regexp = "\\d{3} \\d{3} \\d{3}")
//    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

    private String street;

//    @Pattern(regexp = "\\\\d+[A-Z]?\\\\\\\\\\\\d+[A-Z]?")
    private String numberOfStreet;

    private Integer numberOfFlat;

    private String password;

    private String pesel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String resetPasswordToken;

    private String verificationCode;

    //    @NotBlank(message = "Pole nie może byc puste!")
    private Boolean enabled;

    private Card card;

    private String role;

//    @NotEmpty(message = "Pole nie może byc puste")
    @JsonIgnore
    private Collection<Role> roles;



    public UserDTO(Long id, String name, String surname, String email, String postCode, String city, String phoneNumber, String street, String numberOfStreet, Integer numberOfFlat, String password, String pesel, LocalDateTime createdTime, LocalDate dateOfBirth, String resetPasswordToken, String verificationCode, Boolean enabled, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.postCode = postCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.numberOfStreet = numberOfStreet;
        this.numberOfFlat = numberOfFlat;
        this.password = password;
        this.pesel = pesel;
        this.createdTime = createdTime;
        this.dateOfBirth = dateOfBirth;
        this.resetPasswordToken = resetPasswordToken;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
        this.roles = roles;
    }




    public UserDTO(Long id, String name, String surname, String email, String postCode, String city, String phoneNumber, String street, String numberOfStreet, Integer numberOfFlat, String password, String pesel, LocalDateTime createdTime, LocalDate dateOfBirth, String resetPasswordToken, String verificationCode, Boolean enabled, Card card, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.postCode = postCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.numberOfStreet = numberOfStreet;
        this.numberOfFlat = numberOfFlat;
        this.password = password;
        this.pesel = pesel;
        this.createdTime = createdTime;
        this.dateOfBirth = dateOfBirth;
        this.resetPasswordToken = resetPasswordToken;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
        this.card = card;
        this.roles = roles;
    }
}
