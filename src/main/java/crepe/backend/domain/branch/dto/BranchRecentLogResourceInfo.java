package crepe.backend.domain.branch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class BranchRecentLogResourceInfo {

    private final String fileName;

    private final String fileLink;
}
