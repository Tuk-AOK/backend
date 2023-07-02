package crepe.backend.domain.user.mapper;

import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.entity.UserProject;
import crepe.backend.domain.project.dto.ProjectInfo;
import crepe.backend.domain.project.dto.ProjectInfoList;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.dto.*;

import crepe.backend.global.service.S3Service;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final S3Service s3Service;
    public User mapUserInfoToUser(UserCreate userCreateRequest) {
        String userPhotoLink = s3Service.uploadFile(userCreateRequest.getUserPhoto());
        System.out.println(userPhotoLink);
        return User.builder()
                .email(userCreateRequest.getUserEmail())
                .password(userCreateRequest.getUserPassword())
                .nickname(userCreateRequest.getUserNickname())
                .photo(userPhotoLink)
                .build();
    }

    public UserCreateInfo mapUserEntityToUserCreateInfo(User savedUser) {
        return UserCreateInfo.builder()
                .userUuid(savedUser.getUuid())
                .build();
    }

    public UserInfo mapUserEntityToUserInfo(User savedUser) {
        return UserInfo.builder()
                .userId(savedUser.getId())
                .userUuid(savedUser.getUuid())
                .userEmail(savedUser.getEmail())
                .userNickname(savedUser.getNickname())
                .userPhoto(savedUser.getPhoto())
                .build();
    }

    public UserLogInResponseInfo getUserLogInResponseInfo (User user)
    {
        return UserLogInResponseInfo.builder()
                .userUuid(user.getUuid())
                .build();
    }

    public UserNicknameInfo mapUserNicknameInfoToUser(User user)
    {
        return UserNicknameInfo.builder()
                .userUuid(user.getUuid())
                .build();
    }
}