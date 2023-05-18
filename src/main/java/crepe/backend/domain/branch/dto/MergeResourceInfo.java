package crepe.backend.domain.branch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MergeResourceInfo {

    private String fileName;
    private String fileLink;
    private boolean isDuplicated;

    private boolean isNew;

    @Builder
    public MergeResourceInfo(String fileName, String fileLink, boolean isDuplicated, boolean isNew) {
        this.fileName = fileName;
        this.fileLink = fileLink;
        this.isDuplicated = isDuplicated;
        this.isNew = isNew; //새로 추가된-변경된 파일인가
    }
}
