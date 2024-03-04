package kkkw.subrandom.repository.recipechoice;

import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheeseRepository extends JpaRepository<Cheese, Long> {
}