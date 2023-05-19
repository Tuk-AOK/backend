package crepe.backend.domain.branch.dto;

import crepe.backend.domain.branch.dto.BranchFeedbackInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchFeedbackInfoList {

    List<BranchFeedbackInfo> branchFeedbackInfos = new ArrayList<>();

    public void addAllBranchFeedbackInfo(List<BranchFeedbackInfo> branchFeedbackInfos) {
        this.branchFeedbackInfos.addAll(branchFeedbackInfos);
    }

    public void addBranchFeedbackInfo(BranchFeedbackInfo branchFeedbackInfo) {
        this.branchFeedbackInfos.add(branchFeedbackInfo);
    }

}
