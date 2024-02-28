package kkkw.subrandom.domain.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeCheese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_cheese_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cheese_id")
    private Cheese cheese;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeCheese(Long id, Cheese cheese, Recipe recipe) {
        this.id = id;
        this.cheese = cheese;
        this.recipe = recipe;
    }

    //==생성 메서드==//
    public static RecipeCheese CreateRecipeCheese (Cheese cheese, Recipe recipe) {
        RecipeCheese recipeCheese = new RecipeCheese();
        recipeCheese.cheese = cheese;
        recipeCheese.recipe = recipe;
        recipe.getRecipeCheeses().add(recipeCheese);
        return recipeCheese;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
