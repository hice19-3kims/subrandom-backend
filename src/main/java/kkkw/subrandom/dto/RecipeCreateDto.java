package kkkw.subrandom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreateDto {

    @NotNull
    private String bread;

    @NotNull
    private String mainStuff;

    @NotNull
    private List<Long> sauceIds;

    @NotNull
    private List<Long> cheeseIds;

    @NotNull
    private List<Long> vegetableIds;
}
