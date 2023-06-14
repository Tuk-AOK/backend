package crepe.backend.domain.branch.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchRecentLogResourceInfo {

    private String fileName;
    private String fileLink;
}
