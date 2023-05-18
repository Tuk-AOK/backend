package crepe.backend.domain.branch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class BranchLogInfo {

    private UUID logUuid;
    private String logMessage;

    @Builder
    public BranchLogInfo(UUID logUuid, String logMessage)
    {
        this.logUuid = logUuid;
        this.logMessage = logMessage;
    }
}
