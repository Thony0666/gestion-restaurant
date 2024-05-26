package com.anthonyo.kfc.kfc.dtos.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Getter
@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActionStockDateRangeRequest {
    Integer restaurantId;
    LocalDate startDate;
    LocalDate endDate;
}
