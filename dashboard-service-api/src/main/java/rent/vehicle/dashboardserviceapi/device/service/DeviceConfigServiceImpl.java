package rent.vehicle.dashboardserviceapi.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rent.vehicle.device.constants.ApiPaths;
import rent.vehicle.dashboardserviceapi.common_config.CustomPage;
import rent.vehicle.dashboardserviceapi.common_config.QueryParamUtil;
import rent.vehicle.device.dto.DeviceConfigCreateUpdateDto;
import rent.vehicle.device.dto.DeviceConfigDto;
import rent.vehicle.device.dto.ListDeviceConfigsRequest;

@Service
@RequiredArgsConstructor
public class DeviceConfigServiceImpl implements DeviceConfigService {

    private final WebClient deviceWebClient;

    @Override
    public Mono<DeviceConfigDto> createDeviceConfig(DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto) {
        return deviceWebClient.post()
                .uri(ApiPaths.PATH_DEVICE_CONFIG)
                .bodyValue(deviceConfigCreateUpdateDto)
                .retrieve()
                .bodyToMono(DeviceConfigDto.class);
    }

    @Override
    public Mono<DeviceConfigDto> updateDeviceConfig(long id, DeviceConfigCreateUpdateDto deviceConfigCreateUpdateDto) {
        return deviceWebClient.put()
                .uri(ApiPaths.PATH_DEVICE_CONFIG + ApiPaths.PATH_ID, id)
                .bodyValue(deviceConfigCreateUpdateDto)
                .retrieve()
                .bodyToMono(DeviceConfigDto.class);
    }

    @Override
    public Mono<DeviceConfigDto> findDeviceConfigById(long id) {
        return deviceWebClient.get()
                .uri(ApiPaths.PATH_DEVICE_CONFIG + ApiPaths.PATH_ID, id)
                .retrieve()
                .bodyToMono(DeviceConfigDto.class);
    }

    @Override
    public Mono<CustomPage<DeviceConfigDto>> findAllDeviceConfig(Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(null, pageable);

        return deviceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_DEVICE_CONFIG + ApiPaths.PATH_LIST)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<DeviceConfigDto>>() {
                });
    }

    @Override
    public Mono<CustomPage<DeviceConfigDto>> getListDevicesConfigByParam(ListDeviceConfigsRequest listDeviceConfigsRequest, Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(listDeviceConfigsRequest, pageable);

        return deviceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiPaths.PATH_DEVICE_CONFIG + ApiPaths.PATH_SEARCH)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<DeviceConfigDto>>() {
                });
    }

    @Override
    public Mono<Void> removeDeviceConfig(long id) {
        return deviceWebClient.delete()
                .uri(ApiPaths.PATH_DEVICE_CONFIG + ApiPaths.PATH_ID, id) // /api/device-config/{id}
                .retrieve()
                .bodyToMono(Void.class);
    }


}
