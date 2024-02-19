package kkkw.subrandom.dao;

import kkkw.subrandom.domain.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeDao extends JpaRepository<Recipe, Long> {
}