package kkkw.subrandom;

import kkkw.subrandom.dao.recipechoice.SauceDao;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SauceInitializer {

    private final SauceDao sauceDao;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void sauceInit() {
        Sauce bbq = new Sauce(1L, "bbq");
        Sauce chipotle = new Sauce(2L, "chipotle");
        Sauce honeyMustard = new Sauce(3L, "honeyMustard");
        Sauce horseradish = new Sauce(4L, "horseradish");
        Sauce hotChilli = new Sauce(5L, "hotChilli");
        Sauce mayo = new Sauce(6L, "mayo");
        Sauce olive = new Sauce(7L, "olive");
        Sauce onion = new Sauce(8L, "onion");
        Sauce pepper = new Sauce(9L, "pepper");
        Sauce ranch = new Sauce(10L, "ranch");
        Sauce redWine = new Sauce(11L, "redWine");
        Sauce salt = new Sauce(12L, "salt");
        Sauce sweetChilli = new Sauce(13L, "sweetChilli");
        Sauce yellowMustard = new Sauce(14L, "yellowMustard");

        sauceDao.save(bbq);
        sauceDao.save(chipotle);
        sauceDao.save(honeyMustard);
        sauceDao.save(horseradish);
        sauceDao.save(hotChilli);
        sauceDao.save(mayo);
        sauceDao.save(olive);
        sauceDao.save(onion);
        sauceDao.save(pepper);
        sauceDao.save(ranch);
        sauceDao.save(redWine);
        sauceDao.save(salt);
        sauceDao.save(sweetChilli);
        sauceDao.save(yellowMustard);
    }
}
