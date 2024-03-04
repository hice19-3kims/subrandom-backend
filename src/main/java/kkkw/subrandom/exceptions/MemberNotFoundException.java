package kkkw.subrandom.exceptions;

public class MemberNotFoundException extends BaseException {
    public MemberNotFoundException() {
        super(MemberErrorCode.MEMBER_NOT_FOUND);
    }
}
