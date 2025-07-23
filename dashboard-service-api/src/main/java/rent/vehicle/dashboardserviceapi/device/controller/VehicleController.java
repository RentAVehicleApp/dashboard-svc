package rent.vehicle.dashboardserviceapi.device.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.device.config.CustomPage;
import rent.vehicle.dashboardserviceapi.device.service.VehicleService;
import rent.vehicle.dto.PointFromLatLonDto;
import rent.vehicle.dto.VehicleCreateUpdateDto;
import rent.vehicle.dto.VehicleDto;
import rent.vehicle.dto.ListVehiclesRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public Mono<VehicleDto> createVehicle(@RequestBody VehicleCreateUpdateDto vehicleCreateUpdateDto) {
        return vehicleService.createVehicle(vehicleCreateUpdateDto);
    }

    @PutMapping(ApiPaths.PATH_ID)
    public Mono<VehicleDto> updateVehicle(@PathVariable long id, @RequestBody VehicleCreateUpdateDto vehicleCreateUpdateDto) {
        return vehicleService.updateVehicle(id, vehicleCreateUpdateDto);
    }

    @GetMapping(ApiPaths.PATH_ID)
    public Mono<VehicleDto> findVehicleById(@PathVariable long id) {
        return vehicleService.findVehicleById(id);
    }

    @GetMapping(ApiPaths.PATH_LIST)
    public Mono<CustomPage<VehicleDto>> findAllVehicle(
            @PageableDefault(size = 2)
            Pageable pageable) {
        return vehicleService.findAllVehicle(pageable);
    }

    @GetMapping(ApiPaths.PATH_SEARCH)
    public Mono<CustomPage<VehicleDto>> findListVehiclesByParam(
            @ModelAttribute ListVehiclesRequest listVehiclesRequest,
            @PageableDefault(size = 2)
            Pageable pageable) {
        return vehicleService.getListVehiclesByParam(listVehiclesRequest, pageable);
    }

    @GetMapping(ApiPaths.PATH_NEARBY)
    public Mono<CustomPage<VehicleDto>> findNearbyVehicles(
            @ModelAttribute PointFromLatLonDto pointFromLatLonDto,
            @RequestParam long radiusMeters,
            @PageableDefault() Pageable pageable
    ) {
        return vehicleService.findNearbyVehicles(pointFromLatLonDto, radiusMeters, pageable);
    }


    @DeleteMapping(ApiPaths.PATH_ID)
    public void removeVehicle(@PathVariable long id) {
        vehicleService.removeVehicle(id);
    }
}