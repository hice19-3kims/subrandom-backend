package kkkw.subrandom.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kkkw.subrandom.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @NotNull
    private Recipe recipe;

    @NotNull
    private Float score;

    @Column(length = 2400)
    private String comment;
}
