package rent.vehicle.device.dto;

import lombok.*;
import rent.vehicle.device.enums.Availability;
import rent.vehicle.device.enums.VehicleModel;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private long id;
    //TODO check not null, format reg number, not exist - для этого создать свой кастомный валидатор Unic и переношу туда логику
    private String registrationNumber;
    private VehicleModel vehicleModel;
    private DeviceDto device;
    private Availability availability;
    private PointFromLatLonDto pointFromLatLonDto;
    private Integer batteryStatus;
    private String nodes;
}
