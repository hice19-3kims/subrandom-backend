package kkkw.subrandom;

import kkkw.subrandom.dao.recipechoice.VeggieDao;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VeggieInitializer {

    private final VeggieDao veggieDao;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void sauceInit() {
        Vegetable cucumber = new Vegetable(1L, "cucumber");
        Vegetable jalapeno = new Vegetable(2L, "jalapeno");
        Vegetable lettuce = new Vegetable(3L, "lettuce");
        Vegetable olive = new Vegetable(4L, "olive");
        Vegetable onion = new Vegetable(5L, "onion");
        Vegetable pepper = new Vegetable(6L, "pepper");
        Vegetable pickle = new Vegetable(7L, "pickle");
        Vegetable tomato = new Vegetable(8L, "tomato");

        veggieDao.save(cucumber);
        veggieDao.save(jalapeno);
        veggieDao.save(lettuce);
        veggieDao.save(olive);
        veggieDao.save(onion);
        veggieDao.save(pepper);
        veggieDao.save(pickle);
        veggieDao.save(tomato);
    }
}
