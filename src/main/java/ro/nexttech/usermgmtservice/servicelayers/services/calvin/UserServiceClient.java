package ro.nexttech.usermgmtservice.servicelayers.services.calvin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.nexttech.usermgmtservice.api.models.UserDTO;
import ro.nexttech.usermgmtservice.api.models.UserRequestModel;
import ro.nexttech.usermgmtservice.mapping.UserMapper;
import ro.nexttech.usermgmtservice.servicelayers.models.UserEntity;

import java.util.ArrayList;
import java.util.List;

import static ro.nexttech.usermgmtservice.UserMgmtServiceApplication.users;

@Component
@RequiredArgsConstructor
public class UserServiceClient {
    private final UserMapper userMapper;

    public List<UserEntity> getUsers() {
        return new ArrayList<>(users);
    }

    public UserEntity getUserById(Integer userId) {
//        for (UserEntity user : users) {
//            if (user.getId().equals(userId)) {
//                return user;
//            }
//        }

//        return null;
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);


//        return super.callOnCalvin(Session, ServiceClass, Request(IntegerUserId), UserEntity.class);
    }

    public UserEntity getUser(UserRequestModel userRequest) {
        for (UserEntity user : users) {
            if (userRequest.getName() != null && user.getName().contains(userRequest.getName())
                    || userRequest.getEmail() != null && user.getEmail().contains(userRequest.getEmail())) {
                return user;
            }
        }

        return null;
    }

    public UserEntity saveUser(UserDTO newUser) {
        int userId = users.isEmpty() ? 0 : users.get(users.size() - 1).getId();
        UserEntity user = userMapper.userDTOToUserEntity(userId, newUser);
        users.add(user);
        return user;
    }

    public UserEntity updateUser(Integer userId, UserDTO newUser) {
        UserEntity userToUpdate = null;
        for (UserEntity user : users) {
            if (user.getId().equals(userId)) {
                userToUpdate = user;
                break;
            }
        }

        if (userToUpdate == null) {
            return null;
        }

        userToUpdate.setName(newUser.getName());
        userToUpdate.setEmail(newUser.getEmail());
        return userToUpdate;
    }

    public boolean deleteUserById(Integer userId) {
        int usersToDeleteIndex = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                usersToDeleteIndex = i;
                break;
            }
        }

        if (usersToDeleteIndex == -1) {
            return false;
        }

        users.remove(usersToDeleteIndex);
        return true;
    }
}
