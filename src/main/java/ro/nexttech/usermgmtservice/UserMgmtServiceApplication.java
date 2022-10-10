package ro.nexttech.usermgmtservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ro.nexttech.usermgmtservice.servicelayers.models.UserEntity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class UserMgmtServiceApplication {

    public static final List<UserEntity> users = new ArrayList<>();

    public static void main(String[] args) {

		users.add(new UserEntity(1, "John Doe", "johndoe@mail.com"));
        users.add(new UserEntity(2, "Marry Smith", "marrysmith@mail.com"));
        users.add(new UserEntity(3, "Alex Pop", "alexpop@mail.com"));
        users.add(new UserEntity(4, "Nick Bunnyun", "nickbunnyun@mail.com"));

        SpringApplication.run(UserMgmtServiceApplication.class, args);
    }

}
