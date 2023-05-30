package crepe.backend.domain.project.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProjectBranchInfo {
    private Long branchId;
    private UUID branchUuid;
    private String branchName;
}
