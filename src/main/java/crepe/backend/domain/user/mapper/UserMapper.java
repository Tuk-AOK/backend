package crepe.backend.domain.user.mapper;

import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.entity.UserProject;
import crepe.backend.domain.project.dto.ProjectInfo;
import crepe.backend.domain.project.dto.ProjectInfoList;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.dto.UserCreate;
import crepe.backend.domain.user.dto.UserCreateInfo;
import crepe.backend.domain.user.dto.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public User mapUserInfoToUser(UserCreate usercreate) {
        return User.builder()
                .email(usercreate.getEmail())
                .password(usercreate.getPassword())
                .photo(usercreate.getPhoto())
                .nickname(usercreate.getNickname())
                .build();
    }

    public UserCreateInfo mapUserEntityToUserCreateInfo(User savedUser) {
        return UserCreateInfo.builder()
                .userUuid(savedUser.getUuid())
                .build();
    }

    public UserInfo mapUserEntityToUserInfo(User savedUser) {
        return UserInfo.builder()
                .userUuid(savedUser.getUuid())
                .email(savedUser.getEmail())
                .nickname(savedUser.getNickname())
                .photo(savedUser.getPhoto())
                .build();
    }

    public ProjectInfoList getProjectInfoList(List<Project> projects)
    {
        List<ProjectInfo> projectInfos = new ArrayList<>();

        for(int i = 0; i < projects.size(); i ++)
        {
            projectInfos.add(ProjectInfo.builder()
                    .projectName(projects.get(i).getName())
                    .projectUuid(projects.get(i).getUuid())
                    .projectIntro(projects.get(i).getIntro())
                    .build());
        }

        return new ProjectInfoList(projectInfos);
    }

    public List<Project> getProjectList(Page<UserProject> userProjects) // 유저가 속해있는 프로젝트 ID를 얻기 위한 함수
    {
        List<Project> projects = new ArrayList<>();

        for (UserProject userProject: userProjects) {
            projects.add(userProject.getProject());
        }
        return projects;
    }
}