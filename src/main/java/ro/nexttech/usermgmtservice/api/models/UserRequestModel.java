package ro.nexttech.usermgmtservice.api.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.ws.rs.QueryParam;

import static ro.nexttech.usermgmtservice.api.constants.ApiQueryParamsConstants.USER_EMAIL;
import static ro.nexttech.usermgmtservice.api.constants.ApiQueryParamsConstants.USER_NAME;

@Getter
@Setter
@ToString
public class UserRequestModel {
    @QueryParam(USER_NAME)
    private String name;
    @QueryParam(USER_EMAIL)
    private String email;
}
