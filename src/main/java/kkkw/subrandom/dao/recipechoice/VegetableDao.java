package kkkw.subrandom.dao.recipechoice;

import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeggieDao extends JpaRepository<Vegetable, Long> {
}