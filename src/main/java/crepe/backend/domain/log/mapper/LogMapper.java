package crepe.backend.domain.log.mapper;

import crepe.backend.domain.log.domain.entity.Resource;
import crepe.backend.domain.log.dto.ResourceInfo;
import org.springframework.stereotype.Service;

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
}
