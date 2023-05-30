package crepe.backend.domain.branch.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchLogInfoList {

    List<BranchLogInfo> logs = new ArrayList<>();

    public void addAllBranchLogInfo(List<BranchLogInfo> branchLogInfoList)
    {
        this.logs.addAll(branchLogInfoList);
    }

    public void addBranchLogInfo(BranchLogInfo branchLogInfo)
    {
        this.logs.add(branchLogInfo);
    }
}
