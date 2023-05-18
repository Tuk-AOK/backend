package crepe.backend.domain.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProjectBranchInfo {

    private String branchName;
    private UUID branchUuid;

    @Builder
    public ProjectBranchInfo(String branchName, UUID branchUuid) {
        this.branchName = branchName;
        this.branchUuid = branchUuid;
    }
}
