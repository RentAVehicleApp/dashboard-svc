package rent.vehicle.device.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointFromLatLonDto {
    private String latitude;
    private String longitude;
}
