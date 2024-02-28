package kkkw.subrandom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kkkw.subrandom.domain.recipe.Recipe;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    @JsonIgnore
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @NotNull
    @JsonIgnore
    private Recipe recipe;

    @NotNull
    private Float score;

    @Column(length = 2400)
    private String comment;

    @Builder
    public Review(Long id, Member member, Recipe recipe, Float score, String comment) {
        this.id = id;
        this.member = member;
        this.recipe = recipe;
        this.score = score;
        this.comment = comment;
    }


}
