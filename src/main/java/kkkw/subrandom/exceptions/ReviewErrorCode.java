package kkkw.subrandom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode{
    REVIEW_NOT_FOUND(404, "REVIEW_404", "리뷰를 찾을 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
