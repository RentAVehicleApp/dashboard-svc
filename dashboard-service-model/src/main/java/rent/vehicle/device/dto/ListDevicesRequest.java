package rent.vehicle.device.dto;

import lombok.*;
import rent.vehicle.device.enums.ConnectionStatus;
import rent.vehicle.device.enums.DeviceModel;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListDevicesRequest {

    private String serialNumber;
    private String serialNumberPart;
    private DeviceModel deviceModel;
    private ConnectionStatus connectionStatus;
    private String nodesPart;

    private ListDeviceConfigsRequest listDeviceConfigsRequest;
    private Set<Long> deviceConfigIds;
}
