package kkkw.subrandom.dto;

import jakarta.validation.constraints.NotNull;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.RecipeCheese;
import kkkw.subrandom.domain.recipe.RecipeSauce;
import kkkw.subrandom.domain.recipe.RecipeVegetable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewGetDto {

    private Long id;
    private String mainStuff;
    private String bread;
    private List<Long> cheeseIds = new ArrayList<>();
    private List<Long> sauceIds = new ArrayList<>();
    private List<Long> vegetableIds = new ArrayList<>();
    private String comment;
    private Long hearts;
    private Float score;

    public ReviewGetDto(Review review) {
        id = review.getId();
        mainStuff = review.getRecipe().getMainStuff();
        bread = review.getRecipe().getBread();

        List<RecipeCheese> recipeCheeses = review.getRecipe().getRecipeCheeses();
        recipeCheeses.forEach(rc -> cheeseIds.add(rc.getCheese().getId()));

        List<RecipeSauce> recipeSauces = review.getRecipe().getRecipeSauces();
        recipeSauces.forEach(rs -> sauceIds.add(rs.getSauce().getId()));

        List<RecipeVegetable> recipeVegetables = review.getRecipe().getRecipeVegetables();
        recipeVegetables.forEach(rv -> vegetableIds.add(rv.getVegetable().getId()));

        hearts = review.getHeartCounts();
        comment = review.getComment();
        score = review.getScore();
    }

}
