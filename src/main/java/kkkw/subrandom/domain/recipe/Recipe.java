package kkkw.subrandom.domain.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    @NotNull
    private String bread;

    @NotNull
    private String mainStuff;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Builder.Default
    private List<RecipeSauce> recipeSauces = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Builder.Default
    private List<RecipeCheese> recipeCheeses = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Builder.Default
    private List<RecipeVegetable> recipeVegetables = new ArrayList<>();

    @Builder
    public Recipe(Long id, String bread, String mainStuff,
                  List<RecipeSauce> recipeSauces,
                  List<RecipeCheese> recipeCheeses,
                  List<RecipeVegetable> recipeVegetables) {
        this.id = id;
        this.bread = bread;
        this.mainStuff = mainStuff;
        this.recipeSauces = recipeSauces;
        this.recipeCheeses = recipeCheeses;
        this.recipeVegetables = recipeVegetables;
    }

    //== 연관관계 편의 메서드 ==//
    public void addRecipeCheese(RecipeCheese recipeCheese) {
        this.recipeCheeses.add(recipeCheese);
        recipeCheese.setRecipe(this);
    }

    public void addRecipeSauce(RecipeSauce recipeSauce) {
        this.recipeSauces.add(recipeSauce);
        recipeSauce.setRecipe(this);
    }

    public void addRecipeVegetable(RecipeVegetable recipeVegetable) {
        this.recipeVegetables.add(recipeVegetable);
        recipeVegetable.setRecipe(this);
    }
}