package pl.macieksob.rentCar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.model.Card;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void saveCardTest(){
        Card card = new Card();

        cardRepository.save(card);

        assertThat(card.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteCardTest(){
        Card card = cardRepository.findById(1L).get();

        cardRepository.delete(card);

        Card card2 = null;

        Optional<Card> opCard =  cardRepository.findById(1L);

        if(opCard.isPresent()){
            card2 = opCard.get();
        }

        assertThat(card2).isNull();
    }

    @Test
    public void getCardTest(){
        Card card = cardRepository.findById(1L).get();

        assertThat(card.getId()).isEqualTo(1L);
    }

    @Test
    public void getCardListTest(){
        List<Card> all = cardRepository.findAll();

        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    public void updateCardTest(){
        Card card = cardRepository.findById(1L).get();

        card.setPoints(3000);

        Card card2 = cardRepository.save(card);

        assertThat(card2.getPoints()).isEqualTo(3000);
    }
}