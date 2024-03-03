package kkkw.subrandom.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDto {

    @NotNull
    private Long recipeId;

    @NotNull
    private Float score;

    private String comment;

}
