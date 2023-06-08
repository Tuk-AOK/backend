
package crepe.backend.domain.project.controller;

import crepe.backend.domain.log.domain.entity.Resource;
import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.dto.*;
import crepe.backend.domain.project.service.ProjectService;
import crepe.backend.domain.user.dto.UserInfoList;
import crepe.backend.global.response.ErrorCode;
import crepe.backend.global.response.ResultCode;
import crepe.backend.global.response.ResultResponse;
import crepe.backend.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.*;

import static crepe.backend.global.response.ErrorCode.PAGE_ERROR;

@RequestMapping("/api/v1/projects")
@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<ResultResponse> createProject(
            @Valid @RequestBody ProjectCreateRequest request) {
        ProjectInfo projectInfo = projectService.createProject(request);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_PROJECT_SUCCESS, projectInfo));
    }

    @GetMapping
    public ResponseEntity<ResultResponse> findAllProject(
            @Valid @RequestParam(required = false)Optional<UUID> userUuid, @RequestParam(required = false) int page) {
        ProjectInfoList projectInfoList = projectService.findUserProjectById(userUuid.orElse(null), page);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.READ_ALL_USER_PROJECT_SUCCESS, projectInfoList));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResultResponse> findProjectByUuid(@PathVariable UUID uuid) {
        ProjectInfo projectInfo = projectService.findProjectInfoByUuid(uuid);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.READ_ONE_PROJECT_SUCCESS, projectInfo));
    }

    @PatchMapping("/{uuid}/preview")
    public ResponseEntity<ResultResponse> updateProjectPreviewByUuid(
            @PathVariable UUID uuid,
            @Valid @RequestPart(required = true)MultipartFile projectPreview) {

        String fileName = s3Service.uploadFile(projectPreview);
        projectService.updateProjectPreviewByUuid(uuid, fileName);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_PROJECT_PREVIEW_SUCCESS,""));
    }

    @GetMapping("/{uuid}/branches")
    public ResponseEntity<ResultResponse> findAllBranchByProjectUuid(@PathVariable UUID uuid, @RequestParam(required = false) int page) {
        ProjectBranchInfoList branchInfoList = projectService.findAllBranchInfoByUuid(uuid, page);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.READ_PROJECT_BRANCH_SUCCESS, branchInfoList));
    }

    @GetMapping("/{uuid}/users")
    public ResponseEntity<ResultResponse> findAllUserByProjectUuid(@PathVariable UUID uuid, @RequestParam(required = false) int page) {
        UserInfoList userInfoList = projectService.findAllUserInfoByUuid(uuid, page);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.READ_PROJECT_USER_SUCCESS, userInfoList));
    }

    @PostMapping("/users")
    public ResponseEntity<ResultResponse> createUserProject(
            //@PathVariable UUID uuid,
            @Valid @RequestBody UserProjectCreateRequest userProjectCreateRequest) {
        projectService.createUserProject(userProjectCreateRequest.getUserId(), userProjectCreateRequest.getProjectId());
        return  ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_USERPROJECT_SUCCESS,""));
    }

    @GetMapping("/{projectUuid}/main/resources/{resourceName}")
    public ResponseEntity<ResultResponse> createUserProject(
            @PathVariable UUID projectUuid,
            @PathVariable String resourceName) {

        Resource resource = projectService.findMainResourceByName(projectUuid, resourceName);
        return  ResponseEntity.ok(ResultResponse.of(ResultCode.READ_PROJECT_MAIN_RESOURCE_SUCCESS, resource.getLink()));
    }

}
