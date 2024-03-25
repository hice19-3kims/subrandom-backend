package kkkw.subrandom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kkkw.subrandom.domain.Authority;
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
import kkkw.subrandom.service.MemberService;
import kkkw.subrandom.service.RecipeService;
import kkkw.subrandom.service.SaveService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(SaveController.class)
@Slf4j
class SaveControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;
    @MockBean
    private SaveService saveService;
    @MockBean
    private MemberService memberService;

    Authority authority = Authority.builder()
            .authorityName("ROLE_USER")
            .build();

    Member member = Member.builder()
            .id(1L).name("aaa").email("aaa@a.com").password("1111")
            .authorities(Collections.singleton(authority))
            .activated(true)
            .build();

    Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
    Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
    Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
    Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
    Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();

    @DisplayName("저장 정보 생성")
    @Test
    @WithMockUser("aaa")
    public void asdf() throws Exception {
        /*
         * given
         * */
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

        RecipeCreateDto recipeCreateDto = RecipeCreateDto.builder()
                .bread("flat")
                .mainStuff("bbq")
                .cheeseIds(List.of(1L, 2L))
                .sauceIds(List.of(1L, 2L))
                .vegetableIds(List.of(1L, 2L))
                .build();
        String requestJson = mapper.writeValueAsString(recipeCreateDto);

        Save save = Save.builder()
                .id(1L)
                .member(member)
                .recipe(recipe)
                .build();

        when(recipeService.addRecipe(any())).thenReturn(recipe);
        when(memberService.findMyMemberWithAuthorities()).thenReturn(member);
        when(saveService.addSave(member, recipe)).thenReturn(save);

        /*
         * when then
         * */
        mockMvc.perform(post("/api/recipe/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.member.id").value(1))
                .andExpect(jsonPath("$.recipe.id").value(1))
                .andExpect(jsonPath("$.recipe.recipeSauces", hasSize(2)))
                .andExpect(jsonPath("$.recipe.recipeCheeses", hasSize(2)))
                .andExpect(jsonPath("$.recipe.recipeVegetables", hasSize(1)))
                .andDo(print());
    }
}
