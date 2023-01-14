package pl.macieksob.rentCar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.macieksob.rentCar.dto.ContactDTO;
import pl.macieksob.rentCar.dto.OrderDTO;
import pl.macieksob.rentCar.model.Contact;
import pl.macieksob.rentCar.model.Order;
import pl.macieksob.rentCar.repository.ContactRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    public ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ContactDTO mapToDTO(Contact contact){
        return modelMapper.map(contact, ContactDTO.class);
    }

    private Contact mapToEntity(ContactDTO contact){
        return modelMapper.map(contact, Contact.class);
    }

    public List<ContactDTO> allMessages(){
        return contactRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ContactDTO addContact(ContactDTO contactDTO) {
        contactDTO.setDate(LocalDateTime.now());
        contactDTO.setOpened(false);
        Contact contact = mapToEntity(contactDTO);

        return mapToDTO(contactRepository.save(contact));
    }

    public void setOpened(ContactDTO contactDTO){
        Contact contact = mapToEntity(contactDTO);
        Contact contact1 = contactRepository.findById(contactDTO.getId()).orElseThrow();

        contact1.setOpened(true);

        contactRepository.save(contact1);
    }

    public void respond(ContactDTO contactDTO) {
        Contact contact = mapToEntity(contactDTO);
        Contact contact1 = contactRepository.findById(contact.getId()).orElseThrow();

        contact1.setRespond(contactDTO.getRespond());

        contactRepository.save(contact1);
    }

    public ContactDTO getContact(long l) {
        return mapToDTO(contactRepository.findById(l).orElseThrow());
    }

    public void deleteContactById(long l) {
        contactRepository.deleteById(l);
    }

    public List<ContactDTO> getAll() {
        return contactRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Integer getByOpened() {
        return contactRepository.findAllByOpened(false).size();
    }
}
