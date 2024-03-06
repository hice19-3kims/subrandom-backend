package kkkw.subrandom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kkkw.subrandom.domain.recipe.Recipe;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull
    private Long heartCounts;

    @NotNull
    private LocalDateTime date;


    @Builder
    public Review(Long id, Member member, Recipe recipe, Float score, String comment, Long heartCounts, LocalDateTime date) {
        this.id = id;
        this.member = member;
        this.recipe = recipe;
        this.score = score;
        this.comment = comment;
        this.heartCounts = heartCounts;
        this.date = date;
    }

    public void countHearts() {
        this.heartCounts++;
    }

    public void cancelHearts(){
        this.heartCounts--;
    }
}
