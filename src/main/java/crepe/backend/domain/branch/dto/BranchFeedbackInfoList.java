package crepe.backend.domain.branch.dto;

import crepe.backend.domain.branch.dto.BranchFeedbackInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchFeedbackInfoList {

    List<BranchFeedbackInfo> branchFeedbackInfos = new ArrayList<>();

    public void addAllBranchFeedbackInfo(List<BranchFeedbackInfo> branchFeedbackInfos) {
        this.branchFeedbackInfos.addAll(branchFeedbackInfos);
    }

    public void addBranchFeedbackInfo(BranchFeedbackInfo branchFeedbackInfo) {
        this.branchFeedbackInfos.add(branchFeedbackInfo);
    }

}
