package crepe.backend.domain.user.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserCreate {
    private String userEmail;
    private String userPassword;
    private MultipartFile userPhoto;
    private String userNickname;
}
