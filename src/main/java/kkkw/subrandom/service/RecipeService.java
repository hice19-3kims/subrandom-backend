package kkkw.subrandom.service;


import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.domain.recipe.RecipeCheese;
import kkkw.subrandom.domain.recipe.RecipeSauce;
import kkkw.subrandom.domain.recipe.RecipeVegetable;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import kkkw.subrandom.dto.RecipeCreateDto;
import kkkw.subrandom.repository.SaveRepository;
import kkkw.subrandom.repository.recipe.RecipeRepository;
import kkkw.subrandom.repository.recipechoice.CheeseRepository;
import kkkw.subrandom.repository.recipechoice.SauceRepository;
import kkkw.subrandom.repository.recipechoice.VegetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true) //기본값 조회가 성능이 좋음
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final SauceRepository sauceRepository;
    private final CheeseRepository cheeseRepository;
    private final VegetableRepository vegetableRepository;
    private final SaveRepository saveRepository;

    @Transactional
    public Recipe addRecipe(RecipeCreateDto recipeCreateDto) {
        Recipe recipe = Recipe.builder()
                .bread(recipeCreateDto.getBread())
                .mainStuff(recipeCreateDto.getMainStuff())
                .build();

        for (int i = 0; i < recipeCreateDto.getSauceIds().size(); i++) {
            Sauce sauce = sauceRepository.findById(recipeCreateDto.getSauceIds().get(i)).get();
            RecipeSauce.CreateRecipeSauce(sauce, recipe);
        }
        for (int i = 0; i < recipeCreateDto.getCheeseIds().size(); i++) {
            Cheese cheese = cheeseRepository.findById(recipeCreateDto.getCheeseIds().get(i)).get();
            RecipeCheese.CreateRecipeCheese(cheese, recipe);
        }
        for (int i = 0; i < recipeCreateDto.getVegetableIds().size(); i++) {
            Vegetable vegetable = vegetableRepository.findById(recipeCreateDto.getVegetableIds().get(i)).get();
            RecipeVegetable.CreateRecipeVegetable(vegetable, recipe);
        }

        return recipeRepository.save(recipe);
    }

    public List<Recipe> findSavedRecipesByMemberId(Long memberId) {

        List<Recipe> result = new ArrayList<>();
        saveRepository.findByMemberId(memberId).forEach(s -> result.add(s.getRecipe()));
        return result;
    }

}