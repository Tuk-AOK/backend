package crepe.backend.domain.project.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProjectInfoList {
    List<ProjectInfo> projects = new ArrayList<>();

    public void addAllProjectInfo(List<ProjectInfo> projects)
    {
        this.projects.addAll(projects);
    }

    public void addProjectInfo(ProjectInfo project)
    {
        this.projects.add(project);
    }

}
