package ro.nexttech.usermgmtservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.nexttech.usermgmtservice.api.common.UserNotFoundException;
import ro.nexttech.usermgmtservice.api.models.UserDTO;
import ro.nexttech.usermgmtservice.api.models.UserRequestModel;
import ro.nexttech.usermgmtservice.mapping.UserMapper;
import ro.nexttech.usermgmtservice.servicelayers.models.UserEntity;
import ro.nexttech.usermgmtservice.servicelayers.services.calvin.UserServiceClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServiceClient userServiceClient;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> userEntityList = userServiceClient.getUsers();

        return userMapper.userEntityListToUserDTOList(userEntityList);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        UserEntity userEntity = userServiceClient.getUserById(userId);
        if (userEntity == null) {
            throw new UserNotFoundException("User with id: " + userId + " was not found!");
        }

        return userMapper.userEntityToUserDTO(userEntity);
    }

    @Override
    public UserDTO getUser(UserRequestModel userRequest) {
        UserEntity userEntity = userServiceClient.getUser(userRequest);

        return userMapper.userEntityToUserDTO(userEntity);
    }

    @Override
    public UserDTO saveUser(UserDTO newUser) {
        UserEntity userEntity = userServiceClient.saveUser(newUser);

        return userMapper.userEntityToUserDTO(userEntity);
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO newUser) {
        UserEntity userEntity = userServiceClient.updateUser(userId, newUser);
        if (userEntity == null) {
            throw new UserNotFoundException("User with id: " + userId + " was not found!");
        }

        return userMapper.userEntityToUserDTO(userEntity);
    }

    @Override
    public void deleteUserById(Integer userId) {
        boolean userDeleted = userServiceClient.deleteUserById(userId);
        if (!userDeleted) {
            throw new UserNotFoundException("User with id: " + userId + " was not found!");
        }
    }
}
