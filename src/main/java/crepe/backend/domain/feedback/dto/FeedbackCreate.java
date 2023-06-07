package crepe.backend.domain.feedback.dto;

import crepe.backend.domain.feedback.domain.entity.FeedbackStatus;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FeedbackCreate {

    private Long userId;
    private Long branchId;
    private String message;
    private FeedbackStatus status;
}
