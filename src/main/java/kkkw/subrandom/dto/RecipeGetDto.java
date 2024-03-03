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
    private List<String> cheeses = new ArrayList<>();
    private List<String> sauces = new ArrayList<>();
    private List<String> vegetables = new ArrayList<>();

    public RecipeGetDto(Recipe recipe) {
        id = recipe.getId();
        mainStuff = recipe.getMainStuff();
        bread = recipe.getBread();

        List<RecipeCheese> recipeCheeses = recipe.getRecipeCheeses();
        recipeCheeses.forEach(rc -> cheeses.add(rc.getCheese().getCheeseName()));

        List<RecipeSauce> recipeSauces = recipe.getRecipeSauces();
        recipeSauces.forEach(rs -> sauces.add(rs.getSauce().getSauceName()));

        List<RecipeVegetable> recipeVegetables = recipe.getRecipeVegetables();
        recipeVegetables.forEach(rv -> vegetables.add(rv.getVegetable().getVegetableName()));
    }
}
