package kkkw.subrandom.initializer;

import kkkw.subrandom.repository.recipechoice.CheeseRepository;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CheeseInitializer {

    private final CheeseRepository cheeseRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void cheeseInit() {
        Cheese american = new Cheese(1L, "american");
        Cheese mozzarella = new Cheese(2L, "mozzarella");
        Cheese shred = new Cheese(3L, "shred");

        cheeseRepository.save(american);
        cheeseRepository.save(mozzarella);
        cheeseRepository.save(shred);
    }
}
