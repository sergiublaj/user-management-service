package ro.nexttech.usermgmtservice.servicelayers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserEntity {
    private Integer id;
    private String name;
    private String email;
}
