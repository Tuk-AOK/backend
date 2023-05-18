package crepe.backend.domain.branch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class BranchCreateInfo {

    private UUID branchUuid;

    @Builder
    public BranchCreateInfo(UUID branchUuid)
    {
        this.branchUuid = branchUuid;
    }

}