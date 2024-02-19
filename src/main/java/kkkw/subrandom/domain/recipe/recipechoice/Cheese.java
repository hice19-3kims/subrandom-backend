package kkkw.subrandom.domain.recipe.recipechoice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cheese {

    @Id
    @GeneratedValue
    @Column(name = "cheese_id")
    private Long cheeseId;

    @NotNull
    private String cheeseName;
}
