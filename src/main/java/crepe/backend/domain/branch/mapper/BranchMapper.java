package crepe.backend.domain.branch.mapper;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.branch.dto.*;
import crepe.backend.domain.feedback.domain.entity.Feedback;
import crepe.backend.domain.log.domain.entity.Log;
import crepe.backend.domain.log.domain.entity.Resource;
import crepe.backend.domain.project.domain.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BranchMapper {
    public Branch mapBranchCreateToBranch(BranchCreate branchCreate, Project project) { // BranchCreate 타입을 Branch 타입으로 변환하는 모듈
        return Branch.builder()
                .project(project)
                .name(branchCreate.getName())
                .build();
    }

    public BranchCreateInfo mapBranchEntityToBranchCreateInfo(Branch savedata) { // Branch 타입을 BranchCreateInfo 타입으로 변환하는 모듈
        return BranchCreateInfo.builder()
                .branchUuid(savedata.getUuid())
                .build();
    }

    public BranchInfo mapBranchEntityToBranchInfo(Branch branch) // Branch 타입을 BranchInfo 타입으로 변환하는 모듈
    {
        return BranchInfo.builder()
                .branchName(branch.getName())
                .branchId(branch.getId())
                .build();
    }

    public List<BranchFeedbackInfo> mapBranchEntityToBranchFeedbackInfos(Page<Feedback> feedbacks) // Branch 타입을 BranchInfo 타입으로 변환하는 모듈
    {
        List<BranchFeedbackInfo> branchFeedbackInfos = new ArrayList<>();
        for(Feedback feedback: feedbacks) {
            branchFeedbackInfos.add(BranchFeedbackInfo.builder()
                    .feedbackMessage(feedback.getMessage())
                    .feedbackUserUuid(feedback.getUser().getUuid())
                    .feedbackUuid(feedback.getUuid())
                    .feedbackStatus(feedback.getStatus())
                    .build());
        }
        return branchFeedbackInfos;
    }

    public BranchLogInfoList getLogInfoList(Page<Log> logs) // LogInfo들을 LogInfoList로 변환하는 모듈
    {
        List<BranchLogInfo> logInfos = new ArrayList<>();

        for(Log log : logs)
        {
            logInfos.add(BranchLogInfo.builder()
                    .logUuid(log.getUuid())
                    .logCreatedAt(log.getCreatedAt())
                    .build());
        }

        return new BranchLogInfoList(logInfos);
    }

    public BranchRecentLogInfo mapLogToRecentlogInfo(Log log)
    {
        return BranchRecentLogInfo.builder()
                .logUuid(log.getUuid())
                .build();
    }

    public MergeResourceInfo mapMergeResourceInfo(String name,
                                                   String link,
                                                   boolean isDuplicated,
                                                   boolean isNew) {
        return MergeResourceInfo.builder()
                .fileName(name)
                .fileLink(link)
                .isDuplicated(isDuplicated)
                .isNew(isNew)
                .build();
    }

    public List<List<String>> getFileInfoList(List<Resource> resources) {
        List<List<String>> fileInfos = new ArrayList<>();
        for (Resource resource: resources) {
            fileInfos.add(Arrays.asList(resource.getName(), resource.getLink()));
        }
        return fileInfos;
    }

    public BranchRecentLogResourceInfoList getBranchRecentLogResourceInfoList(List<Resource> recentLogResources)
    {
        List<BranchRecentLogResourceInfo> branchRecentLogResourceInfos = new ArrayList<>();

        for(Resource resource: recentLogResources) {
            branchRecentLogResourceInfos.add(BranchRecentLogResourceInfo.builder()
                    .fileName(resource.getName())
                    .fileLink(resource.getLink())
                    .build());
        }

        return new BranchRecentLogResourceInfoList(branchRecentLogResourceInfos);
    }
}
