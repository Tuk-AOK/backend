package crepe.backend.domain.log.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class LogUuidInfo {
    private UUID logUuid;
    private LocalDateTime logCreatedAt;

    @Builder
    public LogUuidInfo(UUID logUuid, LocalDateTime logCreatedAt) {
        this.logUuid = logUuid;
        this.logCreatedAt = logCreatedAt;
    }
}
