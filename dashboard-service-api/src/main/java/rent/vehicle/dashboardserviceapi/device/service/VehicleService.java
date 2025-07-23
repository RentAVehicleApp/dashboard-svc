package rent.vehicle.dashboardserviceapi.device.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.device.config.CustomPage;
import rent.vehicle.dto.ListVehiclesRequest;
import rent.vehicle.dto.PointFromLatLonDto;
import rent.vehicle.dto.VehicleCreateUpdateDto;
import rent.vehicle.dto.VehicleDto;

public interface VehicleService {
    Mono<VehicleDto> createVehicle(VehicleCreateUpdateDto vehicleCreateUpdateDto);

    Mono<VehicleDto> updateVehicle(long id, VehicleCreateUpdateDto vehicleCreateUpdateDto);

    Mono<VehicleDto> findVehicleById(long id);

    Mono<CustomPage<VehicleDto>> findAllVehicle(Pageable pageable);

    Mono<CustomPage<VehicleDto>> getListVehiclesByParam(ListVehiclesRequest listVehiclesRequest, Pageable pageable);

    Mono<Void> removeVehicle(long id);

    Mono<CustomPage<VehicleDto>> findNearbyVehicles(PointFromLatLonDto pointFromLatLonDto, long radiusMeters, Pageable pageable);
}
