package crepe.backend.domain.branch.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchRecentLogInfo {
    private UUID logUuid;
}
