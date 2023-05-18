package crepe.backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserInfo {

    private UUID userUuid;

    private String email;

    private String nickname;

    private String photo;

    @Builder
    public UserInfo(UUID userUuid, String email, String nickname, String photo)
    {
        this.userUuid = userUuid;
        this.email = email;
        this.nickname = nickname;
        this.photo = photo;
    }
}
