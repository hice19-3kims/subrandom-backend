package kkkw.subrandom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeartErrorCode implements BaseErrorCode {
    HEART_NOT_FOUND(404, "HEART_404", "좋아요 누른 항목이 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
