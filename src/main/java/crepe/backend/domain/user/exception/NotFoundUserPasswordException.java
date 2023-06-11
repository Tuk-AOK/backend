package crepe.backend.domain.user.exception;

import crepe.backend.global.exception.BusinessException;
import crepe.backend.global.response.ErrorCode;

public class NotFoundUserPasswordException extends BusinessException {

        public NotFoundUserPasswordException() {
            super(ErrorCode.USER_NOT_FOUND_PASSWORD);
        }
}
