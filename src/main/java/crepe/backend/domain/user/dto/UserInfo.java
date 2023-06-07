package crepe.backend.domain.user.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserInfo {

    private Long userId;

    private UUID userUuid;

    private String userEmail;

    private String userNickname;

    private String userPhoto;
}
