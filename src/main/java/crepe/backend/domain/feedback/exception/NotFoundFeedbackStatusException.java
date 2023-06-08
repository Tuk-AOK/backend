package crepe.backend.domain.feedback.exception;

import crepe.backend.global.exception.BusinessException;
import crepe.backend.global.response.ErrorCode;

public class NotFoundFeedbackStatusException extends BusinessException {

    public NotFoundFeedbackStatusException()
    {
        super(ErrorCode.FEEDBACK_NOT_FOUND_STATUS_ERROR);
    }
}
