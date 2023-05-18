package crepe.backend.domain.log.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ResourceInfo {

    private String fileName;
    private String fileLink;
    private UUID fileUuid;

    @Builder
    public ResourceInfo(String fileName, String fileLink, UUID fileUuid) {
        this.fileName = fileName;
        this.fileLink = fileLink;
        this.fileUuid = fileUuid;
    }
}
