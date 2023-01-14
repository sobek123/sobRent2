package pl.macieksob.rentCar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.macieksob.rentCar.model.Additional;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdditionalRepositoryTest {

    @Autowired
    private AdditionalRepository additionalRepository;

    @Test
    public void saveAdditionalTest(){
        Additional additional = new Additional(2L, "sdgdsf", BigDecimal.valueOf(3543));

        additionalRepository.save(additional);

        assertThat(additional.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteAdditionalTest(){
        Additional additional = additionalRepository.findById(3L).get();

        additionalRepository.delete(additional);

        Additional additional2 = null;

        Optional<Additional> opAdditional =  additionalRepository.findById(3L);

        if(opAdditional.isPresent()){
            additional2 = opAdditional.get();
        }

        assertThat(additional2).isNull();
    }

    @Test
    public void getAdditionalTest(){
        Additional additional = additionalRepository.findById(2L).get();

        assertThat(additional.getId()).isEqualTo(2L);
    }

    @Test
    public void getAdditionalListTest(){
        List<Additional> all = additionalRepository.findAll();

        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    public void updateAdditionalTest(){
        Additional additional = additionalRepository.findById(2L).get();

        additional.setName("ROLE_ADMIN");

        Additional save = additionalRepository.save(additional);

        assertThat(save.getName()).isEqualTo("ROLE_ADMIN");
    }
}



