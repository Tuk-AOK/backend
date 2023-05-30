package crepe.backend.domain.branch.dto;


import lombok.*;

import java.util.UUID;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchFeedbackInfo {
    private UUID userUuid;
    private String feedbackMessage;
    private UUID feedbackUserUuid;
    private UUID feedbackUuid;
}
