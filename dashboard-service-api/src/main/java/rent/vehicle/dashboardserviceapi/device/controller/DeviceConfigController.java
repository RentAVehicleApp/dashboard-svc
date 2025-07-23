package rent.vehicle.dashboardserviceapi.device.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.device.config.CustomPage;
import rent.vehicle.dashboardserviceapi.device.service.DeviceConfigService;
import rent.vehicle.dto.DeviceConfigCreateUpdateDto;
import rent.vehicle.dto.DeviceConfigDto;
import rent.vehicle.dto.ListDeviceConfigsRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/v1/deviceconfig")
public class DeviceConfigController {
    private final DeviceConfigService deviceConfigService;

    @PostMapping
    public Mono<DeviceConfigDto> createDeviceConfig (@RequestBody DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto) {
        return deviceConfigService.createDeviceConfig(deviceConfigCreateUpdateDto);
    }

    @PutMapping(ApiPaths.PATH_ID)
    public Mono<DeviceConfigDto> updateDeviceConfig (@PathVariable long id, @RequestBody DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto) {
        return deviceConfigService.updateDeviceConfig (id, deviceConfigCreateUpdateDto);
    }

    @GetMapping(ApiPaths.PATH_ID)
    public Mono<DeviceConfigDto> findDeviceConfigById (@PathVariable long id) {
        return deviceConfigService.findDeviceConfigById (id);
    }

    @GetMapping(ApiPaths.PATH_LIST)
    public Mono<CustomPage<DeviceConfigDto>> findAllDeviceConfig(
            Pageable pageable) {
        return deviceConfigService.findAllDeviceConfig(pageable);
    }

    @GetMapping(ApiPaths.PATH_SEARCH)
    public Mono<CustomPage<DeviceConfigDto>> findListDevicesConfigByParam(
            @ModelAttribute ListDeviceConfigsRequest listDeviceConfigsRequest,
            Pageable pageable) {
        return deviceConfigService.getListDevicesConfigByParam(listDeviceConfigsRequest, pageable);
    }

    @DeleteMapping(ApiPaths.PATH_ID)
    public void removeDeviceConfig (@PathVariable long id) {
        deviceConfigService.removeDeviceConfig (id);
    }




}

