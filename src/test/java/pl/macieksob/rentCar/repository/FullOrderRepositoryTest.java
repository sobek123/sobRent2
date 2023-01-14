package pl.macieksob.rentCar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import pl.macieksob.rentCar.model.FullOrder;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class FullOrderRepositoryTest {

    @Autowired
    private FullOrderRepository fullOrderRepository;

    @Test
    public void saveFullOrderTest(){
        FullOrder fullOrder = new FullOrder(1L, Set.of(),null, LocalDateTime.now(), BigDecimal.valueOf(6000));
        FullOrder fullOrder2 = new FullOrder(2L, Set.of(),null, LocalDateTime.now(), BigDecimal.valueOf(8000));

        fullOrderRepository.save(fullOrder);
        fullOrderRepository.save(fullOrder2);


        assertThat(fullOrder.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteFullOrderTest(){
        FullOrder fullOrder = fullOrderRepository.findById(2L).get();

        fullOrderRepository.delete(fullOrder);

        FullOrder fullOrder2 = null;

        Optional<FullOrder> opFullOrder =  fullOrderRepository.findById(2L);

        if(opFullOrder.isPresent()){
            fullOrder2 = opFullOrder.get();
        }

        assertThat(fullOrder2).isNull();
    }

    @Test
    public void getFullOrderTest(){
        FullOrder fullOrder = fullOrderRepository.findById(2L).get();

        assertThat(fullOrder.getId()).isEqualTo(2L);
    }

    @Test
    public void getFullOrderListTest(){
        List<FullOrder> all = fullOrderRepository.findAll();

        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    public void updateFullOrderTest(){
        FullOrder fullOrder = fullOrderRepository.findById(2L).get();

        fullOrder.setPrize(BigDecimal.valueOf(5000));

        FullOrder save = fullOrderRepository.save(fullOrder);

        assertThat(save.getPrize()).isEqualTo(BigDecimal.valueOf(5000));
    }
}