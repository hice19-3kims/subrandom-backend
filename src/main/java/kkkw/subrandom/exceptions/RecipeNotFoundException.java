package kkkw.subrandom.exceptions;

public class RecipeNotFoundException extends BaseException {
    public RecipeNotFoundException() {
        super(RecipeErrorCode.RECIPE_NOT_FOUND);
    }
}
