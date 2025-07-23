package rent.vehicle.dashboardserviceapi.device.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.device.config.CustomPage;
import rent.vehicle.dto.DeviceConfigCreateUpdateDto;
import rent.vehicle.dto.DeviceConfigDto;
import rent.vehicle.dto.ListDeviceConfigsRequest;

public interface DeviceConfigService {

    Mono<DeviceConfigDto> createDeviceConfig(DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto);

    Mono<DeviceConfigDto> updateDeviceConfig(long id, DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto);

    Mono<DeviceConfigDto> findDeviceConfigById(long id);

    Mono<CustomPage<DeviceConfigDto>> findAllDeviceConfig(Pageable pageable);

    Mono<CustomPage<DeviceConfigDto>> getListDevicesConfigByParam(ListDeviceConfigsRequest listDeviceConfigsRequest, Pageable pageable);

    Mono<Void> removeDeviceConfig(long id);
}
