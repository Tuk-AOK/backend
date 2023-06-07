package crepe.backend.domain.branch.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchLogInfo {

    private UUID logUuid;
    private LocalDateTime logCreatedAt;

}
