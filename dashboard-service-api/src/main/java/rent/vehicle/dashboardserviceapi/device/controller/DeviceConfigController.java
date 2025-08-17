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
        log.info("Received request to CREATE device config: {}", deviceConfigCreateUpdateDto);
        return deviceConfigService.createDeviceConfig(deviceConfigCreateUpdateDto)
                .doOnSuccess(deviceConfigDto -> log.info("Successfully created device config: {}", deviceConfigDto))
                .doOnError(e -> log.error("Error while creating device config", e));
    }

    @PutMapping(ApiPaths.PATH_ID)
    public Mono<DeviceConfigDto> updateDeviceConfig (@PathVariable long id, @RequestBody DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto) {
        log.info("Received request to UPDATE device config id={}, payload={}", id, deviceConfigCreateUpdateDto);
        return deviceConfigService.updateDeviceConfig (id, deviceConfigCreateUpdateDto)
                .doOnSuccess(dto -> log.info("Successfully updated device config id={}", dto.getId()))
                .doOnError(e -> log.error("Error while updating device config id={}", id, e));
    }

    @GetMapping(ApiPaths.PATH_ID)
    public Mono<DeviceConfigDto> findDeviceConfigById (@PathVariable long id) {
        log.debug("Received request to GET device config by id={}", id);
        return deviceConfigService.findDeviceConfigById(id)
                .doOnSuccess(dto -> log.info("Found device config id={}", dto.getId()))
                .doOnError(e -> log.error("Error while fetching device config id={}", id, e));
    }

    @GetMapping(ApiPaths.PATH_LIST)
    public Mono<CustomPage<DeviceConfigDto>> findAllDeviceConfig(
            Pageable pageable) {
        log.debug("Received request to LIST all device configs, pageable={}", pageable);
        return deviceConfigService.findAllDeviceConfig(pageable)
                .doOnSuccess(page -> log.info("Fetched {} device configs", page.getContent().size()))
                .doOnError(e -> log.error("Error while fetching all device configs", e));
    }

    @GetMapping(ApiPaths.PATH_SEARCH)
    public Mono<CustomPage<DeviceConfigDto>> findListDevicesConfigByParam(
            @ModelAttribute ListDeviceConfigsRequest listDeviceConfigsRequest,
            Pageable pageable) {
        log.debug("Received request to SEARCH device configs with params={}, pageable={}", listDeviceConfigsRequest, pageable);
        return deviceConfigService.getListDevicesConfigByParam(listDeviceConfigsRequest, pageable)
                .doOnSuccess(page -> log.info("Search returned {} device configs", page.getContent().size()))
                .doOnError(e -> log.error("Error while searching device configs with params={}", listDeviceConfigsRequest, e));
    }

    @DeleteMapping(ApiPaths.PATH_ID)
    public void removeDeviceConfig (@PathVariable long id) {
        log.warn("Received request to DELETE device config id={}", id);
        deviceConfigService.removeDeviceConfig(id);
        log.info("Successfully deleted device config id={}", id);
    }




}

