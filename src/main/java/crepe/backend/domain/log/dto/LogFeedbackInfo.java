package crepe.backend.domain.log.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
public class LogFeedbackInfo {
    private UUID userUuid;
    private String feedbackMessage;
    private UUID feedbackUuid;

    @Builder
    public LogFeedbackInfo(UUID feedbackUserUuid,
                           String feedbackMessage,
                           UUID feedbackUuid) {
        this.userUuid = feedbackUserUuid;
        this.feedbackMessage = feedbackMessage;
        this.feedbackUuid = feedbackUuid;
    }
}
