package crepe.backend.domain.branch.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchRecentLogResourceInfoList {

    List<BranchRecentLogResourceInfo> recentResource = new ArrayList<>();

    public void addAllBranchRecentLogResourceInfo(List<BranchRecentLogResourceInfo> branchRecentLogResourceInfoList)
    {
            this.recentResource.addAll(branchRecentLogResourceInfoList);
    }

    public void addBranchRecentLogResourceInfo(BranchRecentLogResourceInfo branchRecentLogResourceInfo)
        {
            this.recentResource.add(branchRecentLogResourceInfo);
        }
}
