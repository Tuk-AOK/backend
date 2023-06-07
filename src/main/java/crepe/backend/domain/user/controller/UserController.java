package crepe.backend.domain.user.controller;

import crepe.backend.domain.project.dto.ProjectInfoList;
import crepe.backend.domain.user.dto.UserCreate;
import crepe.backend.domain.user.dto.UserCreateInfo;
import crepe.backend.domain.user.dto.UserInfo;
import crepe.backend.domain.user.service.UserService;
import crepe.backend.global.response.ResultResponse;
import crepe.backend.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;
import java.util.UUID;

import static crepe.backend.global.response.ResultCode.*;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // API 테스트를 위한 유저 추가
    @PostMapping
    public ResponseEntity<ResultResponse> createUser(@Valid @ModelAttribute UserCreate userCreateRequest)
    {
        UserCreateInfo userInfo = userService.userCreate(userCreateRequest);
        return ResponseEntity.ok(ResultResponse.of(CREATE_USER_SUCCESS, userInfo));
    }

    // UUID를 이용해 유저 찾기
    @GetMapping("/{uuid}")
    public ResponseEntity<ResultResponse> findById(@PathVariable UUID uuid)
    {
        UserInfo userInfo = userService.findUserInfoById(uuid);
        return ResponseEntity.ok(ResultResponse.of(READ_ONE_USER_SUCCESS, userInfo));
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<ResultResponse> updateUser(@PathVariable UUID uuid, @RequestBody Map<String,String> request)
    {
        userService.updateUserInfo(uuid, request);
        return ResponseEntity.ok(ResultResponse.of(UPDATE_USER_SUCCESS, ""));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ResultResponse> deleteUser(@PathVariable UUID uuid) {
        userService.deleteUser(uuid);
        return ResponseEntity.ok(ResultResponse.of(DELETE_USER_SUCCESS, ""));
    }


}
