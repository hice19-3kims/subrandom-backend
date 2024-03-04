package kkkw.subrandom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SaveErrorCode implements BaseErrorCode {
    SAVE_NOT_FOUND(404, "SAVE_404", "저장한 항목이 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
