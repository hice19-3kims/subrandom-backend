package kkkw.subrandom.domain.recipe;

import jakarta.persistence.*;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCheese {

    @Id
    @GeneratedValue
    @Column(name = "recipe_cheese_id")
    private Long recipeCheeseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cheese_id")
    private Cheese cheese;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    //== 연관관계 편의 메서드 ==//
    public void setCheese(Recipe recipe) {
        this.recipe = recipe;
        recipe.getRecipeCheeses().add(this);
    }
}
