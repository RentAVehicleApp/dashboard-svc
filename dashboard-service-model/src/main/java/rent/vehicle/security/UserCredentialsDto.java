package rent.vehicle.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialsDto {
    private String login;
    private String password;
}
