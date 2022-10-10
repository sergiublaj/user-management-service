package ro.nexttech.usermgmtservice.services;

import ro.nexttech.usermgmtservice.api.models.UserDTO;
import ro.nexttech.usermgmtservice.api.models.UserRequestModel;

import java.util.List;

/**
 * Service class to define user CRUD operations
 * This includes:
 * - Getting all users
 * - Getting specific user
 * - Updating specific user
 * - Deleting specific user
 */
public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUserById(Integer userId);

    UserDTO getUser(UserRequestModel userRequest);

    UserDTO saveUser(UserDTO newUser);

    UserDTO updateUser(Integer userId, UserDTO newUser);

    void deleteUserById(Integer userId);
}
