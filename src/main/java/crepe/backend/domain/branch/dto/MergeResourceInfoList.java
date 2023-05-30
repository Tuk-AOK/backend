package crepe.backend.domain.branch.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeResourceInfoList {
    List<MergeResourceInfo> mergeResourceInfos = new ArrayList<>();

    public void addAllMergeResourceInfo(List<MergeResourceInfo> mergeResourceInfos)
    {
        this.mergeResourceInfos.addAll(mergeResourceInfos);
    }

    public void addMergeResourceInfo(MergeResourceInfo mergeResourceInfo)
    {
        this.mergeResourceInfos.add(mergeResourceInfo);
    }
}
