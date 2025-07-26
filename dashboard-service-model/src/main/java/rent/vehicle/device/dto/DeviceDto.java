package rent.vehicle.device.dto;

import lombok.*;
import rent.vehicle.device.enums.ConnectionStatus;
import rent.vehicle.device.enums.DeviceModel;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {
    private long id;
//    @UniqueConstraint() //todo Unique
    private String serialNumber;
    private DeviceConfigDto deviceConfig;
    private DeviceModel deviceModel;
    private ConnectionStatus connectionStatus;
    private String nodes;
}

