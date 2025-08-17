package rent.vehicle.dashboardserviceapi.device.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.device.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.device.service.DeviceService;
import rent.vehicle.device.dto.DeviceCreateUpdateDto;
import rent.vehicle.device.dto.DeviceDto;
import rent.vehicle.device.dto.ListDevicesRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/support/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    public Mono<DeviceDto> createDevice(@RequestBody DeviceCreateUpdateDto deviceCreateUpdateDto) {
        log.info("Request to create device with data: {}", deviceCreateUpdateDto);
        return deviceService.createDevice(deviceCreateUpdateDto)
                .doOnSuccess(device -> log.info("Successfully created device: {}", device))
                .doOnError(e -> log.error("Error while creating device with data: {}", deviceCreateUpdateDto, e));
    }

    @PutMapping(ApiPaths.PATH_ID)
    public Mono<DeviceDto> updateDevice(@PathVariable long id, @RequestBody DeviceCreateUpdateDto deviceCreateUpdateDto) {
        log.info("Request to update device with id={}, data: {}", id, deviceCreateUpdateDto);
        return deviceService.updateDevice(id, deviceCreateUpdateDto)
                .doOnSuccess(device -> log.info("Successfully updated device: {}", device))
                .doOnError(e -> log.error("Error while updating device with id={}, data={}", id, deviceCreateUpdateDto, e));
    }

    @GetMapping(ApiPaths.PATH_ID)
    public Mono<DeviceDto> findDeviceById(@PathVariable long id) {
        log.debug("Request to find device by id={}", id);
        return deviceService.findDeviceById(id)
                .doOnSuccess(device -> log.debug("Found device: {}", device))
                .doOnError(e -> log.error("Error while finding device with id={}", id, e));
    }

    @GetMapping(ApiPaths.PATH_LIST)
    public Mono<CustomPage<DeviceDto>> findAllDevices(
            @PageableDefault(size = 2)
            Pageable pageable) {
        log.debug("Request to find all devices with pageable={}", pageable);
        return deviceService.findAllDevices(pageable)
                .doOnSuccess(page -> log.debug("Found {} devices", page.getContent().size()))
                .doOnError(e -> log.error("Error while fetching devices, pageable={}", pageable, e));
    }

    @GetMapping(ApiPaths.PATH_SEARCH)
    public Mono<CustomPage<DeviceDto>> findListDevicesByParam(
            @ModelAttribute ListDevicesRequest listDevicesRequest,
            @PageableDefault(size = 2)
            Pageable pageable) {
        log.debug("Request to search devices by params={}, pageable={}", listDevicesRequest, pageable);
        return deviceService.findListDevicesByParam(listDevicesRequest, pageable)
                .doOnSuccess(page -> log.debug("Found {} devices by params", page.getContent().size()))
                .doOnError(e -> log.error("Error while searching devices by params={}, pageable={}", listDevicesRequest, pageable, e));
    }

    @GetMapping(ApiPaths.WITHOUT_VEHICLE)
    public Mono<CustomPage<DeviceDto>> findDevicesWithoutVehicle(Pageable pageable) {
        log.debug("Request to find devices without vehicle, pageable={}", pageable);
        return deviceService.findDevicesWithoutVehicle(pageable)
                .doOnSuccess(page -> log.debug("Found {} devices without vehicle", page.getContent().size()))
                .doOnError(e -> log.error("Error while fetching devices without vehicle, pageable={}", pageable, e));
    }

    @DeleteMapping(ApiPaths.PATH_ID)
    public Mono<Void> removeDevice(@PathVariable long id) {
        log.info("Request to remove device with id={}", id);
        return deviceService.removeDevice(id)
                .doOnSuccess(v -> log.info("Successfully removed device with id={}", id))
                .doOnError(e -> log.error("Error while removing device with id={}", id, e));
    }

}

