package kkkw.subrandom.initializer;

import kkkw.subrandom.repository.recipechoice.VegetableRepository;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class VegetableInitializer {

    private final VegetableRepository vegetableRepository;

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

        vegetableRepository.save(cucumber);
        vegetableRepository.save(jalapeno);
        vegetableRepository.save(lettuce);
        vegetableRepository.save(olive);
        vegetableRepository.save(onion);
        vegetableRepository.save(pepper);
        vegetableRepository.save(pickle);
        vegetableRepository.save(tomato);
    }
}
