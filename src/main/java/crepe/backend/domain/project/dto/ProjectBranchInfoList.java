package crepe.backend.domain.project.dto;

import crepe.backend.domain.branch.dto.BranchInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProjectBranchInfoList {
    private List<ProjectBranchInfo> projectBranchInfos = new ArrayList<>();

    public void addAllProjectBranchInfo(List<ProjectBranchInfo> projectBranchInfos) {
        this.projectBranchInfos.addAll(projectBranchInfos);
    }

    public void addProjectBranchInfo(ProjectBranchInfo projectBranchInfo) {
        this.projectBranchInfos.add(projectBranchInfo);
    }
}
