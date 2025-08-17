package rent.vehicle.dashboardserviceapi.device.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.device.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.device.service.VehicleService;
import rent.vehicle.device.dto.PointFromLatLonDto;
import rent.vehicle.device.dto.VehicleCreateUpdateDto;
import rent.vehicle.device.dto.VehicleDto;
import rent.vehicle.device.dto.ListVehiclesRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/support/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public Mono<VehicleDto> createVehicle(@RequestBody VehicleCreateUpdateDto vehicleCreateUpdateDto) {
        log.info("Request to create vehicle: {}", vehicleCreateUpdateDto);
        return vehicleService.createVehicle(vehicleCreateUpdateDto)
                .doOnSuccess(vehicle -> log.info("Successfully created vehicle: {}", vehicle))
                .doOnError(e -> log.error("Error while creating vehicle: {}", vehicleCreateUpdateDto, e));
    }

    @PutMapping(ApiPaths.PATH_ID)
    public Mono<VehicleDto> updateVehicle(@PathVariable long id, @RequestBody VehicleCreateUpdateDto vehicleCreateUpdateDto) {
        log.info("Request to update vehicle id={}, data={}", id, vehicleCreateUpdateDto);
        return vehicleService.updateVehicle(id, vehicleCreateUpdateDto)
                .doOnSuccess(vehicle -> log.info("Successfully updated vehicle: {}", vehicle))
                .doOnError(e -> log.error("Error while updating vehicle id={}, data={}", id, vehicleCreateUpdateDto, e));
    }

    @GetMapping(ApiPaths.PATH_ID)
    public Mono<VehicleDto> findVehicleById(@PathVariable long id) {
        log.debug("Request to find vehicle by id={}", id);
        return vehicleService.findVehicleById(id)
                .doOnSuccess(vehicle -> log.debug("Found vehicle: {}", vehicle))
                .doOnError(e -> log.error("Error while finding vehicle with id={}", id, e));
    }

    @GetMapping(ApiPaths.PATH_LIST)
    public Mono<CustomPage<VehicleDto>> findAllVehicle(
            @PageableDefault(size = 2)
            Pageable pageable) {
        log.debug("Request to find all vehicles, pageable={}", pageable);
        return vehicleService.findAllVehicle(pageable)
                .doOnSuccess(page -> log.debug("Found {} vehicles", page.getContent().size()))
                .doOnError(e -> log.error("Error while fetching all vehicles, pageable={}", pageable, e));
    }

    @GetMapping(ApiPaths.PATH_SEARCH)
    public Mono<CustomPage<VehicleDto>> findListVehiclesByParam(
            @ModelAttribute ListVehiclesRequest listVehiclesRequest,
            @PageableDefault(size = 2)
            Pageable pageable) {
        log.debug("Request to search vehicles by params={}, pageable={}", listVehiclesRequest, pageable);
        return vehicleService.getListVehiclesByParam(listVehiclesRequest, pageable)
                .doOnSuccess(page -> log.debug("Found {} vehicles by params", page.getContent().size()))
                .doOnError(e -> log.error("Error while searching vehicles by params={}, pageable={}", listVehiclesRequest, pageable, e));
    }

    @GetMapping(ApiPaths.PATH_NEARBY)
    public Mono<CustomPage<VehicleDto>> findNearbyVehicles(
            @ModelAttribute PointFromLatLonDto pointFromLatLonDto,
            @RequestParam long radiusMeters,
            @PageableDefault() Pageable pageable
    ) {
        log.debug("Request to find nearby vehicles at point={}, radius={}m, pageable={}", pointFromLatLonDto, radiusMeters, pageable);
        return vehicleService.findNearbyVehicles(pointFromLatLonDto, radiusMeters, pageable)
                .doOnSuccess(page -> log.debug("Found {} nearby vehicles", page.getContent().size()))
                .doOnError(e -> log.error("Error while finding nearby vehicles at point={}, radius={}m, pageable={}", pointFromLatLonDto, radiusMeters, pageable, e));
    }


    @DeleteMapping(ApiPaths.PATH_ID)
    public Mono<Void> removeVehicle(@PathVariable long id) {
        log.info("Request to remove vehicle with id={}", id);
        return vehicleService.removeVehicle(id)
                .doOnSuccess(v -> log.info("Successfully removed vehicle with id={}", id))
                .doOnError(e -> log.error("Error while removing vehicle with id={}", id, e));
    }
}