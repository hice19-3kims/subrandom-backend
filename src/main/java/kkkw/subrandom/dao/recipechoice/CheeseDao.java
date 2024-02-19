package kkkw.subrandom.dao.recipechoice;

import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheeseDao extends JpaRepository<Cheese, Long> {
}