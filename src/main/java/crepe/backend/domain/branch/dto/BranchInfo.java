package crepe.backend.domain.branch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class BranchInfo {
    private Long branchId;
    private String branchName;



    @Builder
    public BranchInfo(Long branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }
}
