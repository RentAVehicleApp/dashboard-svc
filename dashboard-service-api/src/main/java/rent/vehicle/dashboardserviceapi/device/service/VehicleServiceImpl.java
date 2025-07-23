package rent.vehicle.dashboardserviceapi.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rent.vehicle.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.device.config.CustomPage;
import rent.vehicle.dashboardserviceapi.device.config.QueryParamUtil;
import rent.vehicle.dto.ListVehiclesRequest;
import rent.vehicle.dto.PointFromLatLonDto;
import rent.vehicle.dto.VehicleCreateUpdateDto;
import rent.vehicle.dto.VehicleDto;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final WebClient webClient;

    @Override
    public Mono<VehicleDto> createVehicle(VehicleCreateUpdateDto vehicleCreateUpdateDto) {
        return webClient.post()
                .uri(ApiPaths.PATH_VEHICLE)
                .bodyValue(vehicleCreateUpdateDto)
                .retrieve()
                .bodyToMono(VehicleDto.class);
    }

    @Override
    public Mono<VehicleDto> updateVehicle(long id, VehicleCreateUpdateDto vehicleCreateUpdateDto) {
        return webClient.put()
                .uri(ApiPaths.PATH_VEHICLE + ApiPaths.PATH_ID, id)
                .bodyValue(vehicleCreateUpdateDto)
                .retrieve()
                .bodyToMono(VehicleDto.class);
    }

    @Override
    public Mono<VehicleDto> findVehicleById(long id) {
        return webClient.get()
                .uri(ApiPaths.PATH_VEHICLE + ApiPaths.PATH_ID, id)
                .retrieve()
                .bodyToMono(VehicleDto.class);
    }

    @Override
    public Mono<CustomPage<VehicleDto>> findAllVehicle(Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(null, pageable);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_VEHICLE + ApiPaths.PATH_LIST)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<VehicleDto>>() {
                });
    }

    @Override
    public Mono<CustomPage<VehicleDto>> getListVehiclesByParam(ListVehiclesRequest listVehiclesRequest, Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(listVehiclesRequest, pageable);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_VEHICLE + ApiPaths.PATH_SEARCH)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<VehicleDto>>() {
                });
    }

    //TODO remove не работает
    @Override
    public Mono<Void> removeVehicle(long id) {
        return webClient.delete()
                .uri(ApiPaths.PATH_VEHICLE + ApiPaths.PATH_ID, id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<CustomPage<VehicleDto>> findNearbyVehicles(PointFromLatLonDto pointFromLatLonDto, long radiusMeters, Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(pointFromLatLonDto, pageable);
        queryParams.add("radiusMeters", String.valueOf(radiusMeters));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_VEHICLE + ApiPaths.PATH_NEARBY)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<VehicleDto>>() {
                });
    }
}
