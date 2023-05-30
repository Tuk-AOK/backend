
package crepe.backend.domain.project.dto;

import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProjectInfo {
    private String projectName;
    private UUID projectUuid;
    private String projectIntro;
    private String projectPreview;
}
