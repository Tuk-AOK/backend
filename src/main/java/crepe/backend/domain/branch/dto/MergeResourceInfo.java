package crepe.backend.domain.branch.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MergeResourceInfo {

    private String fileName;
    private String fileLink;
    private boolean isDuplicated;
    private boolean isNew;

}
