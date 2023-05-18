package crepe.backend.domain.branch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class BranchRecentLogInfo {

    private UUID logUuid;

    @Builder
    public BranchRecentLogInfo(UUID logUuid) {
        this.logUuid = logUuid;
    }
}
