package kkkw.subrandom.dto;

import jakarta.validation.constraints.NotNull;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.domain.recipe.RecipeCheese;
import kkkw.subrandom.domain.recipe.RecipeSauce;
import kkkw.subrandom.domain.recipe.RecipeVegetable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeGetDto {

    private Long id;
    private String mainStuff;
    private String bread;
    private List<Long> cheeseIds = new ArrayList<>();
    private List<Long> sauceIds = new ArrayList<>();
    private List<Long> vegetableIds = new ArrayList<>();

    public RecipeGetDto(Recipe recipe) {
        id = recipe.getId();
        mainStuff = recipe.getMainStuff();
        bread = recipe.getBread();

        List<RecipeCheese> recipeCheeses = recipe.getRecipeCheeses();
        recipeCheeses.forEach(rc -> cheeseIds.add(rc.getCheese().getId()));

        List<RecipeSauce> recipeSauces = recipe.getRecipeSauces();
        recipeSauces.forEach(rs -> sauceIds.add(rs.getSauce().getId()));

        List<RecipeVegetable> recipeVegetables = recipe.getRecipeVegetables();
        recipeVegetables.forEach(rv -> vegetableIds.add(rv.getVegetable().getId()));
    }
}
