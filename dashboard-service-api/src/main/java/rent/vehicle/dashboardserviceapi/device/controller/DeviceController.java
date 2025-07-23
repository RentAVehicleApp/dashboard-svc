package rent.vehicle.dashboardserviceapi.device.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.device.config.CustomPage;
import rent.vehicle.dashboardserviceapi.device.service.DeviceConfigService;
import rent.vehicle.dashboardserviceapi.device.service.DeviceService;
import rent.vehicle.dto.DeviceCreateUpdateDto;
import rent.vehicle.dto.DeviceDto;
import rent.vehicle.dto.ListDevicesRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    public Mono<DeviceDto> createDevice(@RequestBody DeviceCreateUpdateDto deviceCreateUpdateDto) {
        return deviceService.createDevice(deviceCreateUpdateDto);
    }

    @PutMapping(ApiPaths.PATH_ID)
    public Mono<DeviceDto> updateDevice(@PathVariable long id, @RequestBody DeviceCreateUpdateDto deviceCreateUpdateDto) {
        return deviceService.updateDevice(id, deviceCreateUpdateDto);
    }

    @GetMapping(ApiPaths.PATH_ID)
    public Mono<DeviceDto> findDeviceById(@PathVariable long id) {
        return deviceService.findDeviceById(id);
    }

    @GetMapping(ApiPaths.PATH_LIST)
    public Mono<CustomPage<DeviceDto>> findAllDevices(
            @PageableDefault(size = 2)
            Pageable pageable) {
        return deviceService.findAllDevices(pageable);
    }

    @GetMapping(ApiPaths.PATH_SEARCH)
    public Mono<CustomPage<DeviceDto>> findListDevicesByParam(
            @ModelAttribute ListDevicesRequest listDevicesRequest,
            @PageableDefault(size = 2)
            Pageable pageable) {
        return deviceService.findListDevicesByParam(listDevicesRequest, pageable);
    }

    @GetMapping(ApiPaths.WITHOUT_VEHICLE)
    public Mono<CustomPage<DeviceDto>> findDevicesWithoutVehicle(Pageable pageable) {
        return deviceService.findDevicesWithoutVehicle(pageable);
    }

    @DeleteMapping(ApiPaths.PATH_ID)
    public Mono<Void> removeDevice(@PathVariable long id) {
        return deviceService.removeDevice(id);
    }

}

