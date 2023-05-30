package crepe.backend.domain.project.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProjectPreviewUpdateRequest {
    private MultipartFile ProjectPreview;
}
