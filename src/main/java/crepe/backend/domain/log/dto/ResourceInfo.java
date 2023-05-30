package crepe.backend.domain.log.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResourceInfo {

    private String fileName;
    private String fileLink;
    private UUID fileUuid;

}
