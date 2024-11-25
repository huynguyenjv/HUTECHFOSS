package olp.hutechfoss.provideservice.exception;


import lombok.Getter;
import olp.hutechfoss.provideservice.error.ErrorCode;

@Getter
public class AppException extends RuntimeException {

    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
    }
}
