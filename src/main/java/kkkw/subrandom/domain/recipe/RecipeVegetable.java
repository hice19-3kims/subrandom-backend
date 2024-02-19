package kkkw.subrandom.domain.recipe;

import jakarta.persistence.*;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeVegetable {

    @Id
    @GeneratedValue
    @Column(name = "recipe_vegetable_id")
    private Long recipeVegetableId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vegetable_id")
    private Vegetable vegetable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    //== 연관관계 편의 메서드 ==//
    public void setVegetable(Vegetable vegetable) {
        this.recipe = recipe;
        recipe.getRecipeVegetables().add(this);
    }
}
