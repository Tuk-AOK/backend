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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // 테스트를 위한 유저 추가와 관련된 코드
    public UserCreateInfo userCreate(UserCreate usercreate) {
        User userdata = userMapper.mapUserInfoToUser(usercreate);
        User savedata = userRepository.save(userdata);
        return userMapper.mapUserEntityToUserCreateInfo(savedata);
    }

    // uuid로 유저 찾기
    public UserInfo findUserInfoById(UUID userUuid) {
        User findUser = findUserByUuId(userUuid);
        return userMapper.mapUserEntityToUserInfo(findUser);
    }
<<<<<<< HEAD

    // uuid를 이용해서 유저가 포함되어있는 프로젝트 찾는 함수
    public ProjectInfoList findUserProjectById(UUID userUuid, int page)
    {
        Pageable pageable = PageRequest.of(page, 8);
        User findUser = findUserByUuId(userUuid);
        Page<UserProject> userProjects = findUserProjectByUser(findUser, pageable);
        List<Project> projects = userMapper.getProjectList(userProjects);
        return userMapper.getProjectInfoList(projects);
    }
=======
    
>>>>>>> b5a5a1aaed20543a0ef4246081313b915cb1087d

    public void updateUserInfo(UUID userUuid, Map<String, String> user)
    {
        User oUser = findUserByUuId(userUuid);

        oUser.updateUser(user.get("email"), user.get("password"), user.get("nickname"), user.get("photo"));

        userRepository.save(oUser);
    }

    public void deleteUser(UUID userUuid) {
        User user = findUserByUuId(userUuid);
        userRepository.deleteById(user.getId());
    }

    // 해당 uuid의 유저를 얻기 위한 함수
    public User findUserByUuId(UUID userUuid)
    {
        return userRepository.findUserByUuidAndIsActiveTrue(userUuid).orElseThrow(NotFoundUserEntityException::new);
    }

    private Page<UserProject> findUserProjectByUser(User user, Pageable pageable)
    {
        return userProjectRepository.findAllByUserAndIsActiveTrueOrderByIdDesc(user, pageable);
    }

    public User findUserById(Long userId)
    {
        return userRepository.findUserByIdAndIsActiveTrue(userId).orElseThrow(NotFoundUserEntityException::new);
    }
}
