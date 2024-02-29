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

import java.util.*;

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

    public RecipeCreateDto generateRandomRecipe() {
        Random random = new Random();

        List<String> breads = new ArrayList<>();
        breads.add(0, "wheat");
        breads.add(1, "oat");
        breads.add(2, "white");
        breads.add(3, "hearty");
        breads.add(4, "parmasan");
        breads.add(5, "flat");

        List<String> mainStuffs = new ArrayList<>();
        mainStuffs.add(0, "egg");
        mainStuffs.add(1, "ham");
        mainStuffs.add(2, "tuna");
        mainStuffs.add(3, "blt");
        mainStuffs.add(4, "bmt");
        mainStuffs.add(5, "veggie");
        mainStuffs.add(6, "chicken slice");
        mainStuffs.add(7, "club");
        mainStuffs.add(8, "rotisserie");
        mainStuffs.add(9, "roast chicken");
        mainStuffs.add(10, "teriyaki");
        mainStuffs.add(11, "spicy italian");
        mainStuffs.add(12, "pulled pork");
        mainStuffs.add(13, "steak");
        mainStuffs.add(14, "cba");
        mainStuffs.add(15, "shrimp");
        mainStuffs.add(16, "kbbq");

        String pickedBread = breads.get(random.nextInt(6));
        String pickedMainStuff = mainStuffs.get(random.nextInt(17));

        int number_of_sauce = random.nextInt(4);
        List<Long> pickedSauces = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        for (int i = 0; i < number_of_sauce; i++) {
            Integer newInt = random.nextInt(14)+1;
            if(set1.contains(newInt) == false){
                set1.add(newInt);
                pickedSauces.add((long) newInt);
            }
        }

        int number_of_cheese = random.nextInt(3);
        List<Long> pickedCheeses = new ArrayList<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < number_of_cheese; i++) {
            Integer newInt = random.nextInt(3)+1;
            if(set2.contains(newInt) == false){
                set2.add(newInt);
                pickedCheeses.add((long) newInt);
            }
        }

        int number_of_vegetable = random.nextInt(9);
        List<Long> pickedVegetables = new ArrayList<>();
        Set<Integer> set3 = new HashSet<>();
        for (int i = 0; i < number_of_vegetable; i++) {
            Integer newInt = random.nextInt(8)+1;
            if(set3.contains(newInt) == false){
                set3.add(newInt);
                pickedVegetables.add((long) newInt);
            }
        }

        RecipeCreateDto result = RecipeCreateDto.builder()
                .bread(pickedBread)
                .mainStuff(pickedMainStuff)
                .sauceIds(pickedSauces)
                .cheeseIds(pickedCheeses)
                .vegetableIds(pickedVegetables)
                .build();

        return result;
    }

}