package kkkw.subrandom.domain.recipe;

import jakarta.persistence.*;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeSauce {

    @Id
    @GeneratedValue
    @Column(name = "recipe_sauce_id")
    private Long recipeSauceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sauce_id")
    private Sauce sauce;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    //== 연관관계 편의 메서드 ==//
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.getRecipeSauces().add(this);
    }
}
