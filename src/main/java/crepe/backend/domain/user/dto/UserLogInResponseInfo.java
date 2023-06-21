package crepe.backend.domain.user.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserLogInResponseInfo {
    private UUID userUuid;
}
