package crepe.backend.domain.project.mapper;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.entity.UserProject;
import crepe.backend.domain.project.dto.*;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.dto.UserInfo;
import crepe.backend.domain.user.dto.UserInfoList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectMapper {

    public UserProject mapUserProject(User user, Project project, boolean isAdmin)
    {
        return UserProject.builder()
                .user(user)
                .project(project)
                .isAdmin(isAdmin)
                .build();
    }

    public UserInfoList getUserInfoList(List<User> users) {
        List<UserInfo> userInfos = new ArrayList<>();
        for(int i = 0; i < users.size(); i++) {
            userInfos.add(UserInfo.builder()
                    .userUuid(users.get(i).getUuid())
                    .email(users.get(i).getEmail())
                    .nickname(users.get(i).getNickname())
                    .photo(users.get(i).getPhoto())
                    .build());
        }
        return new UserInfoList(userInfos);
    }

    public ProjectBranchInfoList getProjectBranchInfoList(List<Branch> branches) {
        List<ProjectBranchInfo> projectBranchInfos = new ArrayList<>();
        for(Branch branch: branches) {
            projectBranchInfos.add(ProjectBranchInfo.builder()
                    .branchId(branch.getId())
                    .branchUuid(branch.getUuid())
                    .branchName(branch.getName())
                    .build());
        }
        return new ProjectBranchInfoList(projectBranchInfos);
    }

    public Project convertProjectFromRequest(ProjectCreateRequest projectCreateRequest) {
        return Project.builder()
                .name(projectCreateRequest.getProjectName())
                .intro(projectCreateRequest.getProjectIntro())
                .build();
    }

    public ProjectInfo mapProjectEntityToProjectInfoResponse(Project project) {
        return ProjectInfo.builder()
                .projectName(project.getName())
                .projectUuid(project.getUuid())
                .projectIntro(project.getIntro())
                .projectPreview(project.getPreview())
                .projectCreatedAt(project.getCreatedAt())
                .projectUpdatedAt(project.getUpdatedAt())
                .build();
    }

    public List<User> getUserList(List<UserProject> userProjects) {
        List<User> users = new ArrayList<>();

        for(UserProject userProject: userProjects) {
            users.add(userProject.getUser());
        }
        return users;
    }

    public ProjectInfoList getProjectInfoList(List<Project> projects)
    {
        List<ProjectInfo> projectInfos = new ArrayList<>();

        for(Project project: projects)
        {
            projectInfos.add(mapProjectEntityToProjectInfoResponse(project));
        }

        return new ProjectInfoList(projectInfos);
    }

    public List<Project> getProjectList(List<UserProject> userProjects) // 유저가 속해있는 프로젝트 ID를 얻기 위한 함수
    {
        List<Project> projects = new ArrayList<>();

        for (UserProject userProject: userProjects) {
            projects.add(userProject.getProject());
        }
        return projects;
    }
}
