package crepe.backend.domain.log.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class LogInfo {
    private UUID userUuid;
    private String logMessage;
    private LocalDateTime logCreatedAt;
    private List<ResourceInfo> resourceInfos;
    private List<LogFeedbackInfo> feedbackInfos;


    @Builder
    public LogInfo(UUID userUuid,
                   String logMessage,
                   LocalDateTime logCreatedAt,
                   List<ResourceInfo> resourceInfos,
                   List<LogFeedbackInfo> feedbackInfos) {

        this.userUuid = userUuid;
        this.logMessage = logMessage;
        this.logCreatedAt = logCreatedAt;
        this.resourceInfos = resourceInfos;
        this.feedbackInfos = feedbackInfos;

    }
}
