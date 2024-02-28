package kkkw.subrandom.domain.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeSauce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_sauce_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sauce_id")
    private Sauce sauce;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeSauce(Long id, Sauce sauce, Recipe recipe) {
        this.id = id;
        this.sauce = sauce;
        this.recipe = recipe;
    }

    //==생성 메서드==//
    public static RecipeSauce CreateRecipeSauce (Sauce sauce, Recipe recipe) {
        RecipeSauce  recipeSauce = new RecipeSauce();
        recipeSauce.sauce = sauce;
        recipeSauce.recipe = recipe;
        recipe.getRecipeSauces().add(recipeSauce);
        return recipeSauce;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
