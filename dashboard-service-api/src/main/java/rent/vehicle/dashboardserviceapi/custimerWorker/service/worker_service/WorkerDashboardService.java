package rent.vehicle.dashboardserviceapi.custimerWorker.service.worker_service;

import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.specification.dto.GenericSearchRequest;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.UpdateWorkerDto;

import org.springframework.data.domain.Pageable;

public interface WorkerDashboardService {
    Mono<ResponseWorkerDto> createWorker(CreateWorkerDto createWorkerDto);

    Mono<ResponseWorkerDto> updateWorker(Long id, UpdateWorkerDto updateWorkerDto);

    Mono<ResponseWorkerDto> findWorker(Long id);

    Mono<Boolean> removeWorker(Long id);

    Mono<CustomPage<ResponseWorkerDto>> getAll(Pageable pageable);

    Mono<CustomPage<ResponseWorkerDto>> searchWorkers(GenericSearchRequest genericSearchRequest);

}
