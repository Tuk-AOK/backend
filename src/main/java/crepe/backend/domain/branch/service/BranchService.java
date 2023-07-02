package crepe.backend.domain.branch.service;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.branch.domain.repository.BranchRepository;
import crepe.backend.domain.branch.dto.*;
import crepe.backend.domain.branch.mapper.BranchMapper;
import crepe.backend.domain.feedback.domain.entity.Feedback;
import crepe.backend.domain.feedback.domain.repository.FeedbackRepository;
import crepe.backend.domain.log.domain.entity.Layer;
import crepe.backend.domain.log.domain.entity.Log;
import crepe.backend.domain.log.domain.entity.Resource;
import crepe.backend.domain.log.domain.repository.LayerRepository;
import crepe.backend.domain.log.domain.repository.LogRepository;

import crepe.backend.domain.log.domain.repository.ResourceRepository;
import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.repository.ProjectRepository;
import crepe.backend.domain.project.exception.NotFoundProjectEntityException;
import crepe.backend.domain.project.exception.NotFoundResourceEntity;
import crepe.backend.domain.branch.exception.NotFoundBranchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final ProjectRepository projectRepository;
    private final LogRepository logRepository;
    private final LayerRepository layerRepository;
    private final ResourceRepository resourceRepository;
    private final FeedbackRepository feedbackRepository;
    private final BranchMapper branchMapper;


    public BranchCreateInfo branchCreate(BranchCreate createRequest) { // 브랜치를 생성하는 모듈
        Project foundProject = getProjectById(createRequest.getProjectId());

        Branch newBranch = branchMapper.mapBranchCreateToBranch(createRequest, foundProject);
        Branch savedBranch = branchRepository.save(newBranch);

        Branch mainBranch = branchRepository.findAllByProjectIdAndIsActiveTrueOrderByCreatedAt(createRequest.getProjectId()).get(0);

        if(mainBranch.getLogs().size() == 0)
        {
            return branchMapper.mapBranchEntityToBranchCreateInfo(savedBranch);
        }
        else
        {
            Log recentLog = logRepository.findAllByBranchAndIsActiveTrueOrderByCreatedAtDesc(mainBranch).get(0);
            List<Layer> findLayers = layerRepository.findAllByLogAndIsActiveTrueOrderBySequence(recentLog);

            Log createLog = branchCreateLog(recentLog, newBranch);

            for(Layer layer : findLayers)
            {
                createLayerEntity(createLog, layer);
            }

            return branchMapper.mapBranchEntityToBranchCreateInfo(savedBranch);
        }
    }

    private Project getProjectById(Long projectId) {
        return projectRepository.findProjectByIdAndIsActiveTrue(projectId).orElseThrow(NotFoundProjectEntityException::new);
    }

    public BranchInfo findBranchInfoByUuId(UUID uuid) { // 특정 브랜치의 정보를 찾을 때 사용하는 모듈
        Branch findBranch = findBranchByUuid(uuid);
        return branchMapper.mapBranchEntityToBranchInfo(findBranch);
    }

    public BranchLogInfoList findLogInfoByUuid(UUID uuid, int page) { // 해당 브랜치의 모든 로그 정보를 찾는 모듈
        Pageable pageable = PageRequest.of(page, 10);

        Branch findBranch = findBranchByUuid(uuid);
        Page<Log> logs = getLogList(findBranch, pageable);
        return branchMapper.getLogInfoList(logs);
    }

    public List<BranchFeedbackInfo> findFeedbackInfoByUuid(UUID branchUuid, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Branch findBranch = findBranchByUuid(branchUuid);
        return branchMapper.mapBranchEntityToBranchFeedbackInfos(getFeedbackListByBranch(findBranch, pageable));
    }

    private Page<Feedback> getFeedbackListByBranch(Branch branch, Pageable pageable) {
        return feedbackRepository.findAllByBranchAndIsActiveTrueOrderByCreatedAtDesc(branch, pageable);
    }

    private Branch findBranchByUuid(UUID uuid) { // 쿼리를 이용해서 UUID를 가지고 브랜치를 찾는 모듈
        return branchRepository.findBranchByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundBranchEntityException::new);
    }

    public Branch findBranchById(Long id) {
        return branchRepository.findBranchByIdAndIsActiveTrue(id).orElseThrow(NotFoundBranchEntityException::new);
    }

    private Page<Log> getLogList(Branch branch, Pageable pageable) { // 쿼리를 이용해서 BranchId로 해당 브랜치에 있는 로그들을 가져오는 모듈
        Page<Log> logs = logRepository.findAllByBranchAndIsActiveTrueOrderByIdDesc(branch, pageable);

        return logs;
    }

    public void updateBranchInfo(UUID uuid, Map<String, String> branch) {
        Branch oBranch = findBranchByUuid(uuid);

        oBranch.updateBranch(branch.get("name"));
        branchRepository.save(oBranch);
    }

    public void deleteBranch(UUID uuid) {
        Branch branch = findBranchByUuid(uuid);
        branchRepository.deleteById(branch.getId());
    }
    private Log getRecentLogByBranch(Branch branch) {
        return logRepository.findAllByBranchAndIsActiveTrueOrderByCreatedAtDesc(branch).get(0);
    }

    private Branch getBranchByUuid(UUID uuid) {
        return branchRepository.findBranchByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundBranchEntityException::new);
    }

    public BranchRecentLogInfo findRecentLogInfoByUuid(UUID uuid) {
        Branch branch = findBranchByUuid(uuid);
        Log log = getRecentLogByBranch(branch);
        return branchMapper.mapLogToRecentlogInfo(log);
    }

    public List<MergeResourceInfo> getMergeResources(UUID uuid) {

        Branch branch = getBranchByUuid(uuid);
        //메인브랜치는 삭제 될 일 없으니 0번 받아옴
        Branch mainBranch = branch.getProject().getBranches().get(0);

        if (mainBranch.getLogs().isEmpty()) {
            List<MergeResourceInfo> mergeResourceInfos = new ArrayList<>();
            Log log = getRecentLogByBranch(branch);
            List<Layer> layers = layerRepository.findAllByLogAndIsActiveTrueOrderBySequence(log);
            List<Resource> resources = getResourcesByLayer(layers);

            for (Resource resource: resources){
                mergeResourceInfos.add(branchMapper.mapMergeResourceInfo(resource.getName(),
                        resource.getLink(),
                        false,
                        true));
            }
            return mergeResourceInfos;
        }

        // isActive 상태의 가장 최신 로그
        Log log = getRecentLogByBranch(branch);
        Log mainLog = getRecentLogByBranch(mainBranch);

        // 정렬된 모든 레이어 받아옴
        List<Layer> layers = layerRepository.findAllByLogAndIsActiveTrueOrderBySequence(log);
        List<Layer> mainLayers = layerRepository.findAllByLogAndIsActiveTrueOrderBySequence(mainLog);

        List<Resource> resources = getResourcesByLayer(layers);
        List<Resource> mainResources = getResourcesByLayer(mainLayers);

        return getMergeResourceInfoList(resources, mainResources);
    }

    //리소스 리스트 반환
    private List<Resource> getResourcesByLayer(List<Layer> layers) {
        List<Resource> resources = new ArrayList<>();
        for(Layer layer: layers) {
            if (layer.getResource().isActive() == true) {
                resources.add(layer.getResource());
            }
            //resources.add(getResourceById(layer.getResource().getId()));
        }
        return resources;
    }

    //리소스 아이디로 리소스 찾기
    private Resource getResourceById(Long id) {
        return resourceRepository.findResourceByIdAndIsActiveTrue(id).orElseThrow(NotFoundResourceEntity::new);
    }

    private List<MergeResourceInfo> getMergeResourceInfoList(List<Resource> resources, List<Resource> mainResources) {
        List<MergeResourceInfo> mergeResourceInfos = new ArrayList<>();

        int index = 0;
        boolean isIn = false;

        List<List<String>> branchFileInfos =branchMapper.getFileInfoList(resources);
        List<List<String>> mainFileInfos = branchMapper.getFileInfoList(mainResources);


        while ((!branchFileInfos.isEmpty()) &&(!mainFileInfos.isEmpty())) {

            // 브랜치의 0번 요소 가져오기
            String currData = branchFileInfos.get(0).get(0);
            // 메인에 해당 파일이 있는지 검사
            for (List<String> mainFileInfo: mainFileInfos) {
                if (mainFileInfo.contains(currData)) {
                    isIn = true;
                    index = mainFileInfos.indexOf(mainFileInfo);
                }
            }
            if (isIn) {  // 있으면
                mergeResourceInfos.add(branchMapper.mapMergeResourceInfo(
                        branchFileInfos.get(0).get(0), //fileName
                        branchFileInfos.get(0).get(1), //fileLink
                        true,
                        true));
                branchFileInfos.remove(0);
                mainFileInfos.remove(index);
                isIn = false;
            } else { // 없으면
                mergeResourceInfos.add(branchMapper.mapMergeResourceInfo(
                        branchFileInfos.get(0).get(0),
                        branchFileInfos.get(0).get(1),
                        false,
                        true));
                branchFileInfos.remove(0);
            }
            // 메인의 0번 요소 가져오기
            if (mainFileInfos.isEmpty()) {
                break;
            }
            currData = mainFileInfos.get(0).get(0);
            // 브랜치에 해당 파일이 있는지 검사
            for (List<String> branchFileInfo: branchFileInfos) {
                if (branchFileInfo.contains(currData)) {
                    isIn = true;
                    index = branchFileInfos.indexOf(branchFileInfo);
                }
            }
            if (isIn) {  // 있으면
                isIn = false;
            } else { // 없으면
                mergeResourceInfos.add(branchMapper.mapMergeResourceInfo(
                        mainFileInfos.get(0).get(0),
                        mainFileInfos.get(0).get(1),
                        false,
                        false));
                mainFileInfos.remove(0);
            }
        }
        // 브랜치에 리소스 요소가 남은 경우
        if (!branchFileInfos.isEmpty()) {
            for (List<String> branchFileInfo: branchFileInfos) {
                mergeResourceInfos.add(branchMapper.mapMergeResourceInfo(
                        branchFileInfo.get(0),
                        branchFileInfo.get(1),
                        false,
                        true));
            }
        }

        if (!mainFileInfos.isEmpty()) {
            for (List<String> mainFileInfo: mainFileInfos) {
                mergeResourceInfos.add(branchMapper.mapMergeResourceInfo(
                        mainFileInfo.get(0),
                        mainFileInfo.get(1),
                        false,
                        false));
            }
        }

        return mergeResourceInfos;
    }
    public BranchRecentLogResourceInfoList findBranchRecentLogResource(UUID branchUuid)
    {
        Branch branch = getBranchByUuid(branchUuid);
        Log log = getRecentLogByBranch(branch);
        List<Layer> layers = layerRepository.findAllByLogAndIsActiveTrueOrderBySequence(log);
        List<Resource> resources = getResourcesByLayer(layers);
        return branchMapper.getBranchRecentLogResourceInfoList(resources);
    }

    private void createLayerEntity(Log log, Layer oldlayer) {
        Layer layer = Layer.builder()
                .log(log)
                .resource(oldlayer.getResource())
                .sequence(oldlayer.getSequence())
                .build();

        layerRepository.save(layer);
    }

    private Log branchCreateLog(Log oldLog, Branch mainBranch)
    {
        Log log = Log.builder()
                .user(oldLog.getUser())
                .branch(mainBranch)
                .message("MainBranch's Log")
                .preview(oldLog.getPreview())
                .build();

        logRepository.save(log);
        return log;
    }
}