package kkkw.subrandom.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kkkw.subrandom.domain.recipe.Recipe;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Save {

    @Id
    @GeneratedValue
    @Column(name = "save_id")
    private Long saveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")
    @NotNull
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @NotNull
    private Recipe recipe;
}
