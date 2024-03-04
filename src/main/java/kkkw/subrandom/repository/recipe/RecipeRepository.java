package kkkw.subrandom.repository.recipe;

import kkkw.subrandom.domain.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByBreadAndMainStuff(String bread, String mainStuff);
}