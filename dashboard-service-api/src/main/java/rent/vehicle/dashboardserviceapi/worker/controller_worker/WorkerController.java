package rent.vehicle.dashboardserviceapi.worker.controller_worker;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.worker.worker_service.WorkerDashboardService;
import rent.vehicle.worker.dto.CreateWorkerDto;
import rent.vehicle.worker.dto.ResponseWorkerDto;
import rent.vehicle.worker.dto.UpdateWorkerDto;

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
        return workerDashboardService.createWorker(createWorkerDto);
    }

    @PostMapping("/workers/supporter/{id}/update")
    public Mono<ResponseWorkerDto> updateWorker(@PathVariable Long id,
                                                @RequestBody UpdateWorkerDto updateWorkerDto) {
        return workerDashboardService.updateWorker(id, updateWorkerDto);
    }

    @GetMapping("/workers/supporter/{id}")
    public Mono<ResponseWorkerDto> getWorker(@PathVariable Long id) {
        return workerDashboardService.findWorker(id);
    }

    @DeleteMapping("/workers/supporter/{id}")
    public Mono<Boolean> deleteWorker(@PathVariable Long id) {
        return workerDashboardService.removeWorker(id);
    }

    @GetMapping("/workers/all")
    public Mono<CustomPage<ResponseWorkerDto>> getAllWorkers(Pageable pageable) {
        return workerDashboardService.getAll(pageable);
    }

    @GetMapping("/workers/search")
    public Mono<CustomPage<ResponseWorkerDto>> searchWorkersByParams( @RequestParam(required = false) String filter,Pageable pageable) {
        return workerDashboardService.searchWorkers(filter,pageable);
    }

}