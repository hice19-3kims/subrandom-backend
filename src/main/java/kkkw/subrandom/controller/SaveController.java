package kkkw.subrandom.controller;

import jakarta.validation.Valid;
import kkkw.subrandom.domain.Save;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.dto.RecipeCreateDto;
import kkkw.subrandom.service.RecipeService;
import kkkw.subrandom.service.SaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class SaveController {

    private final RecipeService recipeService;
    private final SaveService saveService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Save> saveAdd(
            @Valid @RequestBody RecipeCreateDto recipeCreateDto
    ) {
        Recipe recipe = recipeService.addRecipe(recipeCreateDto);
        return ResponseEntity.ok(saveService.addMySave(recipe));
    }
}
