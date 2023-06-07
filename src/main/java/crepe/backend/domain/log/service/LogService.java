package crepe.backend.domain.log.service;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.branch.domain.repository.BranchRepository;
import crepe.backend.domain.branch.exception.NotFoundBranchEntityException;
import crepe.backend.domain.log.domain.entity.Layer;
import crepe.backend.domain.log.domain.entity.Log;
import crepe.backend.domain.log.domain.entity.Resource;
import crepe.backend.domain.log.domain.repository.LayerRepository;
import crepe.backend.domain.log.domain.repository.LogRepository;
import crepe.backend.domain.log.domain.repository.ResourceRepository;
import crepe.backend.domain.log.dto.*;
import crepe.backend.domain.log.exception.NotFoundLogEntityException;
import crepe.backend.domain.log.mapper.LogMapper;
import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.repository.ProjectRepository;
import crepe.backend.domain.project.exception.NotFoundResourceEntity;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.domain.repository.UserRepository;
import crepe.backend.domain.user.exception.NotFoundUserEntityException;
import crepe.backend.global.exception.BusinessException;
import crepe.backend.global.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final LayerRepository layerRepository;
    private final ResourceRepository resourceRepository;
    private final ProjectRepository projectRepository;

    private final LogMapper logMapper;

    public LogUuidInfo createLogUuidInfo(Log log) {
        return LogUuidInfo.builder()
                .logUuid(log.getUuid())
                .logCreatedAt(log.getCreatedAt())
                .build();
    }
    public Log createLog(LogCreateRequest request, String previewLink) {

        try {
            Log log = logMapper.createLogEntity(
                    getBranchById(request.getBranchId()),
                    getUserById(request.getUserId()),
                    request.getMessage(),
                    previewLink);
            return logRepository.save(log);
        } catch(Exception e) {
            throw new BusinessException(ErrorCode.LOG_CREATE_ERROR);
        }
    }
    public void updatePreview(Log log, String preview){
        Project project = log.getBranch().getProject();
        project.updatePreview(preview);
        projectRepository.save(project);
    }
    public LogInfo findLogInfoByUuid(UUID uuid){
        //레이어 전부 읽어서
        Log log = getLogByUuid(uuid);
        List<Layer> layers = layerRepository.findAllByLogAndIsActiveTrueOrderBySequence(log);


        List<Resource> resources = new ArrayList<>();
        for(Layer layer: layers) { //리소스 리스트 만들기
            resources.add(getResourceById(layer.getResource().getId()));
        }

        return LogInfo.builder()
                .userUuid(log.getUser().getUuid())
                .logMessage(log.getMessage())
                .logCreatedAt(log.getCreatedAt())
                .resourceInfos(logMapper.getResourceInfoList(resources))
                .build();
    }

    public void deleteLog(UUID uuid) {
        logRepository.deleteById(getLogByUuid(uuid).getId());
    }


    private User getUserById(Long userId) {
        return userRepository.findUserByIdAndIsActiveTrue(userId).orElseThrow(NotFoundUserEntityException::new);
    }

    private Branch getBranchById(Long branchId) {
        return branchRepository.findBranchByIdAndIsActiveTrue(branchId).orElseThrow(NotFoundBranchEntityException::new);
    }

    private Log getLogByUuid(UUID uuid) {
        return logRepository.findLogByUuidAndIsActiveTrue(uuid).orElseThrow(NotFoundLogEntityException::new);
    }

    private Resource getResourceById(Long id) {
        return resourceRepository.findResourceByIdAndIsActiveTrue(id).orElseThrow(NotFoundResourceEntity::new);
    }

    public void createLayer(Log log, List<Resource> resources) {
        int index = 0;
        for(Resource resource:resources) {
            try {
                createLayerEntity(log, resource, index++);
            } catch(Exception e) {
                throw new BusinessException(ErrorCode.LAYER_CREATE_ERROR);
            }
        }
    }

    private void createLayerEntity(Log log, Resource resource, int sequence) {
        Layer layer = Layer.builder()
                .log(log)
                .resource(resource)
                .sequence(sequence)
                .build();

        layerRepository.save(layer);
    }
}
