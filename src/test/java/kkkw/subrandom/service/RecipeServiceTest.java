package kkkw.subrandom.service;

import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Save;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    SauceRepository sauceRepository;
    @Mock
    CheeseRepository cheeseRepository;
    @Mock
    VegetableRepository vegetableRepository;
    @Mock
    SaveRepository saveRepository;

    @InjectMocks
    RecipeService recipeService;

    @Test
    @DisplayName("중복 없는 레시피 생성 성공")
    public void createRecipeSuccess() {
        /*
        * given
        * */
        RecipeCreateDto recipeCreateDto = RecipeCreateDto.builder()
                .bread("flat")
                .mainStuff("bbq")
                .cheeseIds(List.of(1L, 2L))
                .sauceIds(List.of(1L, 2L))
                .vegetableIds(List.of(1L, 2L))
                .build();
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();
        //새로 만들어지는 레시피 dto 생성.

        Recipe recipe = Recipe.builder()
                .id(1L)
                .bread("flat")
                .mainStuff("bbq")
                .build();
        RecipeCheese.CreateRecipeCheese(cheese1, recipe);
        RecipeCheese.CreateRecipeCheese(cheese2, recipe);
        RecipeSauce.CreateRecipeSauce(sauce1, recipe);
        RecipeSauce.CreateRecipeSauce(sauce2, recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetable1, recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetable2, recipe);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        //생성 시 만들어질 레시피 설정.

        when(recipeRepository.findByBreadAndMainStuff(any(), any())).then(invocation -> {
            System.out.println("This is mock recipeRepository.");
            return Collections.emptyList();
        });
        //중복이 없는 경우로 설정. 빵, 메뉴가 같은 레시피도 없는 것으로 설정.

        when(cheeseRepository.findById(1L)).then(invocation -> {
            System.out.println("This is mock cheeseRepository.");
            return Optional.ofNullable(cheese1);
        });
        when(cheeseRepository.findById(2L)).then(invocation -> {
            System.out.println("This is mock cheeseRepository.");
            return Optional.ofNullable(cheese2);
        });
        when(sauceRepository.findById(1L)).then(invocation -> {
            System.out.println("This is mock sauceRepository.");
            return Optional.ofNullable(sauce1);
        });
        when(sauceRepository.findById(2L)).then(invocation -> {
            System.out.println("This is mock sauceRepository.");
            return Optional.ofNullable(sauce2);
        });
        when(vegetableRepository.findById(1L)).then(invocation -> {
            System.out.println("This is mock vegetableRepository.");
            return Optional.ofNullable(vegetable1);
        });
        when(vegetableRepository.findById(2L)).then(invocation -> {
            System.out.println("This is mock vegetableRepository.");
            return Optional.ofNullable(vegetable2);
        });
        //각 재료 레포지토리에서 올바른 객체를 찾아온다고 가정.

        /*
        * when
        * */
        recipeService.addRecipe(recipeCreateDto);

        /*
        * then
        * */
        verify(recipeRepository, Mockito.times(1)).save(any());
        verify(cheeseRepository, Mockito.times(2)).findById(any());
        verify(sauceRepository, Mockito.times(2)).findById(any());
        verify(vegetableRepository, Mockito.times(2)).findById(any());
        verify(recipeRepository, Mockito.times(1)).findByBreadAndMainStuff(any(), any());
        //서비스 로직을 수행하며, 각 레포지토리 메서드를 정상적인 횟수 실행함을 검사.
    }

    @Test
    @DisplayName("중복 레시피 존재")
    public void duplicateRecipe() throws Exception {
        /*
         * given
         * */
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();
        //재료들의 id 설정.

        RecipeCreateDto recipeCreateDto = RecipeCreateDto.builder()
                .bread("flat")
                .mainStuff("bbq")
                .cheeseIds(List.of(1L, 2L))
                .sauceIds(List.of(1L, 2L))
                .vegetableIds(List.of(1L, 2L))
                .build();
        //생성될 레시피의 정보 설정.

        Recipe duplicateRecipe = Recipe.builder().id(1L).bread("flat").mainStuff("bbq").build();
        RecipeCheese recipeCheese1 = RecipeCheese.builder().id(1L).cheese(cheese1).recipe(duplicateRecipe).build();
        duplicateRecipe.getRecipeCheeses().add(recipeCheese1);
        RecipeCheese recipeCheese2 = RecipeCheese.builder().id(2L).cheese(cheese2).recipe(duplicateRecipe).build();
        duplicateRecipe.getRecipeCheeses().add(recipeCheese2);
        RecipeSauce recipeSauce1 = RecipeSauce.builder().id(1L).sauce(sauce1).recipe(duplicateRecipe).build();
        duplicateRecipe.getRecipeSauces().add(recipeSauce1);
        RecipeSauce recipeSauce2 = RecipeSauce.builder().id(2L).sauce(sauce2).recipe(duplicateRecipe).build();
        duplicateRecipe.getRecipeSauces().add(recipeSauce2);
        RecipeVegetable recipeVegetable1 = RecipeVegetable.builder().id(1L).vegetable(vegetable1).recipe(duplicateRecipe).build();
        duplicateRecipe.getRecipeVegetables().add(recipeVegetable1);
        RecipeVegetable recipeVegetable2 = RecipeVegetable.builder().id(2L).vegetable(vegetable2).recipe(duplicateRecipe).build();
        duplicateRecipe.getRecipeVegetables().add(recipeVegetable2);
        //이미 존재하던 중복 레시피 설정.

        when(cheeseRepository.findById(1L)).then(invocation -> {
            System.out.println("This is mock cheeseRepository.");
            return Optional.ofNullable(cheese1);
        });
        when(cheeseRepository.findById(2L)).then(invocation -> {
            System.out.println("This is mock cheeseRepository.");
            return Optional.ofNullable(cheese2);
        });
        when(sauceRepository.findById(1L)).then(invocation -> {
            System.out.println("This is mock sauceRepository.");
            return Optional.ofNullable(sauce1);
        });
        when(sauceRepository.findById(2L)).then(invocation -> {
            System.out.println("This is mock sauceRepository.");
            return Optional.ofNullable(sauce2);
        });
        when(vegetableRepository.findById(1L)).then(invocation -> {
            System.out.println("This is mock vegetableRepository.");
            return Optional.ofNullable(vegetable1);
        });
        when(vegetableRepository.findById(2L)).then(invocation -> {
            System.out.println("This is mock vegetableRepository.");
            return Optional.ofNullable(vegetable2);
        });

        when(recipeRepository.findByBreadAndMainStuff(any(), any())).then(invocation -> {
            System.out.println("This is mock recipeRepository. Return duplicate recipe");
            return List.of(duplicateRecipe);
        });
        //중복 레시피의 후보를 정상적으로 찾는다고 가정.

        /*
        * when
        * */
        Recipe addedRecipe = recipeService.addRecipe(recipeCreateDto);

        /*
        * then
        * */
        verify(recipeRepository, Mockito.times(0)).save(any());
        //레시피 서비스의 중복 검사 레시피를 거치고, 중복 레시피가 있음을 알아내고 save를 실행시키지 않음을 검사.
    }

    @Test
    @DisplayName("랜덤 레시피 생성")
    public void randomRecipe() throws Exception {
        /*
        * given
        * */

        /*
        * when
        * */
        RecipeCreateDto generated = recipeService.generateRandomRecipe();

        /*
        * then
        * */
        System.out.println("generated.getBread() = " + generated.getBread());
        System.out.println("generated.getMainStuff() = " + generated.getMainStuff());
        System.out.println("generated.getCheeseIds().toString() = " + generated.getCheeseIds().toString());
        System.out.println("generated.getSauceIds().toString() = " + generated.getSauceIds().toString());
        System.out.println("generated.getVegetableIds().toString() = " + generated.getVegetableIds().toString());

        Assertions.assertInstanceOf(RecipeCreateDto.class, generated);
    }

    @Test
    @DisplayName("특정 멤버에게 저장된 레시피만 조회 성공")
    public void savedRecipe() throws Exception {
        /*
        * given
        * */
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Cheese cheese3 = Cheese.builder().id(3L).cheeseName("shred").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();
        //재료들의 id 설정.

        Recipe recipe1 = Recipe.builder().id(1L).bread("flat").mainStuff("bbq").build();
        RecipeCheese recipeCheese1 = RecipeCheese.builder().id(1L).cheese(cheese1).recipe(recipe1).build();
        recipe1.getRecipeCheeses().add(recipeCheese1);
        RecipeCheese recipeCheese2 = RecipeCheese.builder().id(2L).cheese(cheese2).recipe(recipe1).build();
        recipe1.getRecipeCheeses().add(recipeCheese2);
        RecipeSauce recipeSauce1 = RecipeSauce.builder().id(1L).sauce(sauce1).recipe(recipe1).build();
        recipe1.getRecipeSauces().add(recipeSauce1);
        RecipeSauce recipeSauce2 = RecipeSauce.builder().id(2L).sauce(sauce2).recipe(recipe1).build();
        recipe1.getRecipeSauces().add(recipeSauce2);
        RecipeVegetable recipeVegetable1 = RecipeVegetable.builder().id(1L).vegetable(vegetable1).recipe(recipe1).build();
        recipe1.getRecipeVegetables().add(recipeVegetable1);
        RecipeVegetable recipeVegetable2 = RecipeVegetable.builder().id(2L).vegetable(vegetable2).recipe(recipe1).build();
        recipe1.getRecipeVegetables().add(recipeVegetable2);
        //저장될 레시피 하나 설정.

        Recipe recipe2 = Recipe.builder().id(1L).bread("oat").mainStuff("teriyaki").build();
        RecipeCheese recipeCheese3 = RecipeCheese.builder().id(3L).cheese(cheese2).recipe(recipe2).build();
        recipe2.getRecipeCheeses().add(recipeCheese3);
        RecipeCheese recipeCheese4 = RecipeCheese.builder().id(4L).cheese(cheese3).recipe(recipe2).build();
        recipe2.getRecipeCheeses().add(recipeCheese4);
        RecipeSauce recipeSauce3 = RecipeSauce.builder().id(3L).sauce(sauce1).recipe(recipe2).build();
        recipe2.getRecipeSauces().add(recipeSauce3);
        RecipeSauce recipeSauce4 = RecipeSauce.builder().id(4L).sauce(sauce2).recipe(recipe2).build();
        recipe2.getRecipeSauces().add(recipeSauce4);
        RecipeVegetable recipeVegetable3 = RecipeVegetable.builder().id(3L).vegetable(vegetable1).recipe(recipe2).build();
        recipe2.getRecipeVegetables().add(recipeVegetable3);
        RecipeVegetable recipeVegetable4 = RecipeVegetable.builder().id(4L).vegetable(vegetable2).recipe(recipe2).build();
        recipe2.getRecipeVegetables().add(recipeVegetable4);
        //저장되지 않을 레시피 하나 설정.

        Member member = Member.builder()
                .id(1L)
                .email("aba@a.b")
                .password("123123")
                .name("aba")
                .build();
        //저장할 멤버 설정.

        Save save = Save.builder()
                .id(1L)
                .member(member)
                .recipe(recipe1)
                .build();
        //멤버가 recipe1을 저장하도록 설정.

        when(saveRepository.findByMemberId(1L)).then(invocation -> {
            System.out.println("This is mock saveRepository.");
            return List.of(save);
        });
        //올바른 save를 리턴한다고 가정.

        /*
        * when
        * */
        List<Recipe> savedRecipesByMemberId = recipeService.findSavedRecipesByMemberId(member.getId());

        /*
        * then
        * */
        System.out.println("savedRecipesByMemberId.get(0).getBread() = " + savedRecipesByMemberId.get(0).getBread());
        System.out.println("savedRecipesByMemberId.get(0).getMainStuff() = " + savedRecipesByMemberId.get(0).getMainStuff());
        savedRecipesByMemberId.get(0).getRecipeCheeses().forEach(recipeCheese -> System.out.println("recipeCheese.getCheese().getId() = " + recipeCheese.getCheese().getId()));
        savedRecipesByMemberId.get(0).getRecipeSauces().forEach(recipeSauce -> System.out.println("recipeSauce.getSauce().getId() = " + recipeSauce.getSauce().getId()));
        savedRecipesByMemberId.get(0).getRecipeVegetables().forEach(recipeVegetable -> System.out.println("recipeVegetable.getVegetable().getId() = " + recipeVegetable.getVegetable().getId()));

        Assertions.assertEquals(savedRecipesByMemberId.size(), 1);
        //레시피 서비스 로직의 결과, 올바른 크기의 리스트를 반환받음을 검사.

        Assertions.assertEquals(savedRecipesByMemberId.get(0).getId(), 1L);
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getBread(), "flat");
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getMainStuff(), "bbq");
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getRecipeCheeses().get(0).getCheese().getId(), 1L);
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getRecipeCheeses().get(1).getCheese().getId(), 2L);
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getRecipeSauces().get(0).getSauce().getId(), 1L);
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getRecipeSauces().get(1).getSauce().getId(), 2L);
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getRecipeVegetables().get(0).getVegetable().getId(), 1L);
        Assertions.assertEquals(savedRecipesByMemberId.get(0).getRecipeVegetables().get(1).getVegetable().getId(), 2L);
        //저장한 레시피의 정보가 정확함을 검사.
    }

    @Test
    @DisplayName("레시피 id로 조회 성공")
    public void findRecipeById() throws Exception {
        /*
        * given
        * */
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();
        //재료들의 id 설정.

        Recipe recipe = Recipe.builder().id(1L).bread("flat").mainStuff("bbq").build();
        RecipeCheese recipeCheese1 = RecipeCheese.builder().id(1L).cheese(cheese1).recipe(recipe).build();
        recipe.getRecipeCheeses().add(recipeCheese1);
        RecipeCheese recipeCheese2 = RecipeCheese.builder().id(2L).cheese(cheese2).recipe(recipe).build();
        recipe.getRecipeCheeses().add(recipeCheese2);
        RecipeSauce recipeSauce1 = RecipeSauce.builder().id(1L).sauce(sauce1).recipe(recipe).build();
        recipe.getRecipeSauces().add(recipeSauce1);
        RecipeSauce recipeSauce2 = RecipeSauce.builder().id(2L).sauce(sauce2).recipe(recipe).build();
        recipe.getRecipeSauces().add(recipeSauce2);
        RecipeVegetable recipeVegetable1 = RecipeVegetable.builder().id(1L).vegetable(vegetable1).recipe(recipe).build();
        recipe.getRecipeVegetables().add(recipeVegetable1);
        RecipeVegetable recipeVegetable2 = RecipeVegetable.builder().id(2L).vegetable(vegetable2).recipe(recipe).build();
        recipe.getRecipeVegetables().add(recipeVegetable2);
        //레시피 하나 설정.

        when(recipeRepository.findById(1L)).then(invocation -> {
            System.out.println("This is mock recipeRepository.");
            return Optional.of(recipe);
        });
        //올바른 레시피를 찾는 경우를 설정.

        /*
        * when
        * */
        Recipe findRecipe = recipeService.findRecipeByIdOrThrow(1L);

        /*
        * then
        * */
        Assertions.assertEquals(findRecipe.getId(), 1L);
    }

    @Test
    @DisplayName("레시피 id로 조회 실패")
    public void findRecipeByIdFail() throws Exception {
        /*
         * given
         * */
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();
        //재료들의 id 설정.

        Recipe recipe = Recipe.builder().id(1L).bread("flat").mainStuff("bbq").build();
        RecipeCheese recipeCheese1 = RecipeCheese.builder().id(1L).cheese(cheese1).recipe(recipe).build();
        recipe.getRecipeCheeses().add(recipeCheese1);
        RecipeCheese recipeCheese2 = RecipeCheese.builder().id(2L).cheese(cheese2).recipe(recipe).build();
        recipe.getRecipeCheeses().add(recipeCheese2);
        RecipeSauce recipeSauce1 = RecipeSauce.builder().id(1L).sauce(sauce1).recipe(recipe).build();
        recipe.getRecipeSauces().add(recipeSauce1);
        RecipeSauce recipeSauce2 = RecipeSauce.builder().id(2L).sauce(sauce2).recipe(recipe).build();
        recipe.getRecipeSauces().add(recipeSauce2);
        RecipeVegetable recipeVegetable1 = RecipeVegetable.builder().id(1L).vegetable(vegetable1).recipe(recipe).build();
        recipe.getRecipeVegetables().add(recipeVegetable1);
        RecipeVegetable recipeVegetable2 = RecipeVegetable.builder().id(2L).vegetable(vegetable2).recipe(recipe).build();
        recipe.getRecipeVegetables().add(recipeVegetable2);
        //레시피 하나 설정.

        when(recipeRepository.findById(2L)).then(invocation -> {
            System.out.println("This is mock recipeRepository.");
            return Optional.empty();
        });
        //레시피를 찾지 못한 경우를 설정.

        /*
         * when
         * */
        Assertions.assertThrows(RecipeNotFoundException.class, () -> recipeService.findRecipeByIdOrThrow(2L));
        //레시피를 찾지 못하면 올바른 예외가 발생함을 검사.
    }
}