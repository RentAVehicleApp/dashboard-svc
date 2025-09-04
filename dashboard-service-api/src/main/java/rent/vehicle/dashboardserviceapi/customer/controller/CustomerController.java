package rent.vehicle.dashboardserviceapi.customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rent.vehicle.customer.dto.CreateCustomerDto;
import rent.vehicle.customer.dto.CustomerResponse;
import rent.vehicle.customer.dto.UpdateCustomerDto;
import rent.vehicle.dashboardserviceapi.common.config.CustomPage;
import rent.vehicle.dashboardserviceapi.customer.customer_service.CustomerDashboardService;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {


    private final CustomerDashboardService customerDashboardService;


    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerResponse> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        log.info("Request to create customer: {}", createCustomerDto);
        return customerDashboardService.createCustomer(createCustomerDto)
                .doOnSuccess(customer -> log.info("Successfully created customer: {}", customer))
                .doOnError(e -> log.error("Error creating customer: {}", createCustomerDto, e));
    }

    @GetMapping("/customers/{userId}")
    public Mono<CustomerResponse> getCustomer(@PathVariable Long userId) {
        log.debug("Request to get customer with userId={}", userId);
        return customerDashboardService.getCustomer(userId)
                .doOnSuccess(customer -> log.debug("Found customer: {}", customer))
                .doOnError(e -> log.error("Error fetching customer with userId={}", userId, e));
    }

    @PatchMapping("/customers/update/{userId}")
    public Mono<CustomerResponse> updateCustomer(@PathVariable Long userId,
                                                 @RequestBody UpdateCustomerDto updateCustomerDto) {
        log.info("Request to update customer id={}, data={}", userId, updateCustomerDto);
        return customerDashboardService.updateCustomer(userId, updateCustomerDto)
                .doOnSuccess(customer -> log.info("Successfully updated customer: {}", customer))
                .doOnError(e -> log.error("Error updating customer id={}, data={}", userId, updateCustomerDto, e));
    }

    @DeleteMapping("/customers/remove/{userId}")
    public Mono<CustomerResponse> deleteCustomer(@PathVariable Long userId) {
        log.info("Request to delete customer with userId={}", userId);
        return customerDashboardService.removeCustomer(userId)
                .doOnSuccess(customer -> log.info("Successfully deleted customer: {}", customer))
                .doOnError(e -> log.error("Error deleting customer with userId={}", userId, e));
    }

    @GetMapping("/customers/all")
    public Mono<CustomPage<CustomerResponse>> getAllCustomers(Pageable pageable) {
        log.debug("Request to get all customers, pageable={}", pageable);
        return customerDashboardService.getAll(pageable)
                .doOnSuccess(page -> log.debug("Found {} customers", page.getContent().size()))
                .doOnError(e -> log.error("Error fetching all customers, pageable={}", pageable, e));
    }

    @GetMapping("/customers/search")
    public Mono<CustomPage<CustomerResponse>> searchCustomersByParams( @RequestParam(required = false) String filter,Pageable pageable){
        log.debug("Request to search customers with filter='{}', pageable={}", filter, pageable);
        return customerDashboardService.searchCustomers(filter, pageable)
                .doOnSuccess(page -> log.debug("Found {} customers by filter='{}'", page.getContent().size(), filter))
                .doOnError(e -> log.error("Error searching customers with filter='{}', pageable={}", filter, pageable, e));
    }
}
