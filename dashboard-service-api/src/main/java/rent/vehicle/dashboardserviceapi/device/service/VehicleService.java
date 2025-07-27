package rent.vehicle.dashboardserviceapi.device.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.device.dto.ListVehiclesRequest;
import rent.vehicle.device.dto.PointFromLatLonDto;
import rent.vehicle.device.dto.VehicleCreateUpdateDto;
import rent.vehicle.device.dto.VehicleDto;

public interface VehicleService {

    Mono<VehicleDto> createVehicle(VehicleCreateUpdateDto vehicleCreateUpdateDto);

    Mono<VehicleDto> updateVehicle(long id, VehicleCreateUpdateDto vehicleCreateUpdateDto);

    Mono<VehicleDto> findVehicleById(long id);

    Mono<CustomPage<VehicleDto>> findAllVehicle(Pageable pageable);

    Mono<CustomPage<VehicleDto>> getListVehiclesByParam(ListVehiclesRequest listVehiclesRequest, Pageable pageable);

    Mono<Void> removeVehicle(long id);

    Mono<CustomPage<VehicleDto>> findNearbyVehicles(PointFromLatLonDto pointFromLatLonDto, long radiusMeters, Pageable pageable);

}
