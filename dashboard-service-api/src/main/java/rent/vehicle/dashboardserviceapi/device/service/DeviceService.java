package rent.vehicle.dashboardserviceapi.device.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.device.config.CustomPage;
import rent.vehicle.dto.DeviceCreateUpdateDto;
import rent.vehicle.dto.DeviceDto;
import rent.vehicle.dto.ListDevicesRequest;

public interface DeviceService {
    Mono<DeviceDto> createDevice(DeviceCreateUpdateDto deviceCreateUpdateDto);

    Mono<DeviceDto> updateDevice(long id, DeviceCreateUpdateDto deviceCreateUpdateDto);

    Mono<DeviceDto> findDeviceById(long id);

    Mono<CustomPage<DeviceDto>> findAllDevices(Pageable pageable);

    Mono<CustomPage<DeviceDto>> findListDevicesByParam(ListDevicesRequest listDevicesRequest, Pageable pageable);

    Mono<CustomPage<DeviceDto>> findDevicesWithoutVehicle(Pageable pageable);

    Mono<Void> removeDevice(long id);
}
