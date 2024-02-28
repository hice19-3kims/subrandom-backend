package kkkw.subrandom.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    @NotNull
    private Long recipeId;

    @NotNull
    private Float score;

    private String comment;

}
