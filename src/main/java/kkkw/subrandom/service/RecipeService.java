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
import kkkw.subrandom.exceptions.RecipeNotFoundException;
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

        Optional<Recipe> result = findDuplicateRecipe(recipe);
        return result.orElseGet(() -> recipeRepository.save(recipe));
    }

    public Optional<Recipe> findDuplicateRecipe(Recipe recipe) {

        List<Recipe> recipeDuplicates = new ArrayList<>();
        recipeRepository.findByBreadAndMainStuff(recipe.getBread(), recipe.getMainStuff())
                .forEach(r -> recipeDuplicates.add(r));
        if (recipeDuplicates.isEmpty()) return Optional.empty();

        Set<Long> newRecipeSauces = new HashSet<>();
        Set<Long> SaucesRetain = new HashSet<>();
        recipe.getRecipeSauces().forEach(rs -> newRecipeSauces.add(rs.getSauce().getId()));
        recipe.getRecipeSauces().forEach(rs -> SaucesRetain.add(rs.getSauce().getId()));

        int size = recipeDuplicates.size();
        for (int i = 0; i < size; i++) {
            Set<Long> duplicateListSauces = new HashSet<>();
            recipeDuplicates.get(i)
                    .getRecipeSauces()
                    .forEach(rs -> duplicateListSauces.add(rs.getSauce().getId()));
            SaucesRetain.retainAll(duplicateListSauces);
            if (!(SaucesRetain.size() == newRecipeSauces.size() && SaucesRetain.size() == duplicateListSauces.size())) {
                recipeDuplicates.remove(i);
                i--;
                size--;
            }
        }
        if (recipeDuplicates.isEmpty()) {
            return Optional.empty();
        }


        Set<Long> newRecipeCheeses = new HashSet<>();
        Set<Long> CheesesRetain = new HashSet<>();
        recipe.getRecipeCheeses().forEach(rc -> newRecipeCheeses.add(rc.getCheese().getId()));
        recipe.getRecipeCheeses().forEach(rc -> CheesesRetain.add(rc.getCheese().getId()));

        size = recipeDuplicates.size();
        for (int i = 0; i < size; i++) {
            Set<Long> duplicateListCheeses = new HashSet<>();
            recipeDuplicates.get(i)
                    .getRecipeCheeses()
                    .forEach(rc -> duplicateListCheeses.add(rc.getCheese().getId()));
            CheesesRetain.retainAll(duplicateListCheeses);
            if (!(CheesesRetain.size() == newRecipeCheeses.size() && CheesesRetain.size() == duplicateListCheeses.size())) {
                recipeDuplicates.remove(i);
                i--;
                size--;
            }
        }
        if (recipeDuplicates.isEmpty()) {
            return Optional.empty();
        }


        Set<Long> newRecipeVegetables = new HashSet<>();
        Set<Long> VegetablesRetain = new HashSet<>();
        recipe.getRecipeVegetables().forEach(rv -> newRecipeVegetables.add(rv.getVegetable().getId()));
        recipe.getRecipeVegetables().forEach(rv -> VegetablesRetain.add(rv.getVegetable().getId()));

        size = recipeDuplicates.size();
        for (int i = 0; i < size; i++) {
            Set<Long> duplicateListVegetables = new HashSet<>();
            recipeDuplicates.get(i)
                    .getRecipeVegetables()
                    .forEach(rv -> duplicateListVegetables.add(rv.getVegetable().getId()));
            VegetablesRetain.retainAll(duplicateListVegetables);
            if (!(VegetablesRetain.size() == newRecipeVegetables.size() && VegetablesRetain.size() == duplicateListVegetables.size())) {
                recipeDuplicates.remove(i);
                i--;
                size--;
            }
        }
        if (recipeDuplicates.isEmpty()) {
            return Optional.empty();
        } else return Optional.ofNullable(recipeDuplicates.get(0));
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

        Set<Long> sauces = new HashSet<>();
        for (int i = 0; i < random.nextInt(4); i++) {
            int newInt = random.nextInt(14)+1;
            if (sauces.contains((long) newInt)) i--;
            sauces.add((long) newInt);
        }

        Set<Long> cheeses = new HashSet<>();
        for (int i = 0; i < random.nextInt(3); i++) {
            int newInt = random.nextInt(3)+1;
            if(cheeses.contains((long) newInt)) i--;
            cheeses.add((long) newInt);
        }

        Set<Long> vegetables = new HashSet<>();
        for (int i = 0; i < random.nextInt(9); i++) {
            int newInt = random.nextInt(8)+1;
            if (vegetables.contains((long) newInt)) i--;
            vegetables.add((long) newInt);
        }

        RecipeCreateDto result = RecipeCreateDto.builder()
                .bread(pickedBread)
                .mainStuff(pickedMainStuff)
                .sauceIds(sauces.stream().toList())
                .cheeseIds(cheeses.stream().toList())
                .vegetableIds(vegetables.stream().toList())
                .build();

        return result;
    }

    public List<Recipe> findSavedRecipesByMemberId(Long memberId) {

        List<Recipe> result = new ArrayList<>();
        saveRepository.findByMemberId(memberId).forEach(s -> result.add(s.getRecipe()));
        return result;
    }

    public Recipe findRecipeByIdOrThrow(Long recipeId) {
        Optional<Recipe> optionalRecipe = recipeRepository
                .findById(recipeId);

        if (optionalRecipe.isEmpty()){
            throw new RecipeNotFoundException();
        }

        return optionalRecipe.get();
    }
}