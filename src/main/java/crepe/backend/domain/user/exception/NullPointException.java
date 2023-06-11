package crepe.backend.domain.user.exception;

import crepe.backend.global.exception.BusinessException;
import crepe.backend.global.response.ErrorCode;

public class NullPointException extends BusinessException {

    public NullPointException() {
        super(ErrorCode.USER_NULL_POINT);
    }
}
