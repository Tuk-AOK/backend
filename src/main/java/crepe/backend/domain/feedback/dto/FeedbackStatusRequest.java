package crepe.backend.domain.feedback.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FeedbackStatusRequest {

    private String feedbackStatus;
}
