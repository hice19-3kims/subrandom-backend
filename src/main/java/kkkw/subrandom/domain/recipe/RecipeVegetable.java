package kkkw.subrandom.domain.recipe;

import jakarta.persistence.*;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeVegetable {

    @Id
    @GeneratedValue
    @Column(name = "recipe_vegetable_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vegetable_id")
    private Vegetable vegetable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeVegetable(Long id, Vegetable vegetable, Recipe recipe) {
        this.id = id;
        this.vegetable = vegetable;
        this.recipe = recipe;
    }

    //==생성 메서드==//
    public static RecipeVegetable recipeVegetable(Long id, Vegetable vegetable, Recipe recipe) {
        RecipeVegetable recipeVegetable = new RecipeVegetable();
        recipeVegetable.id = id;
        recipeVegetable.vegetable = vegetable;
        recipeVegetable.recipe = recipe;
        recipe.getRecipeVegetables().add(recipeVegetable);
        return recipeVegetable;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
