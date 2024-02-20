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
public class Sauce {

    @Id
    @GeneratedValue
    @Column(name = "sauce_id")
    private Long id;

    @NotNull
    private String sauceName;

    @Builder
    public Sauce(Long id, String sauceName) {
        this.id = id;
        this.sauceName = sauceName;
    }
}
