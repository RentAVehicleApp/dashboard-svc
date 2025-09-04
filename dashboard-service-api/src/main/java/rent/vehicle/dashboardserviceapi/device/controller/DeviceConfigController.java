package rent.vehicle.dashboardserviceapi.device.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.device.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.device.service.DeviceConfigService;
import rent.vehicle.device.dto.DeviceConfigCreateUpdateDto;
import rent.vehicle.device.dto.DeviceConfigDto;
import rent.vehicle.device.dto.ListDeviceConfigsRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/support/v1/deviceconfig")
public class DeviceConfigController {
    private final DeviceConfigService deviceConfigService;

    @PostMapping
    public Mono<DeviceConfigDto> createDeviceConfig (@RequestBody DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto) {
        log.info("Request to create device config with data: {}", deviceConfigCreateUpdateDto);
        return deviceConfigService.createDeviceConfig(deviceConfigCreateUpdateDto)
                .doOnSuccess(config -> log.info("Successfully created device config: {}", config))
                .doOnError(e -> log.error("Error while creating device config with data: {}", deviceConfigCreateUpdateDto, e));
    }

    @PutMapping(ApiPaths.PATH_ID)
    public Mono<DeviceConfigDto> updateDeviceConfig (@PathVariable long id, @RequestBody DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto) {
        log.info("Request to update device config with id={}, data: {}", id, deviceConfigCreateUpdateDto);
        return deviceConfigService.updateDeviceConfig(id, deviceConfigCreateUpdateDto)
                .doOnSuccess(config -> log.info("Successfully updated device config: {}", config))
                .doOnError(e -> log.error("Error while updating device config with id={}, data={}", id, deviceConfigCreateUpdateDto, e));
    }

    @GetMapping(ApiPaths.PATH_ID)
    public Mono<DeviceConfigDto> findDeviceConfigById (@PathVariable long id) {
        log.debug("Request to find device config by id={}", id);
        return deviceConfigService.findDeviceConfigById(id)
                .doOnSuccess(config -> log.debug("Found device config: {}", config))
                .doOnError(e -> log.error("Error while finding device config with id={}", id, e));
    }

    @GetMapping(ApiPaths.PATH_LIST)
    public Mono<CustomPage<DeviceConfigDto>> findAllDeviceConfig(
            Pageable pageable) {
        log.debug("Request to find all device configs with pageable={}", pageable);
        return deviceConfigService.findAllDeviceConfig(pageable)
                .doOnSuccess(page -> log.debug("Found {} device configs", page.getContent().size()))
                .doOnError(e -> log.error("Error while fetching device configs, pageable={}", pageable, e));
    }

    @GetMapping(ApiPaths.PATH_SEARCH)
    public Mono<CustomPage<DeviceConfigDto>> findListDevicesConfigByParam(
            @ModelAttribute ListDeviceConfigsRequest listDeviceConfigsRequest,
            Pageable pageable) {
        log.debug("Request to search device configs by params={}, pageable={}", listDeviceConfigsRequest, pageable);
        return deviceConfigService.getListDevicesConfigByParam(listDeviceConfigsRequest, pageable)
                .doOnSuccess(page -> log.debug("Found {} device configs by params", page.getContent().size()))
                .doOnError(e -> log.error("Error while searching device configs by params={}, pageable={}", listDeviceConfigsRequest, pageable, e));
    }

    @DeleteMapping(ApiPaths.PATH_ID)
    public void removeDeviceConfig (@PathVariable long id) {
        log.info("Request to remove device config with id={}", id);
        deviceConfigService.removeDeviceConfig(id); // Если service возвращает Mono, можно добавить doOnSuccess/doOnError аналогично
    }




}

