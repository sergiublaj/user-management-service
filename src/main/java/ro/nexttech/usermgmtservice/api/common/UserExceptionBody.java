package ro.nexttech.usermgmtservice.api.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class UserExceptionBody {
    private String message;
    private Timestamp date;
}
