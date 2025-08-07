package rent.vehicle.dashboardserviceapi.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChangePasswordRequestDto {


    String oldPassword;


    String newPassword;
}
