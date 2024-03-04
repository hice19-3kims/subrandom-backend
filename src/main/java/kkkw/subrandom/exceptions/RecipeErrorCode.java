package kkkw.subrandom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecipeErrorCode implements BaseErrorCode {
    RECIPE_NOT_FOUND(404, "RECIPE_404", "레시피를 찾을 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
