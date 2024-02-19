package kkkw.subrandom;

import kkkw.subrandom.dao.recipechoice.CheeseDao;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CheeseInitializer {

    private final CheeseDao cheeseDao;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void sauceInit() {
        Cheese american = new Cheese(1L, "american");
        Cheese mozzarella = new Cheese(2L, "mozzarella");
        Cheese shred = new Cheese(3L, "shred");

        cheeseDao.save(american);
        cheeseDao.save(mozzarella);
        cheeseDao.save(shred);
    }
}
