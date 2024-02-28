package kkkw.subrandom.controller;

import jakarta.validation.Valid;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.dto.RecipeDto;
import kkkw.subrandom.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<Recipe> recipeCreateResponse(
            @Valid @RequestBody RecipeDto recipeDto
    ) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeDto));
    }
}
