package crepe.backend.domain.branch.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@NoArgsConstructor
public class BranchFeedbackInfo {
    private UUID userUuid;
    private String feedbackMessage;
    private UUID feedbackUuid;

    @Builder
    public BranchFeedbackInfo(UUID feedbackUserUuid,
                              String feedbackMessage,
                              UUID feedbackUuid) {
        this.userUuid = feedbackUserUuid;
        this.feedbackMessage = feedbackMessage;
        this.feedbackUuid = feedbackUuid;
    }
}
