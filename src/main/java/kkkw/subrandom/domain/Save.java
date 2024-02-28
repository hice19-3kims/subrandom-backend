package kkkw.subrandom.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kkkw.subrandom.domain.recipe.Recipe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Save {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "member_id")
    @NotNull
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @NotNull
    private Recipe recipe;

    @Builder
    public Save(Long id, Member member, Recipe recipe) {
        this.id = id;
        this.member = member;
        this.recipe = recipe;
    }
}
