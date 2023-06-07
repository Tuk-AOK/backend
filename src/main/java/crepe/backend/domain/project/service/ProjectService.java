
package crepe.backend.domain.project.service;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.branch.domain.repository.BranchRepository;
import crepe.backend.domain.branch.dto.BranchInfo;
import crepe.backend.domain.branch.exception.NotFoundBranchEntityException;
import crepe.backend.domain.log.domain.entity.Layer;
import crepe.backend.domain.log.domain.entity.Log;
import crepe.backend.domain.log.domain.entity.Resource;
import crepe.backend.domain.log.domain.repository.LogRepository;
import crepe.backend.domain.log.domain.repository.ResourceRepository;
import crepe.backend.domain.project.dto.*;
import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.repository.ProjectRepository;
import crepe.backend.domain.project.exception.EventDuplicationUserException;
import crepe.backend.domain.project.exception.NotFoundProjectEntityException;
import crepe.backend.domain.project.exception.NotFoundResourceEntity;
import crepe.backend.domain.project.mapper.ProjectMapper;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.domain.repository.UserRepository;
import crepe.backend.domain.user.dto.UserInfo;
import crepe.backend.domain.user.dto.UserInfoList;
import crepe.backend.domain.user.exception.NotFoundUserEntityException;
import crepe.backend.domain.project.domain.entity.UserProject;
import crepe.backend.domain.project.domain.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final UserProjectRepository userProjectRepository;

    private final ResourceRepository resourceRepository;

    private final LogRepository logRepository;
    private final ProjectMapper projectMapper;

    public Resource findMainResourceByName(UUID projectUuid, String resourceName) {
        Branch branch = branchRepository.findBranchByProjectAndIsActiveTrueAndName(findProjectByUuid(projectUuid), "main").orElseThrow(NotFoundBranchEntityException::new);
        Log log = getRecentLogByBranch(branch);
        for (Layer layer:log.getLayers()){
            if (layer.getResource().getName().equals(resourceName)) {
                return layer.getResource();
            }
        }
        throw new NotFoundResourceEntity();
    }

    private Log getRecentLogByBranch(Branch branch) {
        return logRepository.findAllByBranchAndIsActiveTrueOrderByCreatedAtDesc(branch).get(0);
    }
    public ProjectInfo createProject(ProjectCreateRequest projectCreateRequest) {
        Project newProject = projectMapper.convertProjectFromRequest(projectCreateRequest);
        User foundUser = getUserById(projectCreateRequest.getProjectUserId());
        Project savedProject = projectRepository.save(newProject);


        UserProject userProject = projectMapper.mapUserProject(foundUser, savedProject, true);
        userProjectRepository.save(userProject);
        saveBranch(savedProject, "main");

        return projectMapper.mapProjectEntityToProjectInfoResponse(savedProject);
    }
    public void updateProjectPreviewByUuid(UUID uuid, String fileName){
        Project foundProject = getProjectByUuid(uuid);
        foundProject.updatePreview(fileName);
        projectRepository.save(foundProject);
    }


    public void createUserProject(Long userId, Long projectId) {

        Project project = getProjectById(projectId);
        List<UserProject> userProjects = getUserProject(project);

        for(UserProject userProject: userProjects)
        {
            if(userProject.getUser().getId() == userId)
            {
                throw new EventDuplicationUserException();
            }
        }

        UserProject userProject = projectMapper.mapUserProject(getUserById(userId), getProjectById(projectId), false);
        userProjectRepository.save(userProject);
    }


    public ProjectInfoList findUserProjectById(UUID uuid) {
        User foundUser = findUserByUuid(uuid);
        List<UserProject> userProjects = foundUser.getUserProjects();
        List<Project> projects = projectMapper.getProjectList(userProjects);
        return projectMapper.getProjectInfoList(projects);
    }

    public User findUserByUuid(UUID uuid)
    {
        return userRepository.findUserByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundUserEntityException::new);
    }

    private void saveBranch(Project project,String name) {
        branchRepository.save(Branch.builder()
                .project(project)
                .name(name)
                .build());
    }


    public ProjectInfo findProjectInfoByUuid(UUID uuid) {
        Project foundProject = findProjectByUuid(uuid);
        return projectMapper.mapProjectEntityToProjectInfoResponse(foundProject);
    }

    public ProjectBranchInfoList findAllBranchInfoByUuid(UUID uuid) {
        List<Branch> branches = branchRepository.findAllByProjectAndIsActiveTrue(findProjectByUuid(uuid));
        return projectMapper.getProjectBranchInfoList(branches);
    }

    public UserInfoList findAllUserInfoByUuid(UUID uuid) {
        List<UserProject> userProjects = userProjectRepository.findAllByProjectAndIsActiveTrue(findProjectByUuid(uuid));
        return projectMapper.getUserInfoList(projectMapper.getUserList(userProjects));
    }

    private User getUserById(Long userId) {
        return userRepository.findUserByIdAndIsActiveTrue(userId).orElseThrow(NotFoundUserEntityException::new);
    }

    private Project getProjectById(Long projectId) {
        return projectRepository.findProjectByIdAndIsActiveTrue(projectId).orElseThrow(NotFoundProjectEntityException::new);
    }

    private Project getProjectByUuid(UUID uuid) {
        return projectRepository.findProjectByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundProjectEntityException::new);
    }


    private Project findProjectByUuid(UUID uuid) {
        return projectRepository.findProjectByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundProjectEntityException::new);
    }

    private List<UserProject> getUserProject(Project project)
    {
        return userProjectRepository.findAllByProjectAndIsActiveTrue(project);
    }
}
