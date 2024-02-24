package kkkw.subrandom.repository.recipe;

import kkkw.subrandom.domain.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}