package pl.macieksob.rentCar.security;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JWTResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private List<String> roles;

    public JWTResponse(String token, Long id, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}
