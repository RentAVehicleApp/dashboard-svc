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
import rent.vehicle.dto.DeviceCreateUpdateDto;
import rent.vehicle.dto.DeviceDto;
import rent.vehicle.dto.ListDevicesRequest;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{
    private final WebClient webClient;

    @Override
    public Mono<DeviceDto> createDevice(DeviceCreateUpdateDto deviceCreateUpdateDto) {
        return webClient.post()
                .uri(ApiPaths.PATH_DEVICE)
                .bodyValue(deviceCreateUpdateDto)
                .retrieve()
                .bodyToMono(DeviceDto.class);
    }

    @Override
    public Mono<DeviceDto> updateDevice(long id, DeviceCreateUpdateDto deviceCreateUpdateDto) {
        return webClient.put()
                .uri(ApiPaths.PATH_DEVICE + ApiPaths.PATH_ID, id)
                .bodyValue(deviceCreateUpdateDto)
                .retrieve()
                .bodyToMono(DeviceDto.class);
    }

    @Override
    public Mono<DeviceDto> findDeviceById(long id) {
        return webClient.get()
                .uri(ApiPaths.PATH_DEVICE + ApiPaths.PATH_ID, id)
                .retrieve()
                .bodyToMono(DeviceDto.class);
    }

    @Override
    public Mono<CustomPage<DeviceDto>> findAllDevices(Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(null, pageable);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_DEVICE + ApiPaths.PATH_LIST)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<DeviceDto>>() {
                });
    }

    @Override
    public Mono<CustomPage<DeviceDto>> findListDevicesByParam(ListDevicesRequest listDevicesRequest, Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(listDevicesRequest, pageable);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_DEVICE + ApiPaths.PATH_SEARCH)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<DeviceDto>>() {
                });
    }

    @Override
    public Mono<CustomPage<DeviceDto>> findDevicesWithoutVehicle(Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(null, pageable);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_DEVICE + ApiPaths.WITHOUT_VEHICLE)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<DeviceDto>>() {
                });
    }

    @Override
    public Mono<Void> removeDevice(long id) {
        return webClient.delete()
                .uri(ApiPaths.PATH_DEVICE + ApiPaths.PATH_ID, id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
