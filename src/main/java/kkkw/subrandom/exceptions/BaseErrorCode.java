package kkkw.subrandom.exceptions;

public interface BaseErrorCode {
    public String getCode();
    public String getMessage();
    public int getHttpStatus();
}
