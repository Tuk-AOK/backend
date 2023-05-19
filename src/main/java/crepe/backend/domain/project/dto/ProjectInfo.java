
package crepe.backend.domain.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProjectInfo {
    private String projectName;
    private UUID projectUuid;
    private String projectIntro;

    @Builder
    public ProjectInfo(String projectName, UUID projectUuid, String projectIntro) {
        this.projectName = projectName;
        this.projectUuid = projectUuid;
        this.projectIntro = projectIntro;
    }
}
