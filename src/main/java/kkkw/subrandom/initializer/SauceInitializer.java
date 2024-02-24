package kkkw.subrandom.initializer;

import kkkw.subrandom.repository.recipechoice.SauceRepository;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SauceInitializer {

    private final SauceRepository sauceRepository;

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

        sauceRepository.save(bbq); // 서버 재시작 마다 JPA쿼리 계속 날아감 -> SQL 풀어서 할 것
        sauceRepository.save(chipotle);
        sauceRepository.save(honeyMustard);
        sauceRepository.save(horseradish);
        sauceRepository.save(hotChilli);
        sauceRepository.save(mayo);
        sauceRepository.save(olive);
        sauceRepository.save(onion);
        sauceRepository.save(pepper);
        sauceRepository.save(ranch);
        sauceRepository.save(redWine);
        sauceRepository.save(salt);
        sauceRepository.save(sweetChilli);
        sauceRepository.save(yellowMustard);
    }
}
