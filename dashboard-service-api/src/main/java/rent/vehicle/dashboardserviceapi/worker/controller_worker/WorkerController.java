package rent.vehicle.dashboardserviceapi.worker.controller_worker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardService;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.UpdateWorkerDto;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WorkerController {

    private final WorkerDashboardService workerDashboardService;

    // Worker endpoints
    @PostMapping("/workers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseWorkerDto> createWorker(@RequestBody CreateWorkerDto createWorkerDto) {
        log.info("Received request to create worker: {}", createWorkerDto);
        return workerDashboardService.createWorker(createWorkerDto)
                .doOnSuccess(worker -> log.info("Worker created successfully: {}", worker))
                .doOnError(e -> log.error("Error creating worker", e));
    }

    @PostMapping("/workers/supporter/{id}/update")
    public Mono<ResponseWorkerDto> updateWorker(@PathVariable Long id,
                                                @RequestBody UpdateWorkerDto updateWorkerDto) {
        log.info("Received request to update worker id={} with data: {}", id, updateWorkerDto);
        return workerDashboardService.updateWorker(id, updateWorkerDto)
                .doOnSuccess(worker -> log.info("Worker updated successfully: {}", worker))
                .doOnError(e -> log.error("Error updating worker id={}", id, e));
    }

    @GetMapping("/workers/supporter/{id}")
    public Mono<ResponseWorkerDto> getWorker(@PathVariable Long id) {
        log.debug("Fetching worker with id={}", id);
        return workerDashboardService.findWorker(id)
                .doOnSuccess(worker -> log.debug("Found worker: {}", worker))
                .doOnError(e -> log.error("Error fetching worker id={}", id, e));
    }

    @DeleteMapping("/workers/supporter/{id}")
    public Mono<Boolean> deleteWorker(@PathVariable Long id) {
        log.info("Received request to delete worker id={}", id);
        return workerDashboardService.removeWorker(id)
                .doOnSuccess(success -> log.info("Worker id={} deleted successfully", id))
                .doOnError(e -> log.error("Error deleting worker id={}", id, e));
    }

    @GetMapping("/workers/all")
    public Mono<CustomPage<ResponseWorkerDto>> getAllWorkers(Pageable pageable) {
        log.debug("Fetching all workers, pageable={}", pageable);
        return workerDashboardService.getAll(pageable)
                .doOnSuccess(page -> log.debug("Retrieved {} workers", page.getContent().size()))
                .doOnError(e -> log.error("Error fetching all workers", e));
    }


    @GetMapping("/workers/search")
    public Mono<CustomPage<ResponseWorkerDto>> searchWorkersByParams( @RequestParam(required = false) String filter,Pageable pageable) {
        log.debug("Searching workers with filter='{}', pageable={}", filter, pageable);
        return workerDashboardService.searchWorkers(filter, pageable)
                .doOnSuccess(page -> log.debug("Search returned {} workers", page.getContent().size()))
                .doOnError(e -> log.error("Error searching workers with filter='{}'", filter, e));
    }

}