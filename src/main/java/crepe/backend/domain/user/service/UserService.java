package crepe.backend.domain.user.service;

import crepe.backend.domain.project.domain.entity.Project;
import crepe.backend.domain.project.domain.repository.ProjectRepository;
import crepe.backend.domain.project.dto.ProjectInfo;
import crepe.backend.domain.project.dto.ProjectInfoList;
import crepe.backend.domain.project.exception.NotFoundProjectEntityException;
import crepe.backend.domain.user.domain.entity.User;
import crepe.backend.domain.project.domain.entity.UserProject;
import crepe.backend.domain.user.dto.UserCreate;
import crepe.backend.domain.user.dto.UserCreateInfo;
import crepe.backend.domain.user.dto.UserInfo;
import crepe.backend.domain.project.domain.repository.UserProjectRepository;
import crepe.backend.domain.user.domain.repository.UserRepository;
import crepe.backend.domain.user.mapper.UserMapper;
import crepe.backend.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import crepe.backend.domain.user.exception.NotFoundUserEntityException;


import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProjectRepository userProjectRepository;
    private final ProjectRepository projectRepository;
    private final UserMapper userMapper;


    // 테스트를 위한 유저 추가 코드
    public UserCreateInfo userCreate(UserCreate userCreateRequest) {

        User newUser = userMapper.mapUserInfoToUser(userCreateRequest);
        User savedUser = userRepository.save(newUser);

        return userMapper.mapUserEntityToUserCreateInfo(savedUser);
    }

    // uuid로 유저 찾기
    public UserInfo findUserInfoById(UUID userUuid) {
        User findUser = findUserByUuid(userUuid);
        return userMapper.mapUserEntityToUserInfo(findUser);
    }
    

    public void updateUserInfo(UUID userUuid, Map<String, String> user)
    {
        User oUser = findUserByUuid(userUuid);

        oUser.updateUser(user.get("email"), user.get("password"), user.get("nickname"), user.get("photo"));

        userRepository.save(oUser);
    }

    public void deleteUser(UUID userUuid) {
        User user = findUserByUuid(userUuid);
        userRepository.deleteById(user.getId());
    }

    // 해당 uuid의 유저를 얻기 위한 함수
    public User findUserByUuid(UUID userUuid)
    {
        return userRepository.findUserByUuidAndIsActiveTrue(userUuid).orElseThrow(NotFoundUserEntityException::new);
    }

    private List<UserProject> findUserProjectByUser(User user)
    {
        return userProjectRepository.findAllByUserAndIsActiveTrue(user);
    }

    public User findUserById(Long userId)
    {
        return userRepository.findUserByIdAndIsActiveTrue(userId).orElseThrow(NotFoundUserEntityException::new);
    }
}
