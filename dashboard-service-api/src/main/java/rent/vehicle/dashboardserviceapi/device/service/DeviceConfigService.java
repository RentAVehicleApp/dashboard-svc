package rent.vehicle.dashboardserviceapi.device.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.device.dto.DeviceConfigCreateUpdateDto;
import rent.vehicle.device.dto.DeviceConfigDto;
import rent.vehicle.device.dto.ListDeviceConfigsRequest;

public interface DeviceConfigService {

    Mono<DeviceConfigDto> createDeviceConfig(DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto);

    Mono<DeviceConfigDto> updateDeviceConfig(long id, DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto);

    Mono<DeviceConfigDto> findDeviceConfigById(long id);

    Mono<CustomPage<DeviceConfigDto>> findAllDeviceConfig(Pageable pageable);

    Mono<CustomPage<DeviceConfigDto>> getListDevicesConfigByParam(ListDeviceConfigsRequest listDeviceConfigsRequest, Pageable pageable);

    Mono<Void> removeDeviceConfig(long id);
}
