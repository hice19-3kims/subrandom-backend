package kkkw.subrandom.domain.recipe;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long recipeId;

    @NotNull
    private String bread;

    @NotNull
    private String mainStuff;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeSauce> recipeSauces = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeCheese> recipeCheeses = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeVegetable> recipeVegetables = new ArrayList<>();
}