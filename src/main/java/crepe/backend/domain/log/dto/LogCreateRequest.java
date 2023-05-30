package crepe.backend.domain.log.dto;

import lombok.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LogCreateRequest {

    private List<MultipartFile> files;
    private Long userId;
    private Long branchId;
    private String message;
    private MultipartFile preview;
}
