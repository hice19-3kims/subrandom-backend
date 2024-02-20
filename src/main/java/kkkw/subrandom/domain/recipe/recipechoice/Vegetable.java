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
public class Vegetable {

    @Id
    @GeneratedValue
    @Column(name = "vegetable_id")
    private Long id;

    @NotNull
    private String VegetableName;

    @Builder
    public Vegetable(Long id, String vegetableName) {
        this.id = id;
        VegetableName = vegetableName;
    }
}
