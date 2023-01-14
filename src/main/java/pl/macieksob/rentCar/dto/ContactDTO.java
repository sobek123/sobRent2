package pl.macieksob.rentCar.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDTO {

    Long id;

    @NotBlank(message = "Pole nie może być puste")
    private String name;

    @NotBlank(message = "Pole nie może być puste")
    private String surname;

    @NotBlank(message = "Pole nie może być puste")
    private String email;

    @NotBlank(message = "Pole nie może być puste")
    private String phoneNumber;

    @NotBlank(message = "Pole nie może być puste")
    private String content;

    private String respond;

    @NotNull(message = "Pole nie może być puste")
    private Boolean opened;

//    @NotNull(message = "Pole nie może być puste")
    private LocalDateTime date;

    public ContactDTO(String mac, String sob, String s, String s1, String gdfgdf) {
        this.name = mac;
        this.surname = sob;
        this.email = s;
        this.phoneNumber = s1;
        this.content = gdfgdf;
    }
}
