package ro.nexttech.usermgmtservice.api.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private String name;
    private String email;
}
