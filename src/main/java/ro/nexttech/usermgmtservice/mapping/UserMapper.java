package ro.nexttech.usermgmtservice.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.nexttech.usermgmtservice.api.models.UserDTO;
import ro.nexttech.usermgmtservice.servicelayers.models.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "name", source = "userDTO.name")
    @Mapping(target = "email", source = "userDTO.email")
    UserEntity userDTOToUserEntity(int userId, UserDTO userDTO);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    UserDTO userEntityToUserDTO(UserEntity userEntity);

    List<UserDTO> userEntityListToUserDTOList(List<UserEntity> userEntityList);
}
