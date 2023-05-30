package crepe.backend.domain.project.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserProjectCreateRequest {

    private Long projectId;
    private Long userId;
}
