package crepe.backend.domain.user.mapper;

import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.entity.UserProject;
import crepe.backend.domain.project.dto.ProjectInfo;
import crepe.backend.domain.project.dto.ProjectInfoList;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.user.dto.UserCreate;
import crepe.backend.domain.user.dto.UserCreateInfo;
import crepe.backend.domain.user.dto.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public User mapUserInfoToUser(UserCreate usercreate) {
        return User.builder()
                .email(usercreate.getEmail())
                .password(usercreate.getPassword())
                .photo(usercreate.getPhoto())
                .nickname(usercreate.getNickname())
                .build();
    }

    public UserCreateInfo mapUserEntityToUserCreateInfo(User savedUser) {
        return UserCreateInfo.builder()
                .userUuid(savedUser.getUuid())
                .build();
    }

    public UserInfo mapUserEntityToUserInfo(User savedUser) {
        return UserInfo.builder()
                .userUuid(savedUser.getUuid())
                .email(savedUser.getEmail())
                .nickname(savedUser.getNickname())
                .photo(savedUser.getPhoto())
                .build();
    }
}