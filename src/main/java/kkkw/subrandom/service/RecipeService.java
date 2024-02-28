package kkkw.subrandom.service;


import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.domain.recipe.RecipeCheese;
import kkkw.subrandom.domain.recipe.RecipeSauce;
import kkkw.subrandom.domain.recipe.RecipeVegetable;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import kkkw.subrandom.dto.RecipeDto;
import kkkw.subrandom.repository.recipe.RecipeRepository;
import kkkw.subrandom.repository.recipechoice.CheeseRepository;
import kkkw.subrandom.repository.recipechoice.SauceRepository;
import kkkw.subrandom.repository.recipechoice.VegetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //기본값 조회가 성능이 좋음
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final SauceRepository sauceRepository;
    private final CheeseRepository cheeseRepository;
    private final VegetableRepository vegetableRepository;

    @Transactional
    public Recipe createRecipe(RecipeDto recipeDto) {
        Recipe recipe = Recipe.builder()
                .bread(recipeDto.getBread())
                .mainStuff(recipeDto.getMainStuff())
                .build();

        for (int i = 0; i < recipeDto.getSauceIds().size(); i++) {
            Sauce sauce = sauceRepository.findById(recipeDto.getSauceIds().get(i)).get();
            RecipeSauce.CreateRecipeSauce(sauce, recipe);
        }
        for (int i = 0; i < recipeDto.getCheeseIds().size(); i++) {
            Cheese cheese = cheeseRepository.findById(recipeDto.getCheeseIds().get(i)).get();
            RecipeCheese.CreateRecipeCheese(cheese, recipe);
        }
        for (int i = 0; i < recipeDto.getVegetableIds().size(); i++) {
            Vegetable vegetable = vegetableRepository.findById(recipeDto.getVegetableIds().get(i)).get();
            RecipeVegetable.CreateRecipeVegetable(vegetable, recipe);
        }

        return recipeRepository.save(recipe);

    }

}