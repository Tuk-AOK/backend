package crepe.backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@Builder
public class UserLogInRequestInfo {
    @NotBlank(message = "이메일을 입력해주세요.")
    private final String userEmail;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private final String userPassword;
}
