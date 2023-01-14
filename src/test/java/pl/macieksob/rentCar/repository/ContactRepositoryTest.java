package pl.macieksob.rentCar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import pl.macieksob.rentCar.model.Contact;
import pl.macieksob.rentCar.model.Contact;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void saveContactTest(){
        Contact contact = new Contact(1L, "Maciek", "546778889", "Sob", "maciejas@o2.pl", "haloo", LocalDateTime.now(),"elo", false);

        contactRepository.save(contact);

        assertThat(contact.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteContactTest(){
        Contact contact = contactRepository.findById(1L).get();

        contactRepository.delete(contact);

        Contact contact2 = null;

        Optional<Contact> opContact =  contactRepository.findById(1L);

        if(opContact.isPresent()){
            contact2 = opContact.get();
        }

        assertThat(contact2).isNull();
    }

    @Test
    public void getContactTest(){
        Contact contact = contactRepository.findById(1L).get();

        assertThat(contact.getId()).isEqualTo(1L);
    }

    @Test
    public void getContactListTest(){
        List<Contact> all = contactRepository.findAll();

        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    public void updateContactTest(){
        Contact contact = contactRepository.findById(1L).get();

        contact.setName("Maciek");

        Contact save = contactRepository.save(contact);

        assertThat(save.getName()).isEqualTo("Maciek");
    }
}