package crepe.backend.domain.project.exception;

import crepe.backend.global.exception.BusinessException;
import crepe.backend.global.response.ErrorCode;

public class NotAllowProjectException extends BusinessException {

    public NotAllowProjectException(){
        super(ErrorCode.PROJECT_NOT_ALLOW);
    }
}
