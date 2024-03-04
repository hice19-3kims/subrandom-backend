package kkkw.subrandom.exceptions;

public class ReviewNotFoundException extends BaseException {
    public ReviewNotFoundException() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
