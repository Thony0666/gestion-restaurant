package com.anthonyo.kfc.kfc.service.impl;

import com.anthonyo.kfc.kfc.dtos.requests.SaleMovDateRequest;
import com.anthonyo.kfc.kfc.dtos.responses.SaleMovResponse;
import com.anthonyo.kfc.kfc.entities.DetailMenu;
import com.anthonyo.kfc.kfc.repository.SaleMovRepository;
import com.anthonyo.kfc.kfc.service.SaleMovService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleMovServiceImpl implements SaleMovService {
    private final SaleMovRepository saleMovRepository;

    @Override
    public List<SaleMovResponse> findBetweenDate(SaleMovDateRequest saleMovDateRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            var dateStart = saleMovDateRequest.getStartDate();
            var dateEnd = saleMovDateRequest.getEndDate();
            LocalDate parsedDateStart = LocalDate.parse(dateStart, formatter);
            LocalDate parsedDateEnd = LocalDate.parse(dateEnd, formatter);

            Instant startInstant = parsedDateStart.atStartOfDay(ZoneId.systemDefault()).toInstant();
            Instant endInstant = parsedDateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant();

            List<SaleMovResponse> saleMovResponses = saleMovRepository.findBetweenDate(startInstant, endInstant);

            Map<Integer, List<SaleMovResponse>> groupedByRestaurant = saleMovResponses.stream()
                    .collect(Collectors.groupingBy(SaleMovResponse::getRestaurantId));

            List<SaleMovResponse> result = new ArrayList<>();

            for (Map.Entry<Integer, List<SaleMovResponse>> entry : groupedByRestaurant.entrySet()) {
                List<DetailMenu> detailMenus = entry.getValue().stream()
                        .map(sale -> DetailMenu.builder()
                                .menu(sale.getDetailMenuList().getLast().getMenu())
                                .sumQte(sale.getDetailMenuList().getLast().getSumQte())
                                .totalPrice(sale.getDetailMenuList().getLast().getTotalPrice())
                                .build())
                        .collect(Collectors.toList());

                SaleMovResponse firstSaleMov = entry.getValue().get(0);

                result.add(SaleMovResponse.builder()
                        .restaurantId(firstSaleMov.getRestaurantId())
                        .restaurantName(firstSaleMov.getRestaurantName())
                        .detailMenuList(detailMenus)
                        .build());
            }

            return result;

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, please use dd-MM-yyyy", e);
        }
    }
}
