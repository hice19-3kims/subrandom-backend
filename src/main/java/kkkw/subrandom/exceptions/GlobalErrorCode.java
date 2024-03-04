package kkkw.subrandom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    BAD_REQUEST_ERROR(404, "GLOBAL_400_1", "잘못된 요청입니다."),
    INVALID_HTTP_MESSAGE_BODY(404, "GLOBAL_400_2","HTTP 요청 바디의 형식이 잘못되었습니다."),
    ACCESS_DENIED_REQUEST(403,"GLOBAL_403","해당 요청에 접근 권한이 없습니다."),
    UNSUPPORTED_HTTP_METHOD(405, "GLOBAL_405","지원하지 않는 HTTP 메서드입니다."),
    SERVER_ERROR(500,"GLOBAL_500","서버 내부에서 알 수 없는 오류가 발생했습니다."),

    /* 이 5개 추가*/
    NOT_AUTHENTIATED_ERROR(401,"SECURITY_401","사용자가 인증되지 않았습니다."),
    INVALID_TOKEN(401,"TOKEN_401_1","토큰이 유효하지않습니다."),
    INVALID_SIGNATURE_TOKEN(401,"TOKEN_401_2","토큰의 Signature가 일치하지 않습니다."),
    EXPIRED_TOKEN(401,"TOKEN_401_3","토큰이 만료되었습니다."),
    NOT_MATCHED_TOKEN_TYPE(401,"TOKEN_401_4","토큰의 타입이 일치하지 않아 디코딩할 수 없습니다.");



    private final int httpStatus;
    private final String code;
    private final String message;
}
