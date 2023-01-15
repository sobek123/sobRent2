package pl.macieksob.rentCar.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pl.macieksob.rentCar.dto.CardDTO;
import pl.macieksob.rentCar.dto.UserDTO;

import pl.macieksob.rentCar.model.Role;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.repository.CardRepository;
import pl.macieksob.rentCar.repository.CardRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeAll
    public void setup(){
        cardService.addCard(null);
        cardService.addCard(null);

    }

    @Test
    public void addCard(){
        CardDTO cardDTO = cardService.addCard(null);

        assertNotNull(cardService.getCard(3L));
    }

    @Test
    public void editCard(){
        CardDTO card = cardService.getCard(3L);
        card.setPoints(500);
        cardService.addCard(null);

        assertThat(cardService.getCard(3L).getPoints()).isEqualTo(0);
    }

    @Test
    public void deleteCard(){
        cardService.deleteCardById(3L);
        assertThat(cardService.getCard(3L)).isNull();
    }

    @Test
    public void getCards(){
        assertThat(cardService.getAll().size()).isGreaterThan(0);
    }

    @Test
    public void getCard(){
        when(cardService.getCard(1L)).thenReturn(new CardDTO());

        CardDTO cardDTO = cardService.getCard(4L);
        assertEquals(0,cardDTO.getId());

    }
}
