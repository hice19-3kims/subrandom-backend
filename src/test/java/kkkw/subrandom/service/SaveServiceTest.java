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
import kkkw.subrandom.exceptions.SaveNotFoundException;
import kkkw.subrandom.repository.SaveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveServiceTest {

    @Mock
    private SaveRepository saveRepository;

    @InjectMocks
    private SaveService saveService;

    @Test
    @DisplayName("레시피 저장")
    void addSave() {
        /*
        * given
        * */
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();
        //재료 정보 설정.

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
        //저장될 레시피 설정.

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
                .recipe(recipe)
                .build();
        //생성될 저장 정보 설정.

        when(saveRepository.save(any())).then(invocation -> {
            System.out.println("This is mock saveRepository.");
            return save;
        });
        //올바른 저장 정보가 생성됨을 가정.

        /*
         * when
         * */
        saveService.addSave(member, recipe);

        /*
         * then
         * */
        verify(saveRepository, Mockito.times(1)).save(any());
        //서비스의 로직에 따라, save 메서드가 한 번만 실행됨을 검사.
    }

    @Test
    void findSavesByMember() {
        /*
         * given
         * */
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();
        //재료 정보 설정.

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
        //저장될 레시피 설정.

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
                .recipe(recipe)
                .build();
        //생성될 저장 정보 설정.

        when(saveRepository.findByMemberId(1L)).then(invocation -> {
            System.out.println("This is mock saveRepository.");
            return List.of(save);
        });
        when(saveRepository.findByMemberId(2L)).then(invocation -> {
            System.out.println("This is mock saveRepository.");
            return List.of();
        });
        //올바른 멤버 id로 찾으면 저장 정보 리스트를 찾아내고, 그렇지 않으면 빈 리스트를 올바르게 반환함을 가정.


        /*
         * when
         * */
        List<Save> savesByMember = saveService.findSavesByMember(1L);

        /*
         * then
         * */
        Assertions.assertEquals(savesByMember.size(), 1);
        Assertions.assertEquals(savesByMember.get(0).getMember().getName(), "aba");
        Assertions.assertEquals(savesByMember.get(0).getMember().getEmail(), "aba@a.b");
        //서비스의 로직을 따라, repository의 메서드로 찾아낸 리스트를 올바르게 반환함을 검사.
        Assertions.assertThrows(SaveNotFoundException.class, () ->
                saveService.findSavesByMember(2L));
        //올바르지 않은 멤버 id로 찾으면 올바른 예외를 발생시킴을 검사.
    }
}