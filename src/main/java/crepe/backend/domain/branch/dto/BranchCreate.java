package crepe.backend.domain.branch.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BranchCreate {

    private String name;

    private Long projectId;
}
