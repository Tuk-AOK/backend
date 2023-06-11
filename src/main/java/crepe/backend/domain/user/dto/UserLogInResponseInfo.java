package crepe.backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@Builder
public class UserLogInResponseInfo {
    private final UUID userUuid;
}
