package kkkw.subrandom.repository.recipe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @DisplayName("레시피 생성")
    void createRecipe() {

    }

    @Test
    @DisplayName("빵, 재료로 조회")
    void findByBreadAndMainStuff() {

    }
}