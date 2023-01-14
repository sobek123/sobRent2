package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.macieksob.rentCar.dto.CarDTO;
import pl.macieksob.rentCar.dto.CardDTO;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.model.Car;
import pl.macieksob.rentCar.model.Card;
import pl.macieksob.rentCar.model.User;
import pl.macieksob.rentCar.repository.CardRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardRepository cardRepository;

    private CardDTO mapToDTO(Card card){
        return modelMapper.map(card, CardDTO.class);
    }

    private Card mapToEntity(CardDTO cardDTO){
        return modelMapper.map(cardDTO, Card.class);
    }

    @Transactional
    public CardDTO addCard(User user){
        Card card = new Card();
        card.setPoints(0);
        card.setCode(createRandomUniqueCode());
        card.setUser(user);
        return mapToDTO(cardRepository.save((card)));
    }

    @Transactional
    void deleteCardById(Long id){
        cardRepository.deleteById(id);
    }

    public void addPoints(Card car){
        cardRepository.save(car);
    }

    public String createRandomUniqueCode(){

        int i = (int) (Math.random() * ((10000) + 1));
        int i2 = (int) (Math.random() * ((10000) + 1));
        int i3 = (int) (Math.random() * ((10000) + 1));
        int i4 = (int) (Math.random() * ((10000) + 1));

        String first = String.valueOf(i);
        String second = String.valueOf(i2);
        String third = String.valueOf(i3);
        String forth = String.valueOf(i4);

        if(i < 9000){
            int len = first.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            first = format + first;
        }else if(i2 < 9000){
            int len = second.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            second = format + second;
        }else if(i3 < 9000){
            int len = third.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            third = format + third;
        }
        else if(i4 < 9000){
            int len = third.length();
            String format = "";
            for (int j = 0; j < len; j++) {
                format += "0";
            }
            third = format + third;
        }
        return  first + second + third + forth;
    }

    public CardDTO getCard(long l) {
        return null;
    }

    public List<CardDTO> getAll() {
        return cardRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
