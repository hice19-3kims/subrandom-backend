package kkkw.subrandom.domain.recipe.recipechoice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cheese {

    @Id
    @GeneratedValue
    @Column(name = "cheese_id")
    private Long id;

    @NotNull
    private String cheeseName;

    @Builder
    public Cheese(Long id, String cheeseName) {
        this.id = id;
        this.cheeseName = cheeseName;
    }
}
