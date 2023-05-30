package crepe.backend.domain.log.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LogUuidInfo {
    private UUID logUuid;
    private LocalDateTime logCreatedAt;
}
