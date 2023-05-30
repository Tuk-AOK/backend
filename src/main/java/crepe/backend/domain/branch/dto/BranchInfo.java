package crepe.backend.domain.branch.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchInfo {
    private Long branchId;
    private String branchName;
}
