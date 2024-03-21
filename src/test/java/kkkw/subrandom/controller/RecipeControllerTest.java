package kkkw.subrandom.controller;

import kkkw.subrandom.dto.RecipeCreateDto;
import kkkw.subrandom.service.RecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @DisplayName("랜덤 레시피 생성")
    @Test
    @WithMockUser("tester")
    public void generateRecipe() throws Exception {
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
        //랜덤으로 생성될 레시피 설정.

        when(recipeService.generateRandomRecipe()).thenReturn(recipeCreateDto);
        //서비스에서 랜덤으로 레시피를 생성하면 위의 레시피가 나온다고 가정.

        RecipeCreateDto recipeCreateDto1 = recipeService.generateRandomRecipe();
        System.out.println("recipeCreateDto1 = " + recipeCreateDto1.getBread());

        /*
         * when then
         * */
        mockMvc.perform(get("/api/recipe/roulette"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bread").value("flat"))
                .andExpect(jsonPath("$.mainStuff").value("bbq"))
                .andExpect(jsonPath("$.sauceIds[0]").value(1))
                .andExpect(jsonPath("$.sauceIds[1]").value(2))
                .andExpect(jsonPath("$.cheeseIds[0]").value(1))
                .andExpect(jsonPath("$.cheeseIds[1]").value(2))
                .andExpect(jsonPath("$.vegetableIds[0]").value(1))
                .andExpect(jsonPath("$.vegetableIds[1]").value(2))
                .andDo(print());
        //랜덤으로 생성된 레시피가 정상적으로 리턴됨을 검사.
    }
}