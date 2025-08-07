package rent.vehicle.dashboardserviceapi.security.dto.token;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TokenResponseDto {

	private String accessToken;

	private String refreshToken;
}
