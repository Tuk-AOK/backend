package crepe.backend.domain.project.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProjectCreateRequest {

    @NotBlank(message = "프로젝트 이름을 입력해주세요.")
    private String projectName;

    @NotNull(message = "유저 아이디를 입력해주세요.")
    private Long projectUserId;

    private String projectIntro;
}
