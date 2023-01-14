package pl.macieksob.rentCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.macieksob.rentCar.model.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByName(String name );

    List<Contact> findAllByOpened(boolean b);
}
