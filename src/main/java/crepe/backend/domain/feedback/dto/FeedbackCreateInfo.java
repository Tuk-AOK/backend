package crepe.backend.domain.feedback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class FeedbackCreateInfo {

    private UUID feedbackUuid;

    @Builder
    public FeedbackCreateInfo(UUID feedbackUuid)
    {
        this.feedbackUuid = feedbackUuid;
    }
}
