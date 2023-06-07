package crepe.backend.domain.log.mapper;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.log.domain.entity.Log;
import crepe.backend.domain.log.domain.entity.Resource;
import crepe.backend.domain.log.dto.LogCreateRequest;
import crepe.backend.domain.log.dto.LogInfo;
import crepe.backend.domain.log.dto.ResourceInfo;
import crepe.backend.domain.log.dto.ResourceInfoList;
import crepe.backend.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogMapper {

    public List<ResourceInfo> getResourceInfoList(List<Resource> resources) {
        List<ResourceInfo> resourceInfos = new ArrayList<>();
        for(Resource resource: resources) {
            resourceInfos.add(ResourceInfo.builder()
                    .fileName(resource.getName())
                    .fileLink(resource.getLink())
                    .fileUuid(resource.getUuid())
                    .build());
        }
        return resourceInfos;
    }

    public Log createLogEntity(Branch branch, User user, String message, String preview) {
        return Log.builder()
                .branch(branch)
                .user(user)
                .message(message)
                .preview(preview)
                .build();
    }

    public LogInfo createLogInfoEntity(Log log, List<ResourceInfo> resourceInfoList) {
        return LogInfo.builder()
                .userUuid(log.getUser().getUuid())
                .logMessage(log.getMessage())
                .logCreatedAt(log.getCreatedAt())
                .resourceInfos(resourceInfoList)
                .logPreview(log.getPreview())
                .build();
    }
}
