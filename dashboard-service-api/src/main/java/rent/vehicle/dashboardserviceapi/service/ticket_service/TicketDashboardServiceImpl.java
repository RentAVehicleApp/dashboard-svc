package rent.vehicle.dashboardserviceapi.service.ticket_service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rent.vehicle.common.CustomPage;
import rent.vehicle.dashboardserviceapi.configuration.QueryParamUtil;
import rent.vehicle.dashboardserviceapi.service.adapter.SearchAdapterService;
import rent.vehicle.specification.dto.GenericSearchRequest;
import rent.vehicle.ticket.dto.CreateTicketDto;
import rent.vehicle.ticket.dto.ResponseTicketDto;
import rent.vehicle.ticket.dto.UpdateTicketDto;
import org.springframework.data.domain.Pageable;


@Service
@RequiredArgsConstructor
public class TicketDashboardServiceImpl implements  TicketDashboardService {
    private final WebClient workerServiceWebClient;
    private final SearchAdapterService customerAdapterService;

    @Override
    public Mono<ResponseTicketDto> createTicket(CreateTicketDto createTicketDto) {

        return workerServiceWebClient
                .post()
                .uri("/api/v1/workers/ticket")
                .bodyValue(createTicketDto)
                .retrieve()
                .bodyToMono(ResponseTicketDto.class);

    }

    @Override
    public Mono<ResponseTicketDto> updateTicket(Long id, UpdateTicketDto updateTicketDto) {

        return workerServiceWebClient
                .post()
                .uri("/api/v1/workers/ticket/{id}", id)
                .bodyValue(updateTicketDto)
                .retrieve()
                .bodyToMono(ResponseTicketDto.class);

    }

    @Override
    public Mono<ResponseTicketDto> findTicket(Long id) {

        return workerServiceWebClient
                .get()
                .uri("/api/v1/workers/ticket/{id}", id)
                .retrieve()
                .bodyToMono(ResponseTicketDto.class);

    }

    @Override
    public Mono<Boolean> removeTicket(Long id) {
        return workerServiceWebClient
                .delete()
                .uri("/api/v1/workers/ticket/{id}", id)
                .retrieve()
                .bodyToMono(Boolean.class);

    }

    @Override
    public Mono<CustomPage<ResponseTicketDto>> getAll(Pageable pageable) {
        MultiValueMap<String, String> queryParams = QueryParamUtil.convertToQueryParams(null,pageable);

        return workerServiceWebClient
                .get()
                .uri(uriBuilder ->uriBuilder
                        .path("/api/v1/worker/tickets")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<ResponseTicketDto>>(){});
    }

    @Override
    public Mono<CustomPage<ResponseTicketDto>> searchTickets(GenericSearchRequest genericSearchRequest) {
        MultiValueMap<String,String> queryParams = QueryParamUtil.convertToQueryParams(genericSearchRequest,null);
        return workerServiceWebClient
                .get()
                .uri(uriBuilder ->uriBuilder
                        .path("/api/v1/worker/search/tickets")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<ResponseTicketDto>>(){});
    }

}
