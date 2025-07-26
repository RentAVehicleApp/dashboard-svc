package rent.vehicle.dashboardserviceapi.service.worker_service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.service.adapter.SearchAdapterService;
import rent.vehicle.specification.dto.GenericSearchRequest;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.UpdateWorkerDto;
import rent.vehicle.dashboardserviceapi.common.config.QueryParamUtil;



@Service
@RequiredArgsConstructor
public class WorkerDashboardServiceImpl implements WorkerDashboardService {
    private final WebClient workerServiceWebClient;
    private final SearchAdapterService workerAdapterService;
    // Worker methods implementation
    @Override
    public Mono<ResponseWorkerDto> createWorker(CreateWorkerDto createWorkerDto) {
        return workerServiceWebClient
                .post()
                .uri("/api/v1/worker")
                .bodyValue(createWorkerDto)
                .retrieve()
                .bodyToMono(ResponseWorkerDto.class);
    }

    @Override
    public Mono<ResponseWorkerDto> updateWorker(Long id, UpdateWorkerDto updateWorkerDto) {
        return workerServiceWebClient
                .post()
                .uri("/api/v1/worker/supporter/{id}/update", id)
                .bodyValue(updateWorkerDto)
                .retrieve()
                .bodyToMono(ResponseWorkerDto.class);

    }

    @Override
    public Mono<ResponseWorkerDto> findWorker(Long id) {
        return workerServiceWebClient
                .get()
                .uri("/api/v1/worker/supporter/{id}", id)
                .retrieve()
                .bodyToMono(ResponseWorkerDto.class);

    }

    @Override
    public Mono<Boolean> removeWorker(Long id) {
        return workerServiceWebClient
                .delete()
                .uri("/api/v1/worker/supporter/{id}", id)
                .retrieve()
                .bodyToMono(Boolean.class);

    }

    @Override
    public Mono<CustomPage<ResponseWorkerDto>> getAll(Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(null,pageable);

        return workerServiceWebClient
                .get()
                .uri(uriBuilder ->uriBuilder
                        .path("/api/v1/worker/workers")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<ResponseWorkerDto>>(){});
    }

    @Override
    public Mono<CustomPage<ResponseWorkerDto>> searchWorkers(GenericSearchRequest genericSearchRequest) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(genericSearchRequest,null);

        return workerServiceWebClient
                .get()
                .uri(uriBuilder ->uriBuilder
                        .path("/api/v1/worker/workers")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<ResponseWorkerDto>>(){});
    }

}
