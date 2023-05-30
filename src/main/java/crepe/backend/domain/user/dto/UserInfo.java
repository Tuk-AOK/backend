package crepe.backend.domain.user.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserInfo {

    private UUID userUuid;

    private String email;

    private String nickname;

    private String photo;
}
