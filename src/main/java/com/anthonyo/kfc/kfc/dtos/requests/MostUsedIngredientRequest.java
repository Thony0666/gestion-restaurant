package com.anthonyo.kfc.kfc.dtos.requests;

import com.anthonyo.kfc.kfc.entities.Restaurant;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class MostUsedIngredientRequest {
    String startDate;
    String endDate;
    Integer restaurantId;
    Integer limit;
}
