package crepe.backend.domain.log.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LogInfo {
    private UUID userUuid;
    private String logMessage;
    private String logPreview;
    private LocalDateTime logCreatedAt;
    private List<ResourceInfo> resourceInfos;
}
