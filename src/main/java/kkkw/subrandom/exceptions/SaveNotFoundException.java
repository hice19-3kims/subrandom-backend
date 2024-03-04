package kkkw.subrandom.exceptions;

public class SaveNotFoundException extends BaseException {
    public SaveNotFoundException() {
        super(SaveErrorCode.SAVE_NOT_FOUND);
    }
}
