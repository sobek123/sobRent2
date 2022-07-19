package pl.macieksob.rentCar.security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class AuthRequest {

    @Email
    private String email;
    private String password;
}
