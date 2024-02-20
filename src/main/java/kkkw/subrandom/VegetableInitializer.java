package kkkw.subrandom;

import kkkw.subrandom.dao.recipechoice.VegetableDao;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VegetableInitializer {

    private final VegetableDao vegetableDao;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void vegetableInit() {
        Vegetable cucumber = new Vegetable(1L, "cucumber");
        Vegetable jalapeno = new Vegetable(2L, "jalapeno");
        Vegetable lettuce = new Vegetable(3L, "lettuce");
        Vegetable olive = new Vegetable(4L, "olive");
        Vegetable onion = new Vegetable(5L, "onion");
        Vegetable pepper = new Vegetable(6L, "pepper");
        Vegetable pickle = new Vegetable(7L, "pickle");
        Vegetable tomato = new Vegetable(8L, "tomato");

        vegetableDao.save(cucumber);
        vegetableDao.save(jalapeno);
        vegetableDao.save(lettuce);
        vegetableDao.save(olive);
        vegetableDao.save(onion);
        vegetableDao.save(pepper);
        vegetableDao.save(pickle);
        vegetableDao.save(tomato);
    }
}
