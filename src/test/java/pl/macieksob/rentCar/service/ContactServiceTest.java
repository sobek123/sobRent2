//package pl.macieksob.rentCar.service;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import pl.macieksob.rentCar.dto.ContactDTO;
//import pl.macieksob.rentCar.model.Contact;
//import pl.macieksob.rentCar.repository.CardRepository;
//import pl.macieksob.rentCar.repository.ContactRepository;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class ContactServiceTest {
//
//    @Mock
//    private ContactRepository contactRepository;
//
//    @InjectMocks
//    private ContactService contactService;
//
//    @BeforeAll
//    public void setup(){
//        contactService.addContact(new ContactDTO(1L,"Maciek", "Kol", "maciejak@wp.pl","446664555", "WItam", "Odpowiedz", false, LocalDateTime.now()));
//        contactService.addContact(new ContactDTO(2L,"Maciek", "Kol", "maciejak@wp.pl","446664555", "WItam", "Odpowiedz", false, LocalDateTime.now()));
//    }
//
//    @Test
//    public void addContact(){
//        ContactDTO contactDTO = contactService.addContact(new ContactDTO(3L,"Maciek", "Kol", "maciejak@wp.pl","446664555", "WItam", "Odpowiedz", false, LocalDateTime.now()));
//
//
//        assertNotNull(contactService.getContact(7L));
//    }
//
//    @Test
//    public void editContact(){
//        ContactDTO contact = contactService.getContact(7L);
//        contact.setRespond("Nowa odpowiedz");
//        contactService.addContact(contact);
//
//        assertThat(contactService.getContact(7L).getRespond()).isEqualTo(BigDecimal.valueOf(345.55));
//    }
//
//    @Test
//    public void deleteContact(){
//        contactService.deleteContactById(7L);
//        assertThat(contactService.getContact(7L)).isNull();
//    }
//
//    @Test
//    public void getContacts(){
//        assertThat(contactService.getAll().size()).isGreaterThan(0);
//
//    }
//
//    @Test
//    public void getContact(){
//        when(contactService.getContact(1L)).thenReturn(contactService.addContact(new ContactDTO(1L,"Maciek", "Kol", "maciejak@wp.pl","446664555", "WItam", "Odpowiedz", false, LocalDateTime.now())));
//
//        ContactDTO contactDTO = contactService.getContact(1L);
//        assertEquals("Maciek",contactDTO.getName());
//        assertEquals("Kol",contactDTO.getSurname());
//    }
//}
