package rent.vehicle.device.dto;

import lombok.*;
import rent.vehicle.device.enums.Availability;
import rent.vehicle.device.enums.VehicleModel;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListVehiclesRequest {
    private String registrationNumber;
    private String registrationNumberPart;
    private double latitude;
    private double longitude;
    private double radius;
    private VehicleModel vehicleModel;
    private Availability availability;
    private Integer batteryStatusMin;
    private Integer batteryStatusMax;
    private String nodes;
    private ListDevicesRequest listDevicesRequest;
    private Set<Long> deviceIds;

}
