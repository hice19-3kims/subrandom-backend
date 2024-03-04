package kkkw.subrandom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode{
    MEMBER_NOT_FOUND(404, "MEMBER_404", "유저를 찾을 수 없습니다."),
    EMAIL_ALREADY_REGISTERED(404, "MEMBER_409","같은 이메일로 회원가입된 계정이 있습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
