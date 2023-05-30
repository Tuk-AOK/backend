package crepe.backend.domain.log.dto;

import crepe.backend.domain.project.dto.ProjectInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResourceInfoList {

    List<ResourceInfo> resourceInfos = new ArrayList<>();
    public void addAllResourceInfo(List<ResourceInfo> resourceInfos)
    {
        this.resourceInfos.addAll(resourceInfos);
    }
    public void addResourceInfo(ResourceInfo resourceInfo) {this.resourceInfos.add(resourceInfo); }

}
