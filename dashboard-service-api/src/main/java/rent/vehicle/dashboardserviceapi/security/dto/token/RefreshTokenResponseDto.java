package rent.vehicle.dashboardserviceapi.security.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RefreshTokenResponseDto {
	String accessToken;
}
