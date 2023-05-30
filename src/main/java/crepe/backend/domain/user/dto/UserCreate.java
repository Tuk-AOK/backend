package crepe.backend.domain.user.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserCreate {
    private String email;
    private String password;
    private String photo;
    private String nickname;

}
