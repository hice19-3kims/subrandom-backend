package kkkw.subrandom.controller;

import jakarta.validation.Valid;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.dto.RecipeCreateDto;
import kkkw.subrandom.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<Recipe> recipeAdd(
            @Valid @RequestBody RecipeCreateDto recipeCreateDto
    ) {
        return ResponseEntity.ok(recipeService.addRecipe(recipeCreateDto));
    }

    @GetMapping("/roulette")
    public ResponseEntity<RecipeCreateDto> recipeRoulette() {
        return ResponseEntity.ok(recipeService.generateRandomRecipe());
    }
}
