package kkkw.subrandom.exceptions;

public class HeartNotFoundException extends BaseException {
    public HeartNotFoundException() {
        super(HeartErrorCode.HEART_NOT_FOUND);
    }
}
